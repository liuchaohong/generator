<#assign className = table.className> 
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basePackage}.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ${basePackage}.dao.${className}Dao;
import ${basePackage}.model.${className};

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/*.xml"})
public class ${className}Test extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ${className}Dao ${classNameFirstLower}Dao;
	
	@Test
	public void insert(){
		
	}
	
}
