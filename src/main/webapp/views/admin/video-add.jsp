<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Video</title>
</head>
<body>
	<form action="<c:url value="/admin/video/insert"/>" method="post"
		enctype="multipart/form-data">
		<label for="id">Id*:</label><br> 
		<input type="text" id="id" name="id" required><br>
		
		<label for="title">Title*:</label><br> 
		<input type="text" id="title" name="title" required><br>
		 
		<label for="poster">Link poster:</label><br> 
		<input type="text" id="poster" name="posterLink"><br> 
		
		<label for="u-poster">Upload poster:</label><br> 
		<input type="file" id="u-poster" name="posterUpload"><br>
		
		<label for="des">Description:</label><br> 
		<input type="text" id="des" name="description"><br>
		
		<label for="view">Views:</label><br> 
		<input type="number" id="view" name="view" value=0><br>
		
		<label for="html">Status*</label><br> 
		<input type="radio" id="ston" name="active" value="1"> 
		<label for="css">Hoạt động</label><br> 
		<input type="radio" id="stoff" name="active" value="0">
		<label for="javascript">Khóa</label> <br>

	
		<label for="cars">Category*:</label>
		<select name="categoryid" id="cars">
			<c:forEach items="${categories}" var="cate" varStatus="STT">
				<option value="${cate.categoryId }">${cate.categoryname }</option>
			</c:forEach>
		</select>
		
		<br> <input type="submit" value="Insert">
	</form>
</body>
</html>