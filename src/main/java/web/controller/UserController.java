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
    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getUsersList());
        return "users";
    }
    @GetMapping("/users/new")
    public String createNewUserForm(@ModelAttribute("user") User user){
        return "new_user";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/users/delete")
    public String getRemoveUserForm(@RequestParam("id")int id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "deleteUser";
    }
    @PostMapping("/remove")
    public String deleteUser(@ModelAttribute("user")User user){
        userService.deleteUser(user.getId());
        return "redirect:/users";
    }
    @GetMapping("/users/edit")
    public String getEditUserForm(@RequestParam("id") int id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "edit_user";
    }
    @PostMapping("/update")
    public String editUser(@ModelAttribute("user") User user){
        userService.editUser(user);
        return "redirect:/users";
    }
}
