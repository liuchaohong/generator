package com.github.generator.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import com.github.generator.executor.FreemarkerUtil;
import com.github.generator.model.GenColumn;
import com.github.generator.model.GenTable;
import com.github.generator.util.FileHelper;
import com.github.generator.util.StringHelper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;

public class GenTest {

	private static Logger logger = LoggerFactory.getLogger(GenTest.class);
	
    @Test
    public void genTest() throws FileNotFoundException, IOException, TemplateException, JSQLParserException{
//    	String src = FileUtils.readFileToString(ResourceUtils.getFile("classpath:template/${className}Controller.java"));
    	
    	
		String statement = FileUtils.readFileToString(ResourceUtils.getFile("classpath:test_sql/a.sql"));
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		CreateTable createTable = (CreateTable) parserManager.parse(new StringReader(statement));
		String tableName = StringHelper.replace(createTable.getTable().getName());
		List<GenColumn> columns = new ArrayList<GenColumn>();
		List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
		List<GenColumn> pkColumns = new ArrayList<GenColumn>();
		List<GenColumn> notPkColumns = new ArrayList<GenColumn>();
		//获取主键并且处理
		List<String> primaryKeys = ((Index)createTable.getIndexes().get(0)).getColumnsNames();
		List<String> replacePrimaryKeys = Lists.newArrayList(Lists.transform(primaryKeys , new Function<String, String>() {
			public String apply(String Str) {
				return StringHelper.replace(Str);
			}
		}));
		for(ColumnDefinition columnDefinition : columnDefinitions){
			String columnName = StringHelper.replace(columnDefinition.getColumnName());
			GenColumn genColumn = new GenColumn();
			genColumn.setSqlName(columnName);
			genColumn.setDataType(columnDefinition.getColDataType().getDataType());
			columns.add(genColumn);
			
			if (replacePrimaryKeys.contains(columnName)) {
				pkColumns.add(genColumn);
			}else{
				notPkColumns.add(genColumn);
			}
				
		}
		
		GenTable genTable = new GenTable();
		genTable.setSqlName(tableName);
		genTable.setColumns(columns);
		genTable.setPkColumns(pkColumns);
		genTable.setNotPkColumns(notPkColumns);
		System.out.println(genTable);
		
		
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("basePackage", "com.test.package");
    	paramMap.put("classNameFirstLower", genTable.getClassNameFirstLower());
    	paramMap.put("className", genTable.getClassName());
    	paramMap.put("projectCode", "whale");
    	paramMap.put("table", genTable);
    	paramMap.put("author", "LIUCHAOHONG");
    	
    	//文件输出路径
//    	String templateOutputDir = System.getProperty("java.io.tmpdir");
    	File genFile = ResourceUtils.getFile("classpath:generator/templateOutput");
    	String systemTime = String.valueOf(System.currentTimeMillis());
    	//模板路径
    	File templateCodeDir = ResourceUtils.getFile("classpath:generator/template/code");
    	File templateCustomDir = ResourceUtils.getFile("classpath:generator/template/custom");
    	List<File> templateRootDirs = Arrays.asList(templateCodeDir, templateCustomDir);
    	
    	//需要解析的代码文件
//    	List<File> files = FileHelper.searchAllNotIgnoreFile(templateRootFile);
		List<File> templateCodeFiles = (List<File>) FileUtils.listFiles(templateCodeDir, null, true);
    	
    	for(File file : templateCodeFiles){
    		String relativePath = FileHelper.getRelativePath(templateCodeDir, file);
			if (file.isFile()) {
				try {
					String relativePathParse = FreemarkerUtil.parse(relativePath, paramMap, FreemarkerUtil.getDefaultConf());
					//把包名处理成路径：com.test.package => com\test\package
					String fileName = relativePathParse.substring(relativePathParse.lastIndexOf(File.separator)+1, relativePathParse.length());
					String fileRelativePath = relativePathParse.substring(0, relativePathParse.indexOf(fileName)).replace(".", File.separator);
					String filePath = fileRelativePath + fileName;
					File outputFile = new File(genFile,  systemTime + "/" + filePath);
					
					if (isSkipFile(file)) {//不需要解析的文件直接复制
						IOUtils.copy(FileUtils.openInputStream(file), FileUtils.openOutputStream(outputFile));
					}else{
						//获取模板
			    		Template template = FreemarkerUtil.newFreeMarkerConfiguration(templateRootDirs, relativePath).getTemplate(relativePath);
			    		//解析模板
			    		String fileContent = FreemarkerUtil.processTemplate(template, paramMap, "utf-8");
			    		//把解析后的模板内容写到解析后的路径
			    		FileUtils.writeStringToFile(outputFile, fileContent);
			    		logger.info(file + " ==> " + outputFile);
					}
				} catch (Exception e) {
					logger.error("file template parse error", e);
				}
			}
    	}
    }
    
    /**
     * 判断文件是否需要跳过
     * @param file
     * @return
     */
    private boolean isSkipFile(File file){
    	boolean flag = false;
    	List<String> skipExtensions = Arrays.asList(".js", ".css", ".csv", ".map", ".jpg", ".png", ".gif", ".eot", ".svg", ".ttf", ".woff", ".woff2");
    	String fileName = file.getName();
    	String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
    	if (skipExtensions.contains(fileExtension)) {
			flag = true;
		}
    	return flag;
    }
    
}
