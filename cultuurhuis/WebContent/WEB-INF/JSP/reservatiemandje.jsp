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
<div class='recht'>
<h1>Het Cultuurhuis:reservatiemandje</h1>
<img class='recht' alt='voorstellingen' src='images/voorstellingen.png'>
</div>
<h2>Genres</h2>
idsSet: <c:out value='${idsSet}'/><br/>
mandjeMap: <c:out value='${mandjeMap}'/>
<table>
<form method='post'>
<tbody>
		<tr class="titel"><td>Datum</td><td>Titel</td><td>Uitvoerders</td><td>Prijs</td><td>Plaatsen</td><td><input type='submit' value='Verwijderen' id='verwijderenKnop' name='verwijderen'></td></tr>
		<c:set var='total' value='0'/>
		<c:forEach var='voorstellingen' items='${voorstelling}'>
		<tr>
		<td>${voorstellingen.datum}</td>
		<td><c:out value='${voorstellingen.titel}'/></td>
		<td><c:out value="${voorstellingen.uitvoerders}"/></td>
		<td>&euro; <c:out value='${voorstellingen.prijs}'/></td>
		<td>
			<c:forEach var='plaatsNodig' items='${mandjeMap}'>
				<c:if test='${voorstellingen.id eq plaatsNodig.key}'>
					<c:out value='${plaatsNodig.value}'/>
					<c:set var='subtotal' value='${plaatsNodig.value * voorstellingen.prijs}' />
				</c:if>
			</c:forEach>
		</td>
<%-- 		<td><c:out value='${voorstellingen.vrijeplaatsen}'/></td> --%>
 		<td><!--//reserveren -->
 		<input type='checkbox' name='id' value='${voorstellingen.id}'>
		
		</td>		
		</tr>
		<c:set var='total' value='${total + subtotal}'/>
		</c:forEach>

</tbody>
</form>
</table>
<span>Te betalen: &euro;<c:out value='${total}' /></span>
</body>
</html>