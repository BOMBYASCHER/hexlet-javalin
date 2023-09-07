@template.layout.page(
    content = @`
        <main class="px-3">
            <div class="container text-center">
                <p class="display-4">Welcome, Guest.</p>
                <p class="display-6">Thanks for visiting, come again soon!</p>
                <a class="display-6" href="https://github.com/BOMBYASCHER">My GitHub profile</a>
            </div>
        </main>
    `,
    footer = @`
        <footer class="mt-auto py-3 my-4">
            <ul class="nav justify-content-center border-bottom pb-3 mb-3">
                <li class="nav-item"><a href="/users" class="nav-link px-2 text-muted">Users</a></li>
                <li class="nav-item"><a href="/courses" class="nav-link px-2 text-muted">Courses</a></li>
                <li class="nav-item"><a href="/my" class="nav-link px-2 text-muted">About</a></li>
            </ul>
            <p class="text-center text-muted">© 2023 Company, Inc</p>
        </footer>

    `
)