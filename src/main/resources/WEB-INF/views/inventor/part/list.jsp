<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="inventor.part.list.label.name" path="name" width="25%"/>
	<acme:list-column code="inventor.part.list.label.description" path="decription" width="40%"/>
	<acme:list-column code="inventor.part.list.label.cost" path="cost" width="10%"/>
	<acme:list-column code="inventor.part.list.label.kind" path="kind" width="25%"/>
</acme:list>

<jstl:if test="${showCreate}">
	<acme:button code="inventor.part.list.button.create" action="/inventor/part/create?inventionId=${inventionId}"/>
</jstl:if>
