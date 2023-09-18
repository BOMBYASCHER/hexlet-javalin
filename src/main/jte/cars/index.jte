@import org.example.hexlet.dto.cars.CarsPage
@import org.example.hexlet.util.NamedRoutes

@param CarsPage page
@param NamedRoutes path

@template.layout.page(
    page = page,
    content = @`
        <main>
            <div class="my-5">
                <form action="${path.newCarPath()}" method="get">
                    <button class="btn btn-primary">Add new car</button>
                        </form>
                    </div>
            @if(page.getCars().isEmpty())
                <h2>No added cars yet.</h2>
            @else
                @for(var car : page.getCars())
                    <div>
                        <h2>
                            <a href="${path.carPath(car.getId())}">${car.getName()}</a>
                        </h2>
                    </div>
                @endfor
            @endif
        </main>
    `
)