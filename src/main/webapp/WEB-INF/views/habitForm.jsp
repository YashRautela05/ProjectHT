<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Habit Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body>
    <h1>${habit == null ? "Create New Habit" : "Edit Habit"}</h1>
    <form action="${pageContext.request.contextPath}/habits" method="post">
        <input type="hidden" name="action" value="${habit == null ? "create" : "update"}">
        <c:if test="${habit != null}">
            <input type="hidden" name="id" value="${habit.id}">
        </c:if>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${habit.name}" required>
        <br>
        <label for="description">Description:</label>
        <textarea id="description" name="description">${habit.description}</textarea>
        <br>
        <input type="submit" value="${habit == null ? "Create" : "Update"}">
    </form>
    <a href="${pageContext.request.contextPath}/habits">Back to Habits List</a>
</body>
</html>