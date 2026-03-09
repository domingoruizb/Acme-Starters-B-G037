<%@page language="java"%>

<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column path="ticker" code="any.strategy.list.label.ticker"/>
	<acme:list-column path="name" code="any.strategy.list.label.name"/>
	<acme:list-column path="startMoment" code="any.strategy.list.label.start"/>
	<acme:list-column path="endMoment" code="any.strategy.list.label.end"/>
</acme:list>