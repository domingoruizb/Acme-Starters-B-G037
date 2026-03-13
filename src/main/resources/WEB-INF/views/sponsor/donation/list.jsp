<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="sponsor.donation.list.label.name" path="name" width="25%"/>
	<acme:list-column code="sponsor.donation.list.label.notes" path="notes" width="40%"/>
	<acme:list-column code="sponsor.donation.list.label.money" path="money" width="10%"/>
	<acme:list-column code="sponsor.donation.list.label.kind" path="kind" width="25%"/>
</acme:list>