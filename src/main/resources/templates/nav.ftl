<#macro nav>
    <nav class="nav-bar">
        <#assign classes = ["step"]>
        <#if "/setup" == currentStep >
            <#assign classes = classes + ["selected"]>
        </#if>
        <div class="${classes?join(' ')}">
            <a href="/setup">Quickstart</a>
        </div>
        <#list steps as step>
            <#assign classes = ["step"]>
            <#if step.path == currentStep >
                <#assign classes = classes + ["selected"]>
            </#if>
            <div class="${classes?join(' ')}">
                <a href="${step.path}">
                    <#if !step.ready>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" color="orange"><path fill="currentColor" d="M19 2a3 3 0 0 1 2.995 2.824L22 5v14a3 3 0 0 1-2.824 2.995L19 22H5a3 3 0 0 1-2.995-2.824L2 19V5a3 3 0 0 1 2.824-2.995L5 2zm-6.99 13l-.127.007a1 1 0 0 0 0 1.986L12 17l.127-.007a1 1 0 0 0 0-1.986zM12 7a1 1 0 0 0-.993.883L11 8v4l.007.117a1 1 0 0 0 1.986 0L13 12V8l-.007-.117A1 1 0 0 0 12 7"/></svg>
                    <#else>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m5 12l5 5L20 7"/></svg>
                    </#if>
                    ${step.name}
                </a>
            </div>
        </#list>
        <#assign classes = ["step"]>
        <#if "/setup/summary" == currentStep >
            <#assign classes = classes + ["selected"]>
        </#if>
        <div class="${classes?join(' ')}">
            <a href="/setup/summary">Summary</a>
        </div>
    </nav>
</#macro>