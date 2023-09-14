@import org.example.hexlet.dto.MainPage
@import org.example.hexlet.util.NamedRoutes

@param NamedRoutes path
@param MainPage page

@template.layout.page(
    content = @`
        <main>
            <form action="${NamedRoutes.sessionsPath()}" method="post">
                <input type="text" placeholder="Nickname" name="nickname" />
                <input type="password" placeholder="Password" name="password" />
                <input type="submit" />
            </form>
        </main>
    `
)