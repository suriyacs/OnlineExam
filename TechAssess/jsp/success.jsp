<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4>Admin</h4>
<c:if test="${Message != null}">
    <c:out value="${Message}"/>
</c:if>