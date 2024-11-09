<#import "./common.ftlh" as c>

<@c.page>
    <ul>
<#--            <li>-->
<#--                <#if user?? && user.id == u.id>*</#if>-->
<#--                <@c.userlink u true/>-->
<#--            </li>-->
            <div class="datatable">
                <div class="caption">Users</div>
                <table>
                    <tbody>
                    <#list users as u>
                        <tr>
                            <td>
                                <#if user?? && user.id == u.id>*</#if>
                                <@c.userlink u true/>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

    </ul>
</@c.page>
