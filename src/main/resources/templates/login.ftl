<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>
    <#if RequestParameters["logout"]??>
        <p>
            You have been logged out.
        </p>
    </#if>
    <#if RequestParameters["error"]?? >
        <p>Invalid username and password.!!</p>
    </#if>
    <form action="/login" method="post">
        用户名: <label>
        <input type="text" name="username">
    </label>
        密码: <label>
        <input type="password" name="password">
    </label>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>