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
<img class='recht' alt='voorstellingen' src='images/mandje.png'>
<h1>Het Cultuurhuis:overzicht</h1>
</div>

<ul class='voorstellingen'>
	<li class='voorstellingen'>
		<a href='index.htm'>
		Voorstellingen
		</a>
	</li>
</ul>
<br/>
<h2>Mislukte reserveringen</h2>
<br/>
<%-- idsSet: <c:out value='${idsSet}'/><br/> --%>
<%-- mandjeMap: <c:out value='${mandjeMap}'/> --%>
<table>
<form method='post'>
<tbody>
		<tr class="titel"><td>Datum</td><td>Titel</td><td>Uitvoerders</td><td>Prijs(&euro;)</td><td>Plaatsen</td></tr>
		<c:set var='total' value='0'/>
		<c:forEach var='voorstellingenGelukte' items='${voorstellingGelukte}'>
		<tr>
		<td>${voorstellingenGelukte.datum}</td>
		<td><c:out value='${voorstellingenGelukte.titel}'/></td>
		<td><c:out value="${voorstellingenGelukte.uitvoerders}"/></td>
		<td> <c:out value='${voorstellingenGelukte.prijs}'/></td>
		<td>
			<c:forEach var='plaatsNodig' items='${mandjeMap}'>
				<c:if test='${voorstellingenGelukte.id eq plaatsNodig.key}'>
					<c:out value='${plaatsNodig.value}'/>
				</c:if>
			</c:forEach>
		</td>
<%-- 		<td><c:out value='${voorstellingen.vrijeplaatsen}'/></td> --%>
 			
		</tr>
		</c:forEach>

</tbody>
</form>
</table>
<br/>
<c:if test='${not empty mislukte}'>
<h2>Mislukte reserveringen</h2>
<table>
<tbody>
		<tr class="titel"><td>Datum</td><td>Titel</td><td>Uitvoerders</td><td>Prijs(&euro;)</td><td>Plaatsen</td><td>Vrije plaatsen</td></tr>
		<c:set var='total' value='0'/>
		<c:forEach var='mislukten' items='${mislukte}'>
		<tr>
		<td><c:out value='${mislukten.datum}'/></td>
		<td><c:out value='${mislukten.titel}'/></td>
		<td><c:out value='${mislukten.uitvoerders}'/></td>
		<td><c:out value='${mislukten.prijs}'/></td>
		<td>
		<c:forEach var='plaatsNodig' items='${mandjeMislukteMap}'>
				<c:if test='${mislukten.id eq plaatsNodig.key}'>
					<c:out value='${plaatsNodig.value}'/>
				</c:if>
		</c:forEach>
		</td>
		<td><c:out value='${mislukten.vrijeplaatsen}'/></td>
		
		</tr>
		</c:forEach>

</tbody>
</table>
</c:if>
<br/>
<%-- <span>Test1: <c:out value='${test1}' /></span> --%>
<%-- <span>mandje: <c:out value='${mandjeMap}' /></span> --%>
</body>
</html>