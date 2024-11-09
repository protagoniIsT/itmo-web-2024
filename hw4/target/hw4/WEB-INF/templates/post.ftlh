<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
<article>
    <#assign post = c.findBy(posts, "id", post_id)!/>
    <@c.postdisplay post/>
</article>
</@c.page>
