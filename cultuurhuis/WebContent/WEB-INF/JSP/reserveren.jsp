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
<h1>Het Cultuurhuis:voorstellingen</h1>
<img class='recht' alt='voorstellingen' src='images/voorstellingen.png'>
</div>
<h2>Genres</h2>

<h1>${pizza.naam}</h1>
<dl><dt>Voorstelling:</dt><dd>${voorstellingen.titel}</dd>
<dt>Uitvoerders:</dt><dd>${voorstellingen.uitvoerders}</dd>
<dt>Datum:</dt><dd>${voorstellingen.datum}</dd>
<dt>Prijs:</dt><dd>&euro; ${voorstellingen.prijs}</dd>
<dt>Vrije plaatsen:</dt><dd>${voorstellingen.vrijeplaatsen}</dd>
</dl>



</body>
</html>