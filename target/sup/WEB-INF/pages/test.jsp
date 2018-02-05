<%@ page import="java.util.List" %>
<%@ page import="net.shop.model.region.Country" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
</head>
<body>

<div id="app">
    {{title}}
</div>


<form:form action="/registration" method="post" modelAttribute="user">
    <table>
        <tr>
            <th>username</th>
            <td>
                <form:input path="username" />
                <form:errors path="username" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th>password</th>
            <td>
                <form:input path="password" />
                <form:errors path="password" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th>name</th>
            <td>
                <form:input path="name" />
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th>lastName</th>
            <td>
                <form:input path="lastName" />
                <form:errors path="lastName" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th>phone</th>
            <td>
                <form:input path="phone" />
                <form:errors path="phone" cssClass="error" />
            </td>
        </tr>

        <tr>
            <th>phone code</th>
            <td>
                <form:input path="phoneCode" />
                <form:errors path="phoneCode" cssClass="error" />
            </td>
        </tr>

        <tr>
            <th>city</th>
            <td>
                <form:input path="cityid" />
                <form:errors path="cityid" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td><button type="submit">Submit</button></td>
        </tr>
    </table>
</form:form>


<form action="/addsms" method="post">
    <p><input type="phone" name="phone"></p>
    <p><input type="submit" value="Отправить"></p>
</form>

<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script type="text/javascript" defer>
    <%@include file="js/main.js"%>
</script>

</body>
</html>