@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

@template.layout.page(
    content = @`
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <h1>${page.getCourse().getName()}</h1>
            <p>${page.getCourse().getDescription()}</p>
        </div>
    `
)