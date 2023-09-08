@import org.example.hexlet.dto.users.BuildUserPage
@import org.example.hexlet.util.NamedRoutes

@param BuildUserPage page
@param NamedRoutes path

@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif
        <main class="d-flex justify-content-center py-4 bg-body-tertiary">
            <div class="d-block bg-body-secondary p-4 py-md-5">
                <div>
                    <div class="modal-header p-5 pb-4 border-bottom-0">
                        <h1 class="fw-bold mb-0 fs-2">Sign up</h1>
                    </div>
                </div>
                <div class="modal-body p-5 pt-0">
                    <form action="${path.usersPath()}" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control rounded-3" value="${page.getName()}" required name="name" id="floatingInput" placeholder="name">
                            <label for="floatingInput">Name</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control rounded-3" value="${page.getEmail()}" required name="email" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput">Email address</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control rounded-3" required name="password" id="floatingPassword" placeholder="Password">
                            <label for="floatingPassword">Password</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control rounded-3" required name="passwordConfirmation" id="floatingPassword" placeholder="Confirm password">
                            <label for="floatingPassword">Confirm password</label>
                        </div>
                        <button class="w-100 mb-2 btn btn-lg rounded-3 btn-primary" type="submit">Sign up</button>
                        <small class="text-body-secondary">By clicking Sign up, you agree to the terms of use.</small>
                    </form>
                </div>
            </div>
        </main>
    `
)