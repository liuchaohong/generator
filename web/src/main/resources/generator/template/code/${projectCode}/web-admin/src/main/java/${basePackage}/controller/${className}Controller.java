<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basePackage}.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.alibaba.fastjson.JSON;
import com.github.rapid.common.util.CollectionUtil;
import com.github.rapid.common.util.CsvFileUtil;
import com.github.rapid.common.web.scope.Flash;
import ${basePackage}.model.Page;
import ${basePackage}.model.${className};
import ${basePackage}.query.${className}Query;
import ${basePackage}.service.${className}Service;

@Controller
@RequestMapping("/${classNameFirstLower}")
public class ${className}Controller {

	private static Logger logger = LoggerFactory.getLogger(${className}Controller.class);
	
	@Autowired
	private ${className}Service ${classNameFirstLower}Service;
	
	private final String LIST_ACTION = "redirect:/${classNameFirstLower}/index.do";
	
	private static String INSERT_SUCCESS = "新增成功";
	private static String UPDATE_SUCCESS = "更新成功";
	private static String DELETE_SUCCESS = "删除成功";
	
	/** 列表 */
	@RequestMapping
	public String index(ModelMap model, ${className}Query ${classNameFirstLower}Query) {
		return "/${classNameFirstLower}/index";
	}
	
	/**
	 * 分页查询
	 * @param gameQuery
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String page(${className}Query ${classNameFirstLower}Query, HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");  
		Page<${className}> page = ${classNameFirstLower}Service.getPage(${classNameFirstLower}Query);
		String jsonStr = JSON.toJSONString(page);
		return jsonStr;
	}
	
	
	/** 显示 */
	@RequestMapping
	public String show(<@generateArguments table.pkColumns/>, ModelMap model) throws Exception {
		${className} ${classNameFirstLower} = ${classNameFirstLower}Service.getById(<@generatePassingParameters table.pkColumns/>);
		model.put("${classNameFirstLower}", ${classNameFirstLower});
		return "/${classNameFirstLower}/show";
	}
	
	/** 进入新增 */
	@RequestMapping
	public String add(ModelMap model) throws Exception {
		return "/${classNameFirstLower}/add";
	}
	
	/** 保存新增 **/
	@RequestMapping(method=RequestMethod.POST)
	public String insert(${className} ${classNameFirstLower}, ModelMap model) throws Exception {
		${classNameFirstLower}Service.insert(${classNameFirstLower});
		Flash.current().success(INSERT_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 进入编辑 **/
	@RequestMapping
	public String edit(<@generateArguments table.pkColumns/>, ModelMap model) throws Exception {
		${className} ${classNameFirstLower} = ${classNameFirstLower}Service.getById(<@generatePassingParameters table.pkColumns/>);
		model.put("${classNameFirstLower}", ${classNameFirstLower});
		return "/${classNameFirstLower}/edit";
	}
	
	/** 保存更新 **/
	@RequestMapping(method=RequestMethod.POST)
	public String update(${className} ${classNameFirstLower}, ModelMap model) throws Exception {
		${classNameFirstLower}Service.update(${classNameFirstLower});
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping
	public String delete(<@generateArguments table.pkColumns/>, ModelMap model) {
		${classNameFirstLower}Service.deleteById(<@generatePassingParameters table.pkColumns/>);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 上传csv文件保存  */
	@RequestMapping(method=RequestMethod.POST)
	public String upload(@RequestParam("file")  CommonsMultipartFile file) throws Exception {
		Assert.isTrue(file.getOriginalFilename().endsWith(".csv"), "只能上传.csv文件");
		int skipLines = 1;
		List<Map> maps = CsvFileUtil.readCsv2Maps(file.getInputStream(), "UTF-8", "<@generatePassingParameters table.pkColumns/>", skipLines);
		List<${className}> items = CollectionUtil.toBeanList(maps, ${className}.class);
		int successCount = 0;
		int errorCount = 0;
		for(${className} item : items) {
			try {
				${classNameFirstLower}Service.insert(item);
				successCount++;
			}catch(Exception e) {
				errorCount++;
				logger.info("upload error",e);
			}
		}
		Flash.current().success("上传成功, 创建成功条数:{}, 失败条数:{}", successCount, errorCount);
		return LIST_ACTION;
	}
	
}


