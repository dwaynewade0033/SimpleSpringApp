package simpleSpringApp.hwApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import simpleSpringApp.hwApp.model.User;
import simpleSpringApp.hwApp.service.UserService;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";

    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";

    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";

    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        this.userService.deleteUserById(id);
        return "redirect:/";
    }
}

