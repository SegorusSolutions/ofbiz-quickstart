<#import "/template.ftl" as t>
<@t.page>

    <div id="status">
        Loading...
    </div>

    <script>
        function reloadStatus() {
            fetch("/setup/install/status", {})
                .then((response) => response.text())
                .then((html) => {
                    document.getElementById("status").innerHTML = html;
                })
                .catch((error) => {
                    console.warn(error);
                });
            setTimeout(reloadStatus, 1000);
        }

        reloadStatus();
    </script>
</@t.page>