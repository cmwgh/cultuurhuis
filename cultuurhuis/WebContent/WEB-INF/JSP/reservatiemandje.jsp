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
<h1>Het Cultuurhuis:reservatiemandje</h1>
</div>

<ul class='voorstellingen'>
	<li class='voorstellingen'>
		<a href='index.htm'>
		Voorstellingen
		</a>
	</li>
	<li class='voorstellingen'>
		<a href='inloggen.htm'>
		Bevestiging reservatie
		</a>
	</li>
</ul>
<%-- idsSet: <c:out value='${idsSet}'/><br/> --%>
<%-- mandjeMap: <c:out value='${mandjeMap}'/> --%>
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