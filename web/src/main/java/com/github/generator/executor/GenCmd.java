package com.github.generator.executor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import com.github.generator.model.GenColumn;
import com.github.generator.model.GenTable;
import com.github.generator.util.StringHelper;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;

public class GenCmd {

	public String sqls;
	public String basepackage;
	public String tableRemovePrefixes = "";
	public String namespace = "admin";
	
	
	public void execute() throws FileNotFoundException, IOException, JSQLParserException{
//		Assert.hasText(sqls, "'sqls' must be not blank");
//		Assert.hasText(basepackage, "'basepackage' must be not blank");
		
		
		String statement = FileUtils.readFileToString(ResourceUtils.getFile("classpath:test_sql/a.sql"));
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		CreateTable createTable = (CreateTable) parserManager.parse(new StringReader(statement));
		String tableName = StringHelper.replace(createTable.getTable().getName());
		List<GenColumn> columns = new ArrayList<GenColumn>();
		List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
		for(ColumnDefinition columnDefinition : columnDefinitions){
			GenColumn genColumn = new GenColumn();
			genColumn.setSqlName(StringHelper.replace(columnDefinition.getColumnName()));
			genColumn.setDataType(columnDefinition.getColDataType().getDataType());
			columns.add(genColumn);
		}
		
		List<GenColumn> pkColumns = new ArrayList<GenColumn>();
		List<String> primaryKeys = ((Index)createTable.getIndexes().get(0)).getColumnsNames();
		for(String primaryKey : primaryKeys){
			GenColumn genColumn = new GenColumn();
			genColumn.setSqlName(StringHelper.replace(primaryKey));
			pkColumns.add(genColumn);
		}
		
		GenTable genTable = new GenTable();
		genTable.setSqlName(tableName);
		genTable.setColumns(columns);
		genTable.setPkColumns(pkColumns);
		
		System.out.println(genTable);
		
	}
	
	
}
