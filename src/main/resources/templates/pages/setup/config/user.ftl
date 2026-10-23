<#import "/template.ftl" as t>
<@t.page>

    <@t.section title="User">
        <form action="/setup/config/user" method="post">
            <div>Name:</div>
            <input type="text" name="name" value="${(user.name)!""}"/>
<#--            <br/><br/>-->
<#--            <div>Password:</div>-->
<#--            <input type="password" name="password"/>-->
<#--            <input type="password" name="confirmPassword"/>-->
            <br/><br/>

            <div class="card-option-container">
                <button type="submit" value="Submit" class="card-option">
                    <div class="card-option-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m15 11l4 4l-4 4m4-4H8a4 4 0 0 1 0-8h1"/></svg>
                    </div>
                    <div class="card-option-body">
                        <div class="card-option-title">
                            Next
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </@t.section>

</@t.page>