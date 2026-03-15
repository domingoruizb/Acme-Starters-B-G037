<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.campaign.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="any.campaign.list.label.name" path="name" width="30%"/>
	<acme:list-column code="any.campaign.list.label.startmoment" path="startMoment" width="25%"/>
	<acme:list-column code="any.campaign.list.label.endmoment" path="endMoment" width="25%"/>
	<acme:list-hidden path="description"/>
	<acme:list-hidden path="spokesperson.identity.fullName"/> 
</acme:list>