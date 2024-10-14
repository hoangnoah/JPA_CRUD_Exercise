<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Videos</title>
</head>
<body>
	<div>
	  <form action="<c:url value="/admin/videos"/>" method="get">
	    <input name="search" id="search" placeholder="Title...">
	  </form>
	</div>
	<a href="<c:url value="/admin/video/add"/>">Add Video</a>
	<br>

	<hr>
	<table border="1" width="100%">
		<tr>
			<th>Id</th>
			<th>Poster</th>
			<th>Title</th>
			<th>Category</th>
			<th>Description</th>
			<th>Views</th>
			<th>Status</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${videos}" var="video" varStatus="STT">
			<tr>
				<td>${video.videoId }</td>
				
				<c:if test="${video.poster.substring(0,5)=='https'}">
					<c:url value="${video.poster }" var="imgUrl"></c:url>
				</c:if>
				<c:if test="${video.poster.substring(0,5)!='https'}">
					<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>
				</c:if>

				<td><img height="150" width="200" src="${imgUrl}" /></td>
				<td>${video.title }</td>
				<td>${video.category.categoryname }</td>
				<td>${video.description }</td>
				<td>${video.views }</td>
				<td>
					<c:if test="${video.active==1 }">Hoạt động</c:if>
					<c:if test="${video.active!=1 }">Khóa</c:if>
				</td>
				<td><a
					href="<c:url value='/admin/video/edit?id=${video.videoId }'/>">Sửa</a>
					| <a
					href="<c:url value='/admin/video/delete?id=${video.videoId }'/>">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>