package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping()
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getUsersList());
        return "users";
    }
    @GetMapping("/new")
    public String createNewUserForm(Model model){
        model.addAttribute(new User());
        return "new_user";
    }
    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }
    @GetMapping("/deleteUser")
    public String removeUser(@RequestParam(value = "id") int id){
        userService.deleteUser(id);
        return "redirect:/";
    }
    @GetMapping("/updateUser")
    public String getEditUserForm(@RequestParam("id") int id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "edit_user";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user){
        userService.editUser(user);
        return "redirect:/";
    }
}
