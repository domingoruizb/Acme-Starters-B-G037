<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.audit-section.list.label.name" path="name" width="25%"/>
	<acme:list-column code="any.audit-section.list.label.notes" path="notes" width="40%"/>
	<acme:list-column code="any.audit-section.list.label.hours" path="hours" width="10%"/>
	<acme:list-column code="any.audit-section.list.label.kind" path="kind" width="25%"/>
</acme:list>