@import org.example.hexlet.dto.cars.CarPage
@import org.example.hexlet.util.NamedRoutes

@param CarPage page
@param NamedRoutes path

@template.layout.page(
    content = @`
        <main>
            <div>
                <h2>Name: ${page.getCar().getMake()}</h2>
                <h3>Model: ${page.getCar().getModel()}</h3>
            </div>
        </main>
    `
)