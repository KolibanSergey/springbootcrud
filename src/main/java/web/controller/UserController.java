package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String allUser(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "all_user";
    }
    @GetMapping(value = "/addUser")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "new_user";
    }
    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping(value = "/updateUser/{id}")
    public String editUser(@PathVariable("id") long id,Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "edit_user";
    }
    @PostMapping(value = "/updateUser/{id}")
    public String updateUser(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping(value = "/removeUser/{id}")
    public String removeUser (@PathVariable("id") long id){
        userService.removeUser(id);
        return "redirect:/";
    }


}
