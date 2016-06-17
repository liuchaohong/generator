package com.github.generator.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.generator.executor.GenCmdExecutor;


@Controller
@RequestMapping("/generator")
public class GeneratorController {

	static ByteArrayOutputStream memoryConsole = new ByteArrayOutputStream();
	static {
		System.setOut(new PrintStream(new TeeOutputStream(System.out,(memoryConsole))));
	}
	
	@RequestMapping
	public void gen(GenCmdExecutor cmd,HttpServletRequest request,HttpServletResponse response) throws Exception {
		memoryConsole.reset();
		cmd.response = response;
		cmd.request = request;
		cmd.memoryConsole = memoryConsole;
		cmd.execute();
	}

	
}
