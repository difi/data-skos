<#if !object.label.isEmpty()>
    <h2>Labels</h2>

    <div class="row">
        <div class="col-sm-4">
            <h3>Preferred label</h3>

            <#if object.label.preferred?has_content>
                <ul class="list-unstyled">
                    <#list object.label.preferred?sort as value>
                        <li><strong>${value.language}</strong> ${value.value}</li>
                    </#list>
                </ul>
            <#else>
                <em>None</em>
            </#if>
        </div>
        <div class="col-sm-4">
            <h3>Alternative label</h3>

            <#if object.label.alternative?has_content>
                <ul class="list-unstyled">
                    <#list object.label.alternative?sort as value>
                        <li><strong>${value.language}</strong> ${value.value}</li>
                    </#list>
                </ul>
            <#else>
                <em>None</em>
            </#if>
        </div>
        <div class="col-sm-4">
            <h3>Hidden label</h3>

            <#if object.label.hidden?has_content>
                <ul class="list-unstyled">
                    <#list object.label.hidden?sort as value>
                        <li><strong>${value.language}</strong> ${value.value}</li>
                    </#list>
                </ul>
            <#else>
                <em>None</em>
            </#if>
        </div>
    </div>
</#if>