<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.invention.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="any.invention.form.label.name" path="name"/>
	<acme:form-moment code="any.invention.form.label.startmoment" path="startMoment"/>
	<acme:form-moment code="any.invention.form.label.endmoment" path="endMoment"/>
	<acme:form-textbox code="any.invention.form.label.inventor" path="inventor.identity.fullName"/>
	<acme:form-url code="any.invention.form.label.moreinfo" path="moreInfo"/>
	<acme:form-textarea code="any.invention.form.label.description" path="description"/>
	
	<acme:button code="any.invention.form.button.parts" action="/any/part/list?inventionId=${id}"/>
	<acme:button code="any.invention.form.button.inventor" action="/any/inventor/show?inventionId=${id}"/>
</acme:form>