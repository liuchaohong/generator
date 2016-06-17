
 //前N天
  Date.prototype.format = function(fmt)
  {
	    var o = {
	      "M+" : this.getMonth()+1,                 //月份
      "d+" : this.getDate(),                    //日
      "h+" : this.getHours(),                   //小时
      "m+" : this.getMinutes(),                 //分
      "s+" : this.getSeconds(),                 //秒
      "q+" : Math.floor((this.getMonth()+3)/3), //季度
      "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
      if(new RegExp("("+ k +")").test(fmt))
    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	    return fmt;
  }
  
  function getDate(day){
	      var edate=new Date( new Date().getTime()  + (day*24*60*60*1000) ).format("yyyy-MM-dd");
	      return edate;
  }

 /* 
   将String类型解析为Date类型. 
   parseDate('2006-1-1') return new Date(2006,0,1) 
   parseDate(' 2006-1-1 ') return new Date(2006,0,1) 
   parseDate('2006-1-1 15:14:16') return new Date(2006,0,1,15,14,16) 
   parseDate(' 2006-1-1 15:14:16 ') return new Date(2006,0,1,15,14,16); 
   parseDate('2006-1-1 15:14:16.254') return new Date(2006,0,1,15,14,16,254) 
   parseDate(' 2006-1-1 15:14:16.254 ') return new Date(2006,0,1,15,14,16,254) 
   parseDate('不正确的格式') retrun null 
 */  
 function parseDate(str){  
	   if(typeof str == 'string'){  
     var results = str.split("-");
     if(results && results.length>=3)  {
      return new Date(parseFloat(results[0]),parseFloat(results[1]) - 1,parseFloat(results[2]));   
     }
   }  
   throw new Error("cannot parse str for date:"+str);  
 }
 
  /**
   *函数功能 :前N天
   */
 function getBeforeDay(days){
		 if(document.getElementById('startDate')!=null){
     	document.getElementById('startDate').value = getDate(days);
	 }
     if(document.getElementById('endDate')!=null){
     	document.getElementById('endDate').value = getDate(-1);
		 }
   }
  
   /**
* 函数功能 :startDate,endDate滚动n天, n可以为正负数
*/
   function scrollStartDateEndDate(ndays){
	   scrollDateById(ndays,'startDate');
   scrollDateById(ndays,'endDate');
   scrollDateById(ndays,'realTime');
   }
   
   /**
   * 函数功能 :日期滚动n天, n可以为正负数
   */
   function scrollDateById(ndays,elementId) {
   	   var dateElement = document.getElementById(elementId);
	   if(dateElement != null){
	      var oldDate = parseDate(dateElement.value);
	      var newDateNum = oldDate.getTime() + ndays * ( 24 * 3600 * 1000);
	      var newDateString = new Date(newDateNum).format("yyyy-MM-dd");
	      document.getElementById(elementId).value = newDateString;
	   }
   }
   
