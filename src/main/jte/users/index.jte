@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.model.User

@param UsersPage page

@template.layout.page(
    content = @`
        <main class="mx-4">
            <h1>Users</h1>
            <div class="my-5">
                <form action="/users/new" method="get">
                    <button class="btn btn-primary">Registration</button>
                </form>
            </div>
            @if(page.getUsers().isEmpty())
                <h2>No added users yet.</h2>
            @else
                <div>
                @for(User user : page.getUsers())
                    <div class="my-4">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">${user.getName()}</h4>
                                <p class="card-text">${user.getEmail()}</p>
                            </div>
                        </div>
                    </div>
                @endfor
                </div>
            @endif
        </main>
    `
)