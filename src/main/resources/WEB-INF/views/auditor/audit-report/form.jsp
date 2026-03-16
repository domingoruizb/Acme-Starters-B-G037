<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:form-textbox code="auditor.audit-report.form.label.ticker" path="ticker" placeholder="auditor.audit-report.form.placeholder.ticker"/>
	<acme:form-textbox code="auditor.audit-report.form.label.name" path="name"/>
	<acme:form-moment code="auditor.audit-report.form.label.startmoment" path="startMoment"/>
	<acme:form-moment code="auditor.audit-report.form.label.endmoment" path="endMoment"/>
	<acme:form-url code="auditor.audit-report.form.label.moreinfo" path="moreInfo"/>
	<acme:form-textarea code="auditor.audit-report.form.label.description" path="description"/>
	<acme:form-double code="auditor.audit-report.form.label.monthsactive" path="monthsActive" readonly="true"/>
	<acme:form-double code="auditor.audit-report.form.label.hours" path="hours" readonly="true"/>

	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="auditor.audit-report.form.button.audit-sections" action="/auditor/audit-section/list?audit-reportId=${id}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="auditor.audit-report.form.button.audit-sections" action="/auditor/audit-section/list?audit-reportId=${id}"/>
			<acme:submit code="auditor.audit-report.form.button.update" action="/auditor/audit-report/update"/>
			<acme:submit code="auditor.audit-report.form.button.delete" action="/auditor/audit-report/delete"/>
			<acme:submit code="auditor.audit-report.form.button.publish" action="/auditor/audit-report/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.audit-report.form.button.create" action="/auditor/audit-report/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
