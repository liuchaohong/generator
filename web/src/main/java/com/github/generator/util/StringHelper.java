package com.github.generator.util;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

	/**
	 * game_id ==> GameId
	 * @param str
	 * @return
	 */
	public static String getClassName(String str){
		str = replace(str);
		String[] strArray = str.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			sb.append(toUpperCaseFirstOne(strArray[i]));
		}
		return sb.toString();
	}
	
	/**
	 * game_id ==> gameId
	 * @param str
	 * @return
	 */
	public static String getColumnNameFirstLower(String str){
		return toLowerCaseFirstOne(getClassName(str));
	}
	
	public static String getColumnNameFirstUpper(String str){
		return getClassName(str);
	}
	
	/**
	 * [`id`, `game_id`] ==> List<String>
	 * @return
	 */
	public static List<String> getPrimaryKeys(String str){
		str = replace(str);
		String[] strArray = str.split(",");
		List<String> primaryKeys = new ArrayList<String>();
		for (int i = 0; i < strArray.length; i++) {
			primaryKeys.add(strArray[i]);
		}
		return primaryKeys;
	}
	
	
	public static void main(String[] args){
		System.out.println(getClassName("`game_id`"));
		
		System.out.println(toLowerCaseFirstOne(getClassName("`game_id`")));
		
		System.out.println(getPrimaryKeys("[`id`, `game_id`]").get(0));
	}
	
	public static String replace(String str){
		return str.replace("`", "").replace("[", "").replace("]", "");
	}
	
	//首字母转小写
	public static String toLowerCaseFirstOne(String str){
        if(Character.isLowerCase(str.charAt(0)))
            return str;
        else
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }
	
	//首字母转大写
    public static String toUpperCaseFirstOne(String str){
        if(Character.isUpperCase(str.charAt(0)))
            return str;
        else
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }
    
    /**
     * 判断str是否包含keywords
     * @param str
     * @param keywords
     * @return
     */
	public static boolean contains(String str, String... keywords) {
		if(str == null) return false;
		if(keywords == null) throw new IllegalArgumentException("'keywords' must be not null");
		for(String keyword : keywords) {
			if(str.contains(keyword.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
