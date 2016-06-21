package com.github.generator.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.github.generator.executor.GenCmd;

import net.sf.jsqlparser.JSQLParserException;

public class GenTest {

//	private static Logger logger = LoggerFactory.getLogger(GenTest.class);
	
	@Test
	public void Test() throws FileNotFoundException, IOException, JSQLParserException{
		String sqls = FileUtils.readFileToString(ResourceUtils.getFile("classpath:test_sql/a.sql"));
		GenCmd genCmd = new GenCmd();
		genCmd.setProjectCode("projectx");
		genCmd.setAuthor("LIUCHAOHONG");
		genCmd.setBasePackage("com.test.projectx");
		genCmd.setSqls(sqls);
		genCmd.setTableRemovePrefixes("wh_");
		
		genCmd.execute();
	}
	
	@Test
	public void deleteCsv() throws IOException{
		String fileName = "C:/Users/liuchaohong/AppData/Local/Temp/webgeneratorOutput/1466412402149";
		FileUtils.deleteDirectory(new File(fileName));
	}
}
