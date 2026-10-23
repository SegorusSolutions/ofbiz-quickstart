<#macro page>
    <!DOCTYPE HTML>
    <html lang="en">
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-1">
        <title>Quickstart</title>
        <link href="/style.css" rel="stylesheet" media="screen">
    </head>
    <body>
        <header>
            <h1>Quickstart</h1>
        </header>
        <main>
            <#import "nav.ftl" as nav>
            <@nav.nav/>
            <#nested>
        </main>
    </body>
    </html>
</#macro>
<#macro card title="">
    <div class="card">
        <#if title != "">
            <div class="card-title">
                ${title}
            </div>
        </#if>
        <div class="card-content">
            <#nested>
        </div>
    </div>
</#macro>
<#macro section title="">
    <section class="section">
        <#if title != "">
            <h2 class="section-title">
                ${title}
            </h2>
        </#if>
        <#nested>
    </section>
</#macro>