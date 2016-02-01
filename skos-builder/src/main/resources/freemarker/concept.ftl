<#include "header.ftl">

<h1>${config.baseUri}${key}<br /><small>skos:Concept</small></h1>

<div class="row">
    <div class="col-md-8">
        <#include "part/scheme.ftl">
        <#include "part/documentation.ftl">
        <#include "part/relation.ftl">
    </div>
    <div class="col-md-4">
        <#include "part/label.ftl">
    </div>
</div>

<#include "footer.ftl">