<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="inventor.invention.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="inventor.invention.list.label.name" path="name" width="30%"/>
	<acme:list-column code="inventor.invention.list.label.startmoment" path="startMoment" width="20%"/>
	<acme:list-column code="inventor.invention.list.label.endmoment" path="endMoment" width="20%"/>
	<acme:list-column code="inventor.invention.list.label.draftmode" path="draftMode" width="10%"/>
	<acme:list-hidden path="description"/>
</acme:list>

<acme:button code="inventor.invention.list.button.create" action="/inventor/invention/create"/>