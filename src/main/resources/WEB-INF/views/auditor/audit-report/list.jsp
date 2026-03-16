<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.audit-report.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="auditor.audit-report.list.label.name" path="name" width="30%"/>
	<acme:list-column code="auditor.audit-report.list.label.startmoment" path="startMoment" width="20%"/>
	<acme:list-column code="auditor.audit-report.list.label.endmoment" path="endMoment" width="20%"/>
	<acme:list-column code="auditor.audit-report.list.label.draftmode" path="draftMode" width="10%"/>
	<acme:list-hidden path="description"/>
	<acme:list-hidden path="auditor.identity.fullName"/> 
</acme:list>

<acme:button code="auditor.audit-report.list.button.create" action="/auditor/audit-report/create"/>
