<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.inventor.form.label.fullname" path="identity.fullName"/>
	<acme:form-textbox code="any.inventor.form.label.bio" path="bio"/>
	<acme:form-textbox code="any.inventor.form.label.key-words" path="keyWords"/>
	<acme:form-textbox code="any.inventor.form.label.licensed" path="licensed"/>
</acme:form>