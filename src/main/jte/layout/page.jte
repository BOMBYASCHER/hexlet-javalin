@import gg.jte.Content
@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.BasePage

@param Content content
@param Content footer = null
@param BasePage page = null

<!doctype html>
<html lang="en">
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
    </head>
    <body>
        <header class="d-flex justify-content-center py-3 mb-auto">
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="${NamedRoutes.usersPath()}" class="nav-link">Users</a></li>
                <li class="nav-item"><a href="${NamedRoutes.coursesPath()}" class="nav-link">Courses</a></li>
                <li class="nav-item"><a href="${NamedRoutes.carsPath()}" class="nav-link">Cars</a></li>
                <li class="nav-item"><a href="${NamedRoutes.myPath()}" class="nav-link">About</a></li>
            </ul>
        </header>
        @if(page != null && page.getFlash() != null)
            <div class="alert alert-success mx-4" role="alert">
                ${page.getFlash()}
            </div>
        @endif
        <div class="content mx-4">
            ${content}
        </div>
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>