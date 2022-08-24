<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Start page</title>
    <script>
        function sendForm(statOperation) {
            const err = document.getElementById("errorText");
            for (let i = 0; i < document.result.stats.length; i++) {
                const strValue = document.result.stats[i].value;
                const num = new Number(strValue);
                if (strValue === "" || !isInteger(num)) {
                    err.innerHTML = "box " + i + " is not integer";
                    return;
                }
                if (isOut(num)) {
                    err.innerHTML = "box " + i + ": index must be less than " + ${maxValue};
                    return;
                }
            }
            document.result.operation.value = statOperation;
            document.result.submit();

            function isInteger(number) {
                return Math.floor(number) == number;
            }

            function isOut(number) {
                return number < 0 || number >= ${maxValue};
            }
        }

    </script>
</head>
<body>
<p id="errorText">input indices (less than ${ maxValue })</p>
<p></p>
<form name="result" action="<c:url value='/result'/>" onsubmit="return false">

    <c:forEach var="i" begin="0" end="${number - 1}">
        ${i}: <input name="stats" type="number" value="0" min="0" max="${maxValue -1}"/>
        <br/><br/>
    </c:forEach>

    <input type=hidden name="operation" value="no">
    <br/>
    <a href="JavaScript:sendForm('sum')">sum</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('max')">max</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('min')">min</a>&nbsp;&nbsp;
    <a href="JavaScript:sendForm('avg')">avg</a>
</form>
<br/>
<a href="<c:url value='/'/>">Back</a>
</body>
</html>
