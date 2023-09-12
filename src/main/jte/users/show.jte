@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <h1>${page.getUser().getName()}</h1>
            <p>${page.getUser().getEmail()}</p>
        </div>
    `
)