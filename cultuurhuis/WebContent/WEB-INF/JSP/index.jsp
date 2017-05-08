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
<div>
<h1>Het Cultuurhuis:voorstellingen</h1>
<img class='recht' alt='voorstellingen' src='images/voorstellingen.png'>
</div>
<h2>Genres</h2>
<ul>
<c:forEach var='genres' items='${genres}'>

<li><c:url value='/voorstellingen.htm' var='detailURL'>
<c:param name='id' value='${genres.id}'/>
</c:url>
<a href='${detailURL}'>${genres.naam}</a></li>
</c:forEach>
</ul>
</body>
</html>