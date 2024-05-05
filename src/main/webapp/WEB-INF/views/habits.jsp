<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Habit Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body>
    <h1>My Habits</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>Streak</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="habit" items="${habits}">
            <tr>
                <td>${habit.name}</td>
                <td>${habit.description}</td>
                <td>${habit.startDate}</td>
                <td>${habit.streak}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/habits?action=edit&id=${habit.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/habits?action=delete&id=${habit.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/habits?action=create">Add New Habit</a>
    <script src="${pageContext.request.contextPath}/static/js/script.js"></script>
</body>
</html>