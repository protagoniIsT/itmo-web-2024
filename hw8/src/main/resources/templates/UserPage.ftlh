<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="id">Id: ${user.id}</div>
        <div class="login">Login: ${user.login}</div>
        <div class="creationTime">Registered at ${user.creationTime}</div>
    <#else>
        No such user.
    </#if>

</@c.page>