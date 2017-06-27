<%@tag description='menu' pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<h2>Genres</h2>
<div>
<ul class='voorstellingen'>
<c:forEach var='genres' items='${genres}'>
<li class='voorstellingen'><c:url value='/voorstellingen.htm' var='detailURL'>
<c:param name='id' value='${genres.id}'/>
</c:url>
<a href='${detailURL}'>${genres.naam}</a></li>
</c:forEach>
</ul>
</div>