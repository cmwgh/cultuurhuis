<%@page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Het Cultuurhuis'/>
</head>
<body>
<div class='linklijn'>
<img class='recht' alt='voorstellingen' src='images/voorstellingen.png'>
<h1>Het Cultuurhuis:voorstellingen</h1>
</div>
<c:if test='${not empty mandje}'>
<ul class='voorstellingen'>
	<li class='voorstellingen'>
		<a href='reservatiemandje.htm'>
		Reservatiemandje
		</a>
	</li>
	<li class='voorstellingen'>
		<a href='inloggen.htm'>
		Bevestiging reservatie
		</a>
	</li>
</ul>

</c:if>
<div>

</div>
<br />
<vdab:genres/>
<br/>
<div>
<c:forEach var='genresnaam' items='${genres}'>
<c:if  test='${genresnaam.id == voorstelling[0].genreid}'>
<br/>
<h2>
${genresnaam.naam} voorstellingen
</h2>
</c:if>
</c:forEach>
</div>
<table>
<tbody>
		<tr class="titel"><td>Datum</td><td>Titel</td><td>Uitvoerders</td><td>Prijs</td><td>Vrije plaatsen</td><td>Reserveren</td></tr>
		<c:forEach var='voorstellingen' items='${voorstelling}'>
		<tr>
		<td>${voorstellingen.datum}</td>
		<td><c:out value='${voorstellingen.titel}'/></td>
		<td><c:out value="${voorstellingen.uitvoerders}"/></td>
		<td>&euro; <c:out value='${voorstellingen.prijs}'/></td>
		<td><c:out value='${voorstellingen.vrijeplaatsen}'/></td>
 		<td><!--//reserveren -->
 		<c:if test="${voorstellingen.vrijeplaatsen > 1}">
 			<c:url value='/reserveren.htm' var='reserverenURL'>
			<c:param name='id' value='${voorstellingen.id}'/>
			</c:url>
			<a href='${reserverenURL}'>Reserveren</a>
 		</c:if>
<%--  		${voorstellingen.vrijeplaatsen > 1 ? "  --%>
<%--  		<c:url value='/reserveren.htm' var='reserverenURL'>  --%>
<%--  		<c:param name='id' value='${voorstellingen.id}'/>  --%>
<%--  		</c:url> --%>
<%-- 			<a href='${reserverenURL}'>Reserveren</a>  --%>
<%--  		" : " "} --%>
		
		</td>		
		</tr>
		</c:forEach>

</tbody>
</table>
</body>
</html>