@import org.example.hexlet.dto.MainPage
@import org.example.hexlet.util.NamedRoutes

@param MainPage page
@param NamedRoutes path

@template.layout.page(
    content = @`
        <main>
            <div class="col-lg-8 mx-auto p-4 py-md-5">
                <h1 class="text-body-emphasis">Hello Hexlet!</h1>
                <p>Javalin + jte</p>
            </div>
            <div class="my-5">
                <form action="${path.buildSessionPath()}" method="get">
                    <button class="btn btn-primary">Login</button>
                </form>
            </div>
            @if(!page.isVisited())
                <p>This page has been visited for the first time.</p>
            @endif
            @if(page.getCurrentUser() != null)
                <p>Welcome, ${page.getCurrentUser()}.</p>
                <p>To log out delete cookie JSESSIONID from browser</p>
            @endif
        </main>
    `
)
