<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice form-box">
        <div class="header">Create notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="text">Enter text:</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="5"></textarea>
                    </div>
                    <@c.error "noticeText.text"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>
</@c.page>
