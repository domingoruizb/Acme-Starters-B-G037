<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.audit-report.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="any.audit-report.form.label.name" path="name"/>
	<acme:form-moment code="any.audit-report.form.label.startmoment" path="startMoment"/>
	<acme:form-moment code="any.audit-report.form.label.endmoment" path="endMoment"/>
	<acme:form-textbox code="any.audit-report.form.label.auditor" path="auditor.identity.fullName"/>
	<acme:form-url code="any.audit-report.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="any.audit-report.form.label.description" path="description"/>
	
	<acme:button code="any.audit-report.form.button.audit-sections" action="/any/audit-section/list?auditReportId=${id}"/>
	<acme:button code="any.audit-report.form.button.auditor" action="/any/auditor/show?auditReportId=${id}"/>
</acme:form>