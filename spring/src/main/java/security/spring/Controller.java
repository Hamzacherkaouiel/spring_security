package security.spring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    List<Student> students=new ArrayList<>();

    @GetMapping
    public List<Student> hello(){
        return students;
    }
    @GetMapping("/csrf")
    public CsrfToken getcsrf(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("students")
    public Student add(@RequestBody Student student){
        this.students.add(student);
        return student;
    }
}
