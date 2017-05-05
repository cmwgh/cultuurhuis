<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Het Cultuurhuis'/>

</head>
<body>
<h1>TEST</h1>
<ul>
<c:forEach var='genres' items='${genres}'>

<li><c:url value='/genres/genre.htm' var='detailURL'>
<c:param name='id' value='${genres.id}'/>
</c:url>
<a href='${detailURL}'>${genres.naam}</a></li>
</c:forEach>
</ul>
</body>
</html>