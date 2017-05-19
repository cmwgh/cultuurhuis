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
<h1>Het Cultuurhuis:bevestiging reservaties</h1>
<img class='recht' alt='voorstellingen' src='images/voorstellingen.png'>
</div>
<h2>Stap 1:wie ben je?</h2>
<form method="post">
<label>Gebruikersnaam:<span>${fouten.naam}</span><input name='gebruikersnaam' autofocus
required></label>
<label>Paswoord:<span>${fouen.paswoord}</span><input name='paswoord' autofocus
required type="password"></label>
<input type='submit' value='Zoek me op'>
</form>
<br/>
<form>
<input type='submit' value='Ik ben nieuw'>
</form>
<span>${fouten.klant}

<c:forEach var='klant' items='${klantInfo}'>
${klant.voornaam} ${klant.familienaam} ${klant.straat} ${klant.huisnr} ${klant.postcode} ${klant.gemeente}
</c:forEach>
</span>
<h2>Stap 2:Bevestigen</h2>
<form>
<input type='submit' value='Bevestigen' disabled>
</form>
${pw}
</body>
</html>