<%@page contentType='text/html' pageEncoding='UTF-8'%>
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

<img alt='voorstellingen' src='images/bevestig.png'>
<h1>Het Cultuurhuis:bevestiging reservaties</h1>
</div>
<div>

<div>
<ul class='voorstellingen'>
	<li class='voorstellingen'>
		<a href='index.htm'>
		Voorstellingen
		</a>
	</li>
	<c:if test='${not empty mandje}'>
	<li class='voorstellingen'>
		<a href='reservatiemandje.htm'>
		Reservatiemandje
		</a>
	</li>
	</c:if>
</ul>
</div>
<br/>
<h2>Stap 1:wie ben je?</h2>
<form method="post">
<label>Gebruikersnaam:<span>${fouten.naam}</span><input name='gebruikersnaam' autofocus
required <c:if test='${not empty klantInfo}'> value='<c:out value='${klantInfo[0].gebruikersnaam}' />' disabled</c:if> /></label>
<label>Paswoord:<span>${fouen.paswoord}</span><input name='paswoord' autofocus
required type="password" <c:if test='${not empty klantInfo}'>disabled</c:if>></label>
<input type='submit' value='Zoek me op' <c:if test='${not empty klantInfo}'>disabled</c:if>>
</form>
<br/>
<form method="post">
<input type='submit' value='Ik ben nieuw' name='nieuweklant' <c:if test='${not empty klantInfo}'>disabled</c:if>>
</form>
<span>${fouten.klant}

<c:forEach var='klantInf' items='${klantInfo}'>
${klantInf.voornaam} ${klantInf.familienaam} ${klantInf.straat} ${klantInf.huisnr} ${klantInf.postcode} ${klantInf.gemeente}
</c:forEach>
<%-- ${klantInfo.size()} --%>
<%-- ${klantInfo.toString()} --%>
<%-- ${klantInfo[0].gebruikersnaam} --%>

</span>
<h2>Stap 2:Bevestigen</h2>
<form method="post">
<input type='hidden' name='klantId' value='${klantInfo[0].id}'>
<input type='submit' value='Bevestigen' name='bevestigen' <c:if test='${empty klantInfo}'>disabled</c:if>>
</form>
</body>
</html>