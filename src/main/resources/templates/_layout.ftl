<#macro header>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Kotlin Journal</title>
        <style>
            body {
                text-align: center;
                font-family: sans-serif;
                background-color: #22487a;
            }
            .content {
                background-color: #1b305b;
                color: #fff;
                margin: 0 auto;
                width: 50%;
                padding: 20px;
                box-sizing: border-box;
            }
            a {
                color: #4CAF50;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <h1>Kotlin Ktor Journal </h1>
            <p><i>Powered by Ktor & Freemarker!</i></p>
            <hr>
            <#nested>
            <a href="/">Back to the main page</a>
        </div>
    </body>
    </html>
</#macro>