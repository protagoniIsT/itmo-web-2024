<#-- @ftlvariable name="user_id" type="java.lang.String" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>

    <#if handle??>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#elseif user_id??>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>

    <#if u?has_content>
        <div class="user-profile">
            <div class="user-details">
                <h2>
                    <span class="stylized-user-name" style="${c.stylizeUser(u)}">${u.name}</span>
                </h2>
                <div class="info">
                    <ul>
                        <li>id: ${u.id}</li>
                        <li>handle: ${u.handle} </li>
                        <li>name: ${u.name}</li>
                        <#assign postscnt = cntPosts(posts, u)/>
                        <li>
                            <a href="/posts?user_id=${u.id}"> Posts: ${postscnt}</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="avatar">
                <img src="/img/no-title.jpg" alt="${u.name}" />
            </div>
        </div>

        <#assign prevHandle = moveToUser(users, u.handle, "prev")!>
        <#assign nextHandle = moveToUser(users, u.handle, "next")!>

        <div class="arrows">
            <#if prevHandle != "@#NO_DATA@#">
                <a href="/user?handle=${prevHandle}">←</a>
            <#else>
                <span class="inactive-link">←</span>
            </#if>
            <#if nextHandle != "@#NO_DATA@#">
                <a href="/user?handle=${nextHandle}">→</a>
            <#else>
                <span class="inactive-link">→</span>
            </#if>
        </div>
    <#else>
        No such user
    </#if>

    <#function moveToUser users currUserHandle mode>
        <#list users as usr>
            <#if usr.handle == currUserHandle>
                <#if mode == "next" && usr?index < users?size - 1>
                    <#return users[usr?index + 1].handle />
                </#if>
                <#if mode == "prev" && 0 < usr?index>
                    <#return users[usr?index - 1].handle />
                </#if>
            </#if>
        </#list>
        <#return "@#NO_DATA@#">
    </#function>

    <#function cntPosts posts user>
        <#assign postscnt = 0/>
        <#list posts as post>
            <#if post.userId == user.id>
                <#assign postscnt = postscnt + 1/>
            </#if>
        </#list>
        <#return postscnt />
    </#function>

</@c.page>
