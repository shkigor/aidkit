<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 13.04.16
  Time: 22:45
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'drug.label', default: 'Drug')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
<div id="list-drug">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="title" title="${message(code: 'drug.title.label', default: 'Title')}" />

            <g:sortableColumn property="titleEn" title="${message(code: 'drug.titleEn.label', default: 'Title En')}" />

            <g:sortableColumn property="quantity" title="${message(code: 'drug.quantity.label', default: 'Quantity')}" />

            <g:sortableColumn property="description" title="${message(code: 'drug.description.label', default: 'Description')}" />

            <g:sortableColumn property="structure" title="${message(code: 'drug.structure.label', default: 'Structure')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${drugInstanceList}" status="i" var="drugInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${drugInstance.id}">${fieldValue(bean: drugInstance, field: "title")}</g:link></td>

                <td>${fieldValue(bean: drugInstance, field: "titleEn")}</td>

                <td>${fieldValue(bean: drugInstance, field: "quantity")}</td>

                <td>${fieldValue(bean: drugInstance, field: "description")}</td>

                <td>${fieldValue(bean: drugInstance, field: "structure")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${drugInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>