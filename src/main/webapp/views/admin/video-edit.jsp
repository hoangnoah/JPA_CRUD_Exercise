<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Video</title>
</head>
<body>
	<form action="<c:url value="/admin/video/update"/>" method="post"
		enctype="multipart/form-data">
		<label for="id">Id*:</label><br> 
		<input type="text" id="id" name="id" value="${video.videoId }" required readonly><br>
		
		<label for="title">Title*:</label><br> 
		<input type="text" id="title" name="title" value="${video.title }" required><br>
		 
		 
		 <c:if test="${video.poster.substring(0,5)=='https'}">
			<c:url value="${video.poster }" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${video.poster.substring(0,5)!='https'}">
			<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>
		</c:if>
		
		<img height="150" width="200" src="${imgUrl}" /><br>
		
		<label for="poster">Link poster:</label><br> 
		<input type="text" id="poster" name="posterLink" value="${video.poster }"><br> 
		
		<label for="u-poster">Upload poster:</label><br> 
		<input type="file" id="u-poster" name="posterUpload"><br>
		
		<label for="des">Description:</label><br> 
		<input type="text" id="des" name="description" value="${video.description }"><br>
		
		<label for="view">Views:</label><br> 
		<input type="number" id="view" name="view" value="${video.views }"><br>
		
		<label for="html">Status*</label><br> 
		<input type="radio" id="ston" name="active" value="1" ${video.active==1?'checked':'' }> 
		<label for="css">Hoạt động</label><br> 
		<input type="radio" id="stoff" name="active" value="0" ${video.active!=1?'checked':'' }>
		<label for="javascript">Khóa</label> <br>

	
		<label for="cars">Category*:</label>
		<select name="categoryid" id="cars">
			<c:forEach items="${categories}" var="cate" varStatus="STT">
				<option value="${cate.categoryId }" ${cate.categoryId==video.category.categoryId?'selected':''} >${cate.categoryname }</option>
			</c:forEach>
		</select>
		
		<br> <input type="submit" value="Update">
	</form>
</body>
</html>