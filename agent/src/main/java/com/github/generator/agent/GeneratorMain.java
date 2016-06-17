package com.github.generator.agent;
/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain {
	public static void main(String[] args) throws Exception {
		Generator g = new Generator();
		
		g.clean();
		g.generateTable("blog");
//		g.generateAllTable();
	}
}
