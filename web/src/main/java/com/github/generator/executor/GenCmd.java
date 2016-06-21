package com.github.generator.executor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import com.github.generator.model.GenColumn;
import com.github.generator.model.GenTable;
import com.github.generator.test.GenTest;
import com.github.generator.util.FileHelper;
import com.github.generator.util.FreemarkerUtil;
import com.github.generator.util.StringHelper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import freemarker.template.Template;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;

/**
 * 
 * @author LIUCHAOHONG
 *
 */
public class GenCmd {

	private static Logger logger = LoggerFactory.getLogger(GenTest.class);
	
	public String projectCode;
	public String author;
	public String sqls;
	public String basePackage;
	public String tableRemovePrefixes;
	private String outputRootDir = System.getProperty("java.io.tmpdir") + File.separator + "webgeneratorOutput" + File.separator + System.currentTimeMillis();
	private StringBuilder sb = new StringBuilder();
		
	public void execute() throws FileNotFoundException, IOException, JSQLParserException{
		Assert.hasText(projectCode, "'projectCode' must be not blank");
		Assert.hasText(author, "'author' must be not blank");
		Assert.hasText(sqls, "'sqls' must be not blank");
		Assert.hasText(basePackage, "'basePackage' must be not blank");
		
		if (StringUtils.isNotBlank(sqls)) {
			String[] createSqlArray = sqls.split(";");
			for (int i = 0; i < createSqlArray.length; i++) {
				genBySql(createSqlArray[i]);
			}
		}
	}

    private void genBySql(String createSql) throws IOException{
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		CreateTable createTable = null;
		try {
			 createTable = (CreateTable) parserManager.parse(new StringReader(createSql));
		} catch (JSQLParserException e) {
			throw new RuntimeException("sql parse error, please check your sql...", e);
		}
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
			List<String> columnSpecStrings = columnDefinition.getColumnSpecStrings();
			if (StringUtils.equalsIgnoreCase(columnSpecStrings.get(columnSpecStrings.size()-2), "comment")) {
				String comment = columnSpecStrings.get(columnSpecStrings.size()-1).replace("'", "");
				genColumn.setComment(comment);
			}
			columns.add(genColumn);
			
			if (replacePrimaryKeys.contains(columnName)) {
				pkColumns.add(genColumn);
			}else{
				notPkColumns.add(genColumn);
			}
		}
		
		GenTable genTable = new GenTable();
		genTable.setTableRemovePrefixes(tableRemovePrefixes);
		genTable.setSqlName(tableName);
		genTable.setColumns(columns);
		genTable.setPkColumns(pkColumns);
		genTable.setNotPkColumns(notPkColumns);
		
		logger.info("{} => {}", tableName, genTable.toString());
		sb.append(tableName).append(" => ").append(genTable.toString()).append("\r\n");
		
    	Map<String, Object> paramMap = FreemarkerUtil.getDefaultParamMap();
    	paramMap.put("projectCode", projectCode);
    	paramMap.put("author", author);
    	paramMap.put("basePackage", basePackage);
    	paramMap.put("table", genTable);
    	paramMap.put("classNameFirstLower", genTable.getClassNameFirstLower());
    	paramMap.put("className", genTable.getClassName());
    	
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
					File outputFile = new File(outputRootDir + File.separator + filePath);
					
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
			    		sb.append(file).append(" ==> ").append(outputFile).append("\r\n");
					}
				} catch (Exception e) {
					logger.error("file template parse error", e);
					sb.append("file template parse error").append(e).append("\r\n");
				}
			}
    	}
    	FileUtils.writeStringToFile(new File(outputRootDir + File.separator + "log.txt"), sb.toString());
    }
    
    /**
     * 判断文件是否需要跳过
     * @param file
     * @return
     */
    private boolean isSkipFile(File file){
    	boolean flag = false;
    	List<String> skipExtensions = Arrays.asList(".js", ".css", ".csv", ".map", ".jpg", ".png", ".gif", ".doc", ".txt", ".eot", ".svg", ".ttf", ".woff", ".woff2");
    	String fileName = file.getName();
    	String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
    	if (skipExtensions.contains(fileExtension)) {
			flag = true;
		}
    	return flag;
    }
	
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSqls() {
		return sqls;
	}

	public void setSqls(String sqls) {
		this.sqls = sqls;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getTableRemovePrefixes() {
		return tableRemovePrefixes;
	}

	public void setTableRemovePrefixes(String tableRemovePrefixes) {
		this.tableRemovePrefixes = tableRemovePrefixes;
	}

	public String getOutputRootDir() {
		return outputRootDir;
	}

	public void setOutputRootDir(String outputRootDir) {
		this.outputRootDir = outputRootDir;
	}
	
}
