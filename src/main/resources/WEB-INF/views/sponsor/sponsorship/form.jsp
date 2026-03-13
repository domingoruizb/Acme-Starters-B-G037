<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="sponsor.sponsorship.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="sponsor.sponsorship.form.label.name" path="name"/>
	<acme:form-moment code="sponsor.sponsorship.form.label.startmoment" path="startMoment"/>
	<acme:form-moment code="sponsor.sponsorship.form.label.endmoment" path="endMoment"/>
	<acme:form-textbox code="sponsor.sponsorship.form.label.sponsor" path="sponsor.identity.fullName"/>
	<acme:form-url code="sponsor.sponsorship.form.label.moreinfo" path="moreInfo"/>
	<acme:form-textarea code="sponsor.sponsorship.form.label.description" path="description"/>
	<acme:form-double code="sponsor.sponsorship.form.label.monthsactive" path="monthsActive"/>
	<acme:form-money code="sponsor.sponsorship.form.label.totalmoney" path="totalMoney"/>
<%-- 	<acme:form-checkbox code="any.sponsorship.form.label.draftmode" path="draftMode"/> --%>
</acme:form>