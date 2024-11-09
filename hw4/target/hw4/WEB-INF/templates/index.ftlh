<#import "./common.ftlh" as c>

<@c.page>
<article>
    <#list posts as post>
        <@c.postdisplay post/>
    </#list>
</article>
</@c.page>
