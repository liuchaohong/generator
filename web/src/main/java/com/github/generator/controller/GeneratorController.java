package com.github.generator.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.generator.executor.GenCmd;
import com.github.generator.util.ZipHelper;


@Controller
@RequestMapping("/generator")
public class GeneratorController {

//	static ByteArrayOutputStream memoryConsole = new ByteArrayOutputStream();
//	static {
//		System.setOut(new PrintStream(new TeeOutputStream(System.out,(memoryConsole))));
//	}
	public static String outputRootDir = System.getProperty("java.io.tmpdir") + File.separator + "webgeneratorOutput" + File.separator;
	
	@RequestMapping
	public void gen(GenCmd genCmd,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String outputPath = outputRootDir + System.currentTimeMillis();
		genCmd.setOutputRootDir(outputPath);
		genCmd.execute();
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + "generatorOutput.zip" + "\"");
		ZipHelper.zip(outputPath, response.getOutputStream());
	}
	
//	@RequestMapping
//	public void gen(GenCmdExecutor cmd,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		memoryConsole.reset();
//		cmd.response = response;
//		cmd.request = request;
//		cmd.memoryConsole = memoryConsole;
//		cmd.execute();
//	}
	
}
