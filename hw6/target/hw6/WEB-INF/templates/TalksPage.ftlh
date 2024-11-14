<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="loginById" type="java.util.HashMap" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->


<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="sendMessage"/>
                <div class="field">
                    <div class="name">
                        <label for="text">Message:</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="5"></textarea>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="select">To User:</label>
                    </div>
                    <div class="value">
                        <select id="select" name="select">
                            <#list users as user>
                                <option value="${user.id}">${user.login}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>

    <div class="datatable">
        <div class="caption">Talks</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
                <th>Sent</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as talk>
                <tr>
                    <td>${talk.id}</td>
                    <td>${loginById[talk.sourceUserId?string]}</td>
                    <td>${loginById[talk.targetUserId?string]}</td>
                    <td>${talk.text}</td>
                    <td>${talk.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
