<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/common_head.jsp" %>
	<script type="text/javascript">
		$(function(){
			$("a.deleteClass").click(function(){
				return confirm("你确定要删除?" + $(this).parent().parent().find("td:first").text());
			});


			//校验边界，一般服务器端也要校验防止从地址栏直接进入
			$("#searchPageBtn").click(function(){
				var pageNo = $("#pn_input").val();
				var pageTotal = ${requestScope.page.pageTotal};
				if(pageNo > 0 && pageTotal >= pageNo){
					location.href = "${pageScope.basePath}manager/bookServlet?callMethodName=page&pageNo=" + pageNo;
				}else{
					alert("输入0-" + pageTotal);
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>

			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>


			<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<!--一般如果jsp无法得到数据，一般不要直接跳转，而是经过一次servlet，以隔离每一次的request -->
				<td><a href="manager/bookServlet?callMethodName=getBook&id=${book.id}">修改</a></td>
				<td><a class="deleteClass" href="manager/bookServlet?callMethodName=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
			</tr>
			</c:forEach>


			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>

		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>