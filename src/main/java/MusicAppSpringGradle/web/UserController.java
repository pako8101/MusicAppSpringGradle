package MusicAppSpringGradle.web;

import MusicAppSpringGradle.models.bindings.UserRegistrationBindingModel;
import MusicAppSpringGradle.models.service.UserRegistrationServiceModel;
import MusicAppSpringGradle.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }
@ModelAttribute ("registrationBindingModel")
UserRegistrationBindingModel userRegistrationBindingModel(){
        return new UserRegistrationBindingModel();
}
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerAndLogin(@Valid UserRegistrationBindingModel registrationBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registrationBindingModel",registrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationBindingModel",
                    bindingResult);
            return "redirect:/users/register";
        }

      UserRegistrationServiceModel userRegistrationServiceModel =
modelMapper.map(registrationBindingModel, UserRegistrationServiceModel.class);

      userService.registerAndLogin(userRegistrationServiceModel);

      return "redirect:/home";
    }



    @PostMapping("/users/login-error")
    public ModelAndView failedLogin(@ModelAttribute
                                                (UsernamePasswordAuthenticationFilter
                                                        .SPRING_SECURITY_FORM_USERNAME_KEY)String username){

        ModelAndView modelAndView = new ModelAndView();

         modelAndView.addObject("bad_credentials",true);
        modelAndView.addObject("username",username);

        modelAndView.setViewName("/login");

        return modelAndView;

    }
//    @GetMapping("/logout")
//    public String logout(HttpSession httpSession){
//        httpSession.invalidate();
//        return "redirect:/";
//
//    }
}
