<div class="status-box">
    <#list stepStates as step>
        <div class="status-item">
            <div class="status-progress-bar">
                <#if step.status == "SCHEDULED">
                <#elseif step.status == "IN_PROGRESS">
                    <#if step.progress == -1>
                        <div class="status-progress-bar-value unknown"></div>
                    <#else>
                        <div class="status-progress-bar-value" style="width:${(step.progress * 100)?string(",##0")}%"></div>
                    </#if>
                <#elseif step.status == "COMPLETE">
                    <div class="status-progress-bar-value" style="width:100%;background-color: green"></div>
                <#elseif step.status == "ERROR">
                    <div class="status-progress-bar-value" style="width:100%;background-color: red"></div>
                </#if>

            </div>
            <div class="status-item-header">
                <#if step.status == "SCHEDULED">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" color="grey"><g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"><path d="M3 12a9 9 0 1 0 18 0a9 9 0 0 0-18 0"/><path d="M12 7v5l3 3"/></g></svg>
                <#elseif step.status == "IN_PROGRESS">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" color="royalblue"><path fill="currentColor" d="M6 4v16a1 1 0 0 0 1.524.852l13-8a1 1 0 0 0 0-1.704l-13-8A1 1 0 0 0 6 4"/></svg>
                <#elseif step.status == "COMPLETE">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" color="green"><path fill="currentColor" d="M17 3.34a10 10 0 1 1-14.995 8.984L2 12l.005-.324A10 10 0 0 1 17 3.34m-1.293 5.953a1 1 0 0 0-1.32-.083l-.094.083L11 12.585l-1.293-1.292l-.094-.083a1 1 0 0 0-1.403 1.403l.083.094l2 2l.094.083a1 1 0 0 0 1.226 0l.094-.083l4-4l.083-.094a1 1 0 0 0-.083-1.32"/></svg>
                <#elseif step.status == "ERROR">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" color="red"><path fill="currentColor" d="M12 2c5.523 0 10 4.477 10 10a10 10 0 0 1-19.995.324L2 12l.004-.28C2.152 6.327 6.57 2 12 2m.01 13l-.127.007a1 1 0 0 0 0 1.986L12 17l.127-.007a1 1 0 0 0 0-1.986zM12 7a1 1 0 0 0-.993.883L11 8v4l.007.117a1 1 0 0 0 1.986 0L13 12V8l-.007-.117A1 1 0 0 0 12 7"/></svg>
                </#if>
                <div>
                    ${step.title}
                    <!-- (${(step.progress * 100)?string(",##0")}%) -->
                </div>
            </div>
            <#if step.details != "">
                <div class="status-item-details">
                    ${step.details}
                </div>
            </#if>
        </div>
    </#list>
</div>