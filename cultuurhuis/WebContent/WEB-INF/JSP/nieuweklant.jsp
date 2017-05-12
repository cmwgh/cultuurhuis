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
<div>
<h1>Het Cultuurhuis:voorstellingen</h1>
<img class='recht' alt='voorstellingen' src='images/voorstellingen.png'>
</div>
<form method="post" id='nieuweklantform'>
<label>voornaam:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Familienaam:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Straat:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Huisnr.:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Postcode:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Gemeente:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Gebruikersnaam:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Paswoord:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<label>Herhaal paswoord:<span>${fouten.idfout}</span></label>
<input name='plaatsen' value='' required>

<input type='submit' value='ok' id='okknop' name='ok'>
</form>

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