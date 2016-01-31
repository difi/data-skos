<#include "header.ftl">

<h1>${config.root}${key}<br /><small>skos:Concept</small></h1>

<#include "part/labels.ftl">

<#if object.broader?? || object.narrower?? || object.broaderTransitive?? || object.narrowerTransitive??>
<h2>Relations</h2>

<#if object.broader?? || object.narrower??>
<div class="row">
    <div class="col-sm-6">
        <h3>Broader</h3>

        <#if object.broader??>
        <ul class="list-unstyled">
        <#list object.broader?sort as foreign>
            <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
        </#list>
        </ul>
        <#else>
        <em>None</em>
        </#if>
    </div>
    <div class="col-sm-6">
        <h3>Narrower</h3>

        <#if object.narrower??>
        <ul class="list-unstyled">
        <#list object.narrower?sort as foreign>
            <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
        </#list>
        </ul>
        <#else>
        <em>None</em>
        </#if>
    </div>
</div>
</#if>

<#if object.broaderTransitive?? || object.narrowerTransitive??>
<div class="row">
    <div class="col-sm-6">
        <h3>Broader Transitive</h3>

    <#if object.broaderTransitive??>
        <ul class="list-unstyled">
            <#list object.broaderTransitive?sort as foreign>
                <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
            </#list>
        </ul>
    <#else>
        <em>None</em>
    </#if>
    </div>
    <div class="col-sm-6">
        <h3>Narrower Transitive</h3>

    <#if object.narrowerTransitive??>
        <ul class="list-unstyled">
            <#list object.narrowerTransitive?sort as foreign>
                <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
            </#list>
        </ul>
    <#else>
        <em>None</em>
    </#if>
    </div>
</div>
</#if>
</#if>

<#include "footer.ftl">