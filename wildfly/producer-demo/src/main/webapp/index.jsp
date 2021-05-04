<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

Select a file to upload: <br />
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="Upload File"/>
</form>

</body>
</html>