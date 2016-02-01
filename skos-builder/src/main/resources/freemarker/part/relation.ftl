<#if !object.relation.isEmpty()>
    <h2>Relations</h2>

    <#if object.relation.broader?has_content || object.relation.narrower?has_content>
    <div class="row">
        <div class="col-sm-6">
            <h3>Broader</h3>

            <#if object.relation.broader?has_content>
                <ul class="list-unstyled">
                    <#list object.relation.broader?sort as foreign>
                        <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
                    </#list>
                </ul>
            <#else>
                <em>None</em>
            </#if>
        </div>
        <div class="col-sm-6">
            <h3>Narrower</h3>

            <#if object.relation.narrower?has_content>
                <ul class="list-unstyled">
                    <#list object.relation.narrower?sort as foreign>
                        <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
                    </#list>
                </ul>
            <#else>
                <em>None</em>
            </#if>
        </div>
    </div>
    </#if>

    <#if object.relation.broaderTransitive?has_content || object.relation.narrowerTransitive?has_content>
    <div class="row">
        <div class="col-sm-6">
            <h3>Broader Transitive</h3>

            <#if object.relation.broaderTransitive?has_content>
                <ul class="list-unstyled">
                    <#list object.relation.broaderTransitive?sort as foreign>
                        <li><a href="${config.root}${foreign}.html">${config.root}${foreign}</a></li>
                    </#list>
                </ul>
            <#else>
                <em>None</em>
            </#if>
        </div>
        <div class="col-sm-6">
            <h3>Narrower Transitive</h3>

            <#if object.relation.narrowerTransitive?has_content>
                <ul class="list-unstyled">
                    <#list object.relation.narrowerTransitive?sort as foreign>
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