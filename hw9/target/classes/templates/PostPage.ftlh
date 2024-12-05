<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <div class="form">
            <div class="body">
                <form method="post" action="">
                    <div class="field" style="margin-bottom: 0rem">
                        <div class="name">
                            <label for="text">Write Comment</label>
                        </div>
                        <div class="value" >
                            <textarea id="text" name="text" style="min-height: 3rem"></textarea>
                        </div>
                    </div>
                    <@c.error "comment.text"/>
                    <div class="button-field" style="margin-bottom: 1rem; margin-top: 0.3rem">
                        <input type="submit" value="Send">
                    </div>
                </form>
            </div>
        </div>
        <span id="comments" style="font-size: 1.15rem; font-weight: bold">
            Comments (${post.comments?size})
        </span>
        <#list post.comments as comment>
            <@c.comment comment=comment/>
        </#list>
    <#else>
        No such post.
    </#if>
</@c.page>