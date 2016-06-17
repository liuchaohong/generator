
package ${basepackage}.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.rapid.common.beanutils.BeanUtils;
import com.github.rapid.common.exception.MessageException;
import com.github.rapid.common.web.scope.Flash;
import com.github.rapid.common.util.CsvFileUtil;
import com.github.rapid.common.util.ValidationErrorsUtil;
import com.github.rapid.common.util.page.Page;


/**
 * [${table.tableAlias}] 的业务操作
 * 
<#include "/java_description.include">
 *
 */
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Controller {
	
	private static Logger logger = LoggerFactory.getLogger(${className}Controller.class);
	
	private ${className}Service ${classNameFirstLower}Service;
	
	private final String LIST_ACTION = "redirect:/${classNameLowerCase}/index.do";
	
	private static String CREATED_SUCCESS = "创建成功";
	private static String UPDATE_SUCCESS = "更新成功";
	private static String DELETE_SUCCESS = "删除成功";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void set${className}Service(${className}Service service) {
		this.${classNameFirstLower}Service = service;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}

}


