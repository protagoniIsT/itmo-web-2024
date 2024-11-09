<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user false/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <#assign activity = "" />
                    <#if currUri??>
                        <#if item.href == currUri>
                            <#assign activity = "active" />
                        </#if>
                    </#if>
                    <li class="${activity}">
                        <a href="${item.href}">${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <section>
                <div class="header">Post #${post.id}</div>
                <div class="body">
                    <#assign text = post.text!/>
                    <#if 350 < text?length>
                        ${text?substring(0, 350)}...
                    <#else>
                        ${text}
                    </#if>
                </div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2024-2024 &copy; by Konstantin Gordienko
    </footer>
</#macro>

<#macro userlink user nameOnly>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly == true>
        <a href="/user?handle=${user.handle}" style="${stylizeUser(user)}" f>${user.name}</a>
    <#else>
        <a href="/user?handle=${user.handle}" f>${user.name}</a>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function stylizeUser user>
    <#switch user.color>
        <#case "RED">
            <#assign color = "red"/>
            <#break>
        <#case "GREEN">
            <#assign color = "green"/>
            <#break>
        <#case "BLUE">
            <#assign color = "blue"/>
            <#break>
    </#switch>

    <#assign style = "color: ${color};
           font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
           font-weight: bold;
           text-decoration: none"/>

    <#return style>
</#function>

<#macro postdisplay post>
    <article>
        <#if post_id??>
            <#assign post = findBy(posts, "id", post_id)!/>
        </#if>
        <#assign u = findBy(users, "id", post.userId)!/>
        <div class="title">${post.title}</div>
        <div class="information">By <@userlink u true/>, 2 days ago, translation
        </div>
        <div class="body">
            <#if post_id?? || user_id??>
                <p>${post.text}</p>
            <#else>
                <#if 350 < post.text?length>
                    ${post.text?substring(0, 350)}...
                <#else>
                    ${post.text}
                </#if>
            </#if>
        </div>
    </article>
</#macro>
