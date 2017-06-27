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

<img alt='voorstellingen' src='images/voorstellingen.png'>
<h1>Het Cultuurhuis:voorstellingen</h1>
</div>
<br/>
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
<br/>
<vdab:genres/>
</body>
</html>