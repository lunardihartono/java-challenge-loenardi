package ist.challenge.loenardi.controller;

import ist.challenge.loenardi.model.UsersModel;
import ist.challenge.loenardi.repository.UsersRepository;
import ist.challenge.loenardi.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController{

    private final UsersService usersService;
    private final UsersRepository usersRepository;

    public UsersController(UsersService usersService,
                           UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        Object registeredUser = usersService.registerUser(usersModel.getUsername(), usersModel.getPassword());
        if (registeredUser != null){
            return "success";
        }else return "error/400";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getUsername(),usersModel.getPassword());
        if (authenticated != null){
            model.addAttribute("userLogin", authenticated.getUsername());
            return "dashboard";
        }else {
            return "error/400";
        }
    }
}
