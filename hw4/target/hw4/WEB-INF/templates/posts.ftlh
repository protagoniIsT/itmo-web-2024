<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <article>
        <#if !user_id??>
            No such user
        <#else>
            <#assign posts_exist = false/>
            <#list posts as post>
                <#if post.userId == user_id>
                    <#assign posts_exist = true/>
                    <@c.postdisplay post/>
                </#if>
            </#list>
            <#if !posts_exist>
                No posts yet.
            </#if>
        </#if>
    </article>
</@c.page>
