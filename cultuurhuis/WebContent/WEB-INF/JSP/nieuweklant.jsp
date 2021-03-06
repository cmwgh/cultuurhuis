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

<img alt='voorstellingen' src='images/nieuweklant.png'>
<h1>Het Cultuurhuis:nieuwe klant</h1>
</div>


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
	<li class='voorstellingen'>
		<a href='inloggen.htm'>
		Bevestiging reservatie
		</a>
	</li>
	</c:if>
</ul>
</div>
<br/>
<br/>
<div>
<form method="post" id='nieuweklantform'>
<label>voornaam:</label>
<input name='voornaam' value='' required>

<label>Familienaam:</label>
<input name='familienaam' value='' required>

<label>Straat:</label>
<input name='straat' value='' required>

<label>Huisnr.:</label>
<input name='huisnr' value='' required>

<label>Postcode:</label>
<input name='postcode' value='' required>

<label>Gemeente:</label>
<input name='gemeente' value='' required>

<label>Gebruikersnaam:</label>
<input name='gebruikersnaam' value='' required>

<label>Paswoord:</label>
<input name='paswoord' value='' required>

<label>Herhaal paswoord:</label>
<input name='paswoord2' value='' required>

<input type='submit' value='ok' id='okknop' name='ok'>
</form>
</div>



<div id='fouten'>
<c:forEach var='entry' items='${fouten}'>
<span>${entry.value}</span><br/>
</c:forEach>

</div>

<script>
document.getElementById('nieuweklantform').onsubmit = function(){
	if ( ! navigator.cookieEnabled) {
		alert("Dit werkt enkel als cookies aanstaan");
		return false;
	}
	document.getElementById('okknop').disabled=true;
};
</script>
</body>
</html>