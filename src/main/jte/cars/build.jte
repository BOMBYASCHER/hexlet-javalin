@import org.example.hexlet.dto.cars.BuildCarPage
@import org.example.hexlet.util.NamedRoutes

@param BuildCarPage page
@param NamedRoutes path

@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <div class="alert alert-danger" role="alert">
                        ${error.getMessage()}
                    </div>
                @endfor
            @endfor
        @endif
        <main class="d-flex align-items-center py-4 bg-body-tertiary">
            <div class="form-signin m-auto">
                <form action="${path.carsPath()}" method="post">
                    <h1 class="h3 mb-3 fw-normal">Add new car</h1>
                        <div class="form-floating">
                            <input type="text" class="form-control" value="${page.getMake()}" required name="make" id="floatingInput" placeholder="make">
                            <label for="floatingInput">Make</label>
                        </div>
                        <div class="form-floating">
                            <input type="text" class="form-control" value="${page.getModel()}" required name="model" id="floatingInput" placeholder="model">
                            <label for="floatingInput">Model</label>
                        </div>
                        <button class="btn btn-primary mt-4 w-100 py-2" type="submit">Create</button>
                        <p class="mt-5 mb-3 text-muted">© 2023</p>
                </form>
            </div>
        </main>
    `
)