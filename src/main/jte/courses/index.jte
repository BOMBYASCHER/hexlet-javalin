@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.util.NamedRoutes

@param CoursesPage page
@param NamedRoutes path

@template.layout.page(
    page = page,
    content = @`
        <main class="mx-4">
            <h1>${page.getHeader()}</h1>
            <div class="my-5">
                <form action="${path.newCoursePath()}" method="get">
                    <button class="btn btn-primary">Add new course</button>
                </form>
            </div>
            <div>
                <h3>Search courses</h3>
                <div class="navbar input-group md-3">
                    <form action="${path.coursesPath()}" method="get" class="d-flex" >
                        <input class="form-control" name="term" type="search" value="${page.getTerm()}">
                        <button class="mx-2 btn btn-primary" type="submit">Search</button>
                    </form>
                </div>
            </div>
            @if(page.getCourses().isEmpty())
                <p>No courses added yet.</p>
            @else
                <div class="my-4">
                @for(var course : page.getCourses())
                    <div class="col-sm-6 mt-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${course.getName()}</h5>
                                <p class="card-text">${course.getDescription()}</p>
                                <a href="${path.coursePath(course.getId())}" class="btn btn-primary">Go to course</a>
                            </div>
                        </div>
                    </div>
                @endfor
                </div>
            @endif
        </main>
    `
)
