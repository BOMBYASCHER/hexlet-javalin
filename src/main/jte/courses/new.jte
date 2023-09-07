@template.layout.page(
    content = @`
        <main class="d-flex align-items-center py-4 bg-body-tertiary">
            <div class="form-signin m-auto">
                <form action="/courses" method="post">
                    <h1 class="h3 mb-3 fw-normal">Add new course</h1>
                    <div class="form-floating">
                        <input type="text" class="form-control" required name="name" id="floatingInput" placeholder="name">
                        <label for="floatingInput">Name</label>
                    </div>
                    <div class="form-floating">
                        <input type="text" class="form-control" required name="description" id="floatingInput" placeholder="description">
                        <label for="floatingInput">Description</label>
                    </div>
                    <button class="btn btn-primary mt-4 w-100 py-2" type="submit">Create</button>
                    <p class="mt-5 mb-3 text-muted">© 2023</p>
                </form>
            </div>
        </main>
    `
)