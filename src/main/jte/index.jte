@import org.example.hexlet.dto.MainPage

@param MainPage page

@template.layout.page(
    content = @`
        <main>
            <div class="col-lg-8 mx-auto p-4 py-md-5">
                <h1 class="text-body-emphasis">Hello Hexlet!</h1>
                <p>Javalin + jte</p>
            </div>
            @if(!page.isVisited())
                <p>This page has been visited for the first time.</p>
            @endif
        </main>
    `
)
