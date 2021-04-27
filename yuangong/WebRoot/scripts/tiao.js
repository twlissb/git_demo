/**
 * 
 */

//JavaScript代码区域
layui.use('element', function() {
	var element = layui.element;

});

function shu(url,id,js,uid) {
	 $(".sss").load(url+"&uid="+uid+"&&id="+id,function(){
         var sc =  document.createElement("script");
         sc.src= js; //是你数据表格的js
         $(".layui-body").append(sc);
      })
	
}


