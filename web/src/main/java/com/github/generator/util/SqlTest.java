package com.github.generator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.github.generator.executor.FreemarkerUtil;
import com.github.generator.executor.GenCmd;
import com.github.generator.util.FileHelper;

import cn.org.rapid_framework.generator.util.StringHelper;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.jsqlparser.JSQLParserException;

public class SqlTest {

	@Test
	public void test(){
		String test = StringHelper.toJavaClassName("ad_id_xd");
		System.out.println(test);
	}
	
    @Test
    public void testFreemark() {
          String script = "${DateUtils.addDays(sysTime, -1)?string(\"yyyy-MM-dd HH\")}";
          String result = FreemarkerUtil.parse(script);
          System. out.println(result);
    }

    @Test
    public void testGen() throws FileNotFoundException, IOException, JSQLParserException{
    	new GenCmd().execute();
    }
    
    @Test
    public void readSrc() throws FileNotFoundException, IOException, TemplateException{
//    	String src = FileUtils.readFileToString(ResourceUtils.getFile("classpath:template/${className}Controller.java"));
    	
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("basepackage", "test.package");
    	paramMap.put("classNameLowerCase", "game");
    	paramMap.put("classNameFirstLower", "game");
    	paramMap.put("className", "Game");
    	
    	
//    	String templateOutputDir = System.getProperty("java.io.tmpdir");
    	String genPath = "generator/" + Long.toString(System.currentTimeMillis());
    	File genFile = ResourceUtils.getFile("classpath:generator/output");
    	
    	
    	File templateCodeDir = ResourceUtils.getFile("classpath:generator/template/code");
    	File templateCustomDir = ResourceUtils.getFile("classpath:generator/template/custom");
    	
    	List<File> templateRootDirs = Arrays.asList(templateCodeDir, templateCustomDir);
    	
//    	List<File> files = FileHelper.searchAllNotIgnoreFile(templateRootFile);
    	@SuppressWarnings("unchecked")
		List<File> templateCodeFiles = (List<File>) FileUtils.listFiles(templateCodeDir, null, true);
    	
    	for(File file : templateCodeFiles){
    		String relativePath = FileHelper.getRelativePath(templateCodeDir, file);
			
			if (file.isFile()) {
				String relativePathParse = FreemarkerUtil.parse(relativePath, paramMap, FreemarkerUtil.getDefaultConf());
				File outputFile = new File(genFile, relativePathParse);
				
	    		Template template = FreemarkerUtil.newFreeMarkerConfiguration(templateRootDirs, relativePath).getTemplate(relativePath);
//	    		FreemarkerUtil.processTemplate(template, paramMap, outputFile, "utf-8");
	    		
	    		
	    		String fileContent = FreemarkerUtil.processTemplate(template, paramMap, "utf-8");
	    		FileUtils.writeStringToFile(outputFile, fileContent);
			}
			
			
//			if (file.isFile()) {
//				String relativePathParse = FreemarkerUtil.parse(relativePath, paramMap, FreemarkerUtil.getDefaultConf());
//				String fileStr = FileUtils.readFileToString(file);
//				String fileContent = FreemarkerUtil.parse(fileStr, paramMap, FreemarkerUtil.getDefaultConf(templateRootDirs));
//				File outputFile = new File(genFile, relativePathParse);
//				FileUtils.writeStringToFile(outputFile, fileContent);
//			}
			
    	}
    	

    	System.out.println(templateCodeFiles);
    	
//    	for(File file : templateCodeFiles){
//    		String fileStr = FileUtils.readFileToString(file);
//        	String fileContent = FreemarkerUtil.parse(fileStr, paramMap, FreemarkerUtil.getDefaultConf());
//        	System.out.println(fileContent);
//    	}
    }
}
