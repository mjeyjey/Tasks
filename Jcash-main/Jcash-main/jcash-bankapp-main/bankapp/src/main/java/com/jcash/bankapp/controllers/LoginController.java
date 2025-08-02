
package com.jcash.bankapp.controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcash.bankapp.model.User;
import com.jcash.bankapp.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            userService.login(username, password); 
            model.addAttribute("user", user);

            if (user.isAdmin()) {
                return "redirect:/admin_dashboard";
            } else {
                return "redirect:/dashboard";
            }
        }

        return "redirect:/?error"; 
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/";

        if (user.isAdmin()) {
            return "redirect:/admin_dashboard"; 
        }

        double balance = loadBalance(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("balance", balance);
        return "dashboard";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        if (userService.registerUser(username, password)) {
            model.addAttribute("success", " Registration successful. You may now log in.");
        } else {
            model.addAttribute("error", " Username already taken. Try another one.");
        }
        return "register";
    }


    @GetMapping("/settings")
    public String showSettings(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings/rename")
    public String renameUsername(@RequestParam String newUsername, Model model) {
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/";

        boolean success = userService.changeUsername(user.getUsername(), newUsername); 

        if (success) {
            model.addAttribute("success", "Username changed successfully.");
        } else {
            model.addAttribute("error", "Failed to change username. Username might already exist.");
        }

        model.addAttribute("user", userService.getCurrentUser());
        return "settings";
    }

    private double loadBalance(String username) {
        File file = new File("data/" + username + "_balance.dat");
        if (!file.exists()) return 0.0;
        try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
            return in.readDouble();
        } catch (IOException e) {
            return 0.0;
        }
    }
}
