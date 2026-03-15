<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.sponsorship.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="any.sponsorship.form.label.name" path="name"/>
	<acme:form-moment code="any.sponsorship.form.label.startmoment" path="startMoment"/>
	<acme:form-moment code="any.sponsorship.form.label.endmoment" path="endMoment"/>
	<acme:form-textbox code="any.sponsorship.form.label.sponsor" path="sponsor.identity.fullName"/>
	<acme:form-url code="any.sponsorship.form.label.moreinfo" path="moreInfo"/>
	<acme:form-textarea code="any.sponsorship.form.label.description" path="description"/>
<%-- 	<acme:form-checkbox code="any.sponsorship.form.label.draftmode" path="draftMode"/> --%>
	
	<acme:button code="any.sponsorship.form.button.donations" action="/any/donation/list?sponsorshipId=${id}"/>
	<acme:button code="any.sponsorship.form.button.sponsor" action="/any/sponsor/show?sponsorshipId=${id}"/>
</acme:form>