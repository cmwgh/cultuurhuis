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

<img alt='voorstellingen' src='images/reserveren.png'>
<h1>Het Cultuurhuis:reserveren</h1>
</div>

<a href='index.htm'>
Voorstellingen
</a>

<dl><dt>Voorstelling:</dt><dd>${voorstelling.titel}</dd>
<dt>Uitvoerders:</dt><dd>${voorstelling.uitvoerders}</dd>
<dt>Datum:</dt><dd>${voorstelling.datum}</dd>
<dt>Prijs:</dt><dd>&euro; ${voorstelling.prijs}</dd>
<dt>Vrije plaatsen:</dt><dd>${voorstelling.vrijeplaatsen}</dd>
</dl>
<form method='post' id='reserverenform'>
<label>Plaatsen:<c:if test='${not empty fout}'><span>Tik een getal tussen 1 en ${voorstelling.vrijeplaatsen}</span></c:if></label>
<input name='plaatsen' type='number' min='1' max='${voorstelling.vrijeplaatsen}' value='${plaatsen}' required>
<input name='id' type='hidden' value='${voorstelling.id}'>
<!-- <input type='text' value='${sessionScope.mandje}'> -->
<input type='submit' value='Reserveren' id='reserveknop' name='reserveren'>
</form>

<script>
document.getElementById('reserverenform').onsubmit = function(){
	if ( ! navigator.cookieEnabled) {
		alert("Dit werkt enkel als cookies aanstaan");
		return false;
	}
	document.getElementById('reserveknop').disabled=true;
};
</script>

</body>
</html>