package MusicAppSpringGradle.web;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
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
