package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

import java.util.List;

@Controller
public class SignupController {
    @Autowired
    private SignupRepository signupRepository;

    private DatabaseManager databaseManager = new DatabaseManager();

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        databaseManager.insertSignup(name, address);
        return "done";
    }

    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    public String loadSignups(Model model) {
        List<Signup> signups = databaseManager.getSignups();
        model.addAttribute("signups", signups);
        return "/signups";
    }
}
