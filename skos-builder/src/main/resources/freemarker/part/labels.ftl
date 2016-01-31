<h2>Labels</h2>

<div class="row">
    <div class="col-sm-4">
        <h3>Preferred label</h3>

        <ul class="list-unstyled">
        <#list object.label?keys?sort as language>
            <li><strong>${language}</strong> ${object.label[language]}</li>
        </#list>
        </ul>
    </div>
    <!-- <div class="col-sm-4">
        <h3>Alternative labels</h3>

        <em>None</em>
    </div> -->
    <!-- <div class="col-sm-4">
        <h3>Hidden labels</h3>

        <em>None</em>
    </div> -->
</div>