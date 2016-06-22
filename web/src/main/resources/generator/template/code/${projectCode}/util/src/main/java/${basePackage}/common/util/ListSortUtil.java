package ${basePackage}.common.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 用法:
 * List<T> beans = xxxx;
   ListSortUtil<T> sortList = new ListSortUtil<T>(); 
   sortList.sort(beans, "field", "asc");  
   
<#include "/java_description.include">
 *
 */
public class ListSortUtil<T> {  
	
    /** 
     * @param targetList 目标排序List 
     * @param sortField 排序字段(实体类属性名) 
     * @param sortMode 排序方式（asc or  desc） 
     */  
	@SuppressWarnings("all")
    public void sort(List<T> targetList, final String sortField, final String sortMode) {  
      
        Collections.sort(targetList, new Comparator() {  
            public int compare(Object obj1, Object obj2) {   
                int retVal = 0;  
                try {  
                    //首字母转大写  
                    String newStr = sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");   
                    String methodStr = "get" + newStr;  
                      
                    Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);  
                    Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);  
                    if (sortMode != null && "desc".equals(sortMode)) {  
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序  
                    } else {  
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序  
                    }  
                } catch (Exception e) {  
                    throw new RuntimeException();  
                }  
                return retVal;  
            }  
        });  
    }  
      
}  