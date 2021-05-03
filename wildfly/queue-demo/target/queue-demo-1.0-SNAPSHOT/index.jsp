<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Java Web App Demo</title>
</head>
<body>
<h1>Hello, I am a Java web app!</h1>
<p>I am running from a WAR artifact.</p>

<h3>Some info</h3>
<form action="ProcessInfo" method="post">
    <label>Name </label>
    <input type="text" name="name"><br>
    <input type="submit" value="Send">
</form>


Select a file to upload: <br />
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" value="Upload File"/>
</form>


</body>
</html>