<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="sponsor.sponsorship.form.label.ticker" path="ticker" placeholder="sponsor.sponsorship.form.placeholder.ticker"/>
	<acme:form-textbox code="sponsor.sponsorship.form.label.name" path="name"/>
	<acme:form-moment code="sponsor.sponsorship.form.label.startmoment" path="startMoment"/>
	<acme:form-moment code="sponsor.sponsorship.form.label.endmoment" path="endMoment"/>
	<acme:form-url code="sponsor.sponsorship.form.label.moreinfo" path="moreInfo"/>
	<acme:form-textarea code="sponsor.sponsorship.form.label.description" path="description"/>
	<acme:form-double code="sponsor.sponsorship.form.label.monthsactive" path="monthsActive" readonly="true"/>
	<acme:form-money code="sponsor.sponsorship.form.label.totalmoney" path="totalMoney" readonly="true"/>
<%-- 	<acme:form-checkbox code="any.sponsorship.form.label.draftmode" path="draftMode"/> --%>

	<jstl:choose>
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="sponsor.sponsorship.form.button.donations" action="/sponsor/donation/list?sponsorshipId=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="sponsor.sponsorship.form.button.donations" action="/sponsor/donation/list?sponsorshipId=${id}"/>
			<acme:submit code="sponsor.sponsorship.form.button.update" action="/sponsor/sponsorship/update"/>
			<acme:submit code="sponsor.sponsorship.form.button.delete" action="/sponsor/sponsorship/delete"/>
			<acme:submit code="sponsor.sponsorship.form.button.publish" action="/sponsor/sponsorship/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="sponsor.sponsorship.form.button.create" action="/sponsor/sponsorship/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>