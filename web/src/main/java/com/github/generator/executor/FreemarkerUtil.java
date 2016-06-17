package com.github.generator.executor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;

import cn.org.rapid_framework.generator.util.FreemarkerHelper;
import cn.org.rapid_framework.generator.util.GLogger;
import cn.org.rapid_framework.generator.util.StringHelper;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Freemarker工具类
 *
 */
public class FreemarkerUtil {

	/**
	 * 用Freemarker解析字符串脚本(默认参数和配置)
	 * @param script
	 * @return
	 */
	public static String parse(String script) {
		return parse(script, getDefaultParamMap(), getDefaultConf());
	}

	/**
	 * 用两次Freemarker解析字符串脚本
	 * @param script
	 * @param paramMap
	 * @return
	 */
	public static String parse2Time(String script, Map<String, Object> paramMap) {
		paramMap.putAll(getDefaultParamMap());
		String resultLevel1 = parse(script, paramMap, getDefaultConf());
		return parse(resultLevel1, paramMap, getDefaultConf());
	}
	
	/**
	 * 用Freemarker解析字符串脚本
	 * @param script
	 * @param paramMap
	 * @return
	 */
	public static String parse(String script, Map<String, Object> paramMap, Configuration conf) {
		Reader reader = null;
		Writer writer = null;
		String result;
		try {
			reader = new StringReader(script);
			Template template = new Template("" + reader, reader, conf);
			writer = new StringWriter();
			template.process(paramMap, writer);
			result = writer.toString();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(reader);
		}
		return result;
	}
	
	/**
	 * 默认配置
	 * @return
	 */
	public static Configuration getDefaultConf() {
		Configuration conf = new Configuration();
		conf.setNumberFormat("###############");
		conf.setBooleanFormat("true, false");
		return conf;
	}
	
	/**
	 * 默认参数
	 * @return
	 */
	public static Map<String, Object> getDefaultParamMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("DateUtils", new DateUtils());
		params.put("sysTime", new Date());
		return params;
	}
	
	public static String processTemplate(Template template, Map<String, Object> paramMap, String encoding) throws IOException, TemplateException {
		Writer writer = null;
		String result;
		try {
			writer = new StringWriter();
			template.process(paramMap, writer);
			result = writer.toString();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
		return result;
	}
	
	public static void processTemplate(Template template, Map<String, Object> model, File outputFile,String encoding) throws IOException, TemplateException {
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), encoding));
		template.process(model, out);
		out.close();
	}
	
    public static Configuration newFreeMarkerConfiguration(List<File> templateRootDirs, String templateName) throws IOException {
		Configuration conf = new Configuration();
			
		FileTemplateLoader[] templateLoaders = new FileTemplateLoader[templateRootDirs.size()];
		for (int i = 0; i < templateRootDirs.size(); i++) {
			templateLoaders[i] = new FileTemplateLoader((File) templateRootDirs.get(i));
		}
		MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);
			
		conf.setTemplateLoader(multiTemplateLoader);
		conf.setNumberFormat("###############");
		conf.setBooleanFormat("true, false");
		conf.setDefaultEncoding("utf-8");
			
		List<String> autoIncludes = getParentPaths(templateName, "macro.include");
		List<String> availableAutoInclude = getAvailableAutoInclude(conf, autoIncludes);
		conf.setAutoIncludes(availableAutoInclude);
//		GLogger.trace("set Freemarker.autoIncludes:" + availableAutoInclude + " for templateName:" + templateName + " autoIncludes:" + autoIncludes);
		return conf;
	}

	public static List<String> getParentPaths(String templateName, String suffix) {
		String array[] = StringHelper.tokenizeToStringArray(templateName, "\\/");
		List<String> list = new ArrayList<String>();
		list.add(suffix);
		list.add(File.separator + suffix);
		String path = "";
		for (int i = 0; i < array.length; i++) {
			path = path + File.separator + array[i];
			list.add(path + File.separator + suffix);
		}
		return list;
	}
	
	public static List<String> getAvailableAutoInclude(Configuration conf,List<String> autoIncludes) {
		List<String> results = new ArrayList<String>();
		for(String autoInclude : autoIncludes) {
			try {
				Template t = new Template("__auto_include_test__", new StringReader("1"), conf);
				conf.setAutoIncludes(Arrays.asList(new String[]{autoInclude}));
				t.process(new HashMap(), new StringWriter());
				results.add(autoInclude);
			}catch(Exception e) {
			}
		}
		return results;
	}
	
}
