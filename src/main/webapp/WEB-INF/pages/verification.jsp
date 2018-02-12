<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
</head>
<body>

<%--<c:if test = "${phone != null}">--%>
    <%--<form:form action="addsms" method="post" modelAttribute="phone">--%>
        <%--<table>--%>
            <%--<tr>--%>
                <%--<th>phone</th>--%>
                <%--<td>--%>
                    <%--<form:input path="phone" />--%>
                    <%--<form:errors path="phone" cssClass="error" />--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><button type="submit">Submit</button></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form:form>--%>
<%--</c:if>--%>

<%--<c:if test = "${newPhone != null}">--%>
    <%--<<button type="button">You already have phone, you can change it.</button>--%>
<%--</c:if>--%>

<div id="addSms">
    <input type="text" name="phone" value="79998882222">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="submit" onclick="addSms()">
</div>

<div id="checkCode">
    <input type="text" name="code" value="2234">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="submit" onclick="checkCode()">
</div>

<script>
    var xhr = new XMLHttpRequest();
    function addSms(){
        var body = createBody("addSms");
        postRequest("addsms", body).onload = function() {
            processingSms(JSON.parse(this.responseText));
        }
    }

    function checkCode(){
        var body = createBody("checkCode");
        postRequest("checkcode", body).onload = function() {
            processingSms(JSON.parse(this.responseText));
        }
    }

    function postRequest(link, body){
        xhr.open("POST", '/'+link, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        console.log(body)
        xhr.send(body);
        return xhr;
    }

    function createBody(id){
        var elem = document.getElementById(id).childNodes;
        var body = "";
        console.log(elem);
        for (var i = 0; i < elem.length; i++) {
            if (elem[i].value)
                body+=elem[i].name+"="+elem[i].value+"&";
        }
        return body;
    }

    function processingSms(ans){
        console.log(ans);
        if (ans.result == 'false')
            alert(ans.description);
    }
</script>



<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
    <input type="submit" value="Exit">
</form>

</body>
</html>