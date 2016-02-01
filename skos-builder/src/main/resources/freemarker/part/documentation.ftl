<#if !object.documentation.isEmpty()>
    <h2>Documentation</h2>

    <#if object.documentation.definition?has_content>
        <h3>Definition</h3>

        <ul class="list-unstyled">
            <#list object.documentation.definition?sort as value>
                <li><strong>${value.language}</strong> ${value.value}</li>
            </#list>
        </ul>
    </#if>
</#if>