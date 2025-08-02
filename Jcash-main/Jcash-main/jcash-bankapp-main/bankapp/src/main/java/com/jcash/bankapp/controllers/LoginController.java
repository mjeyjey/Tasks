// package com.jcash.bankapp.controllers;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import jakarta.servlet.http.HttpSession;


// @Controller
// public class LoginController {

//       private double balance = 0;
//         private List<String> history = new ArrayList<>();


    
//     @GetMapping("/")
//     public String showLoginPage(@RequestParam(required = false) String error, Model model) {
//         if (error != null) {
//             model.addAttribute("loginError", "Invalid username or password");
//         }
//         return "login"; // hahanapin ang login.html sa /templates
//     }

//     @PostMapping("/login")
//     public String handleLogin(@RequestParam String username,
//                               @RequestParam String password,
//                               Model model,
//                               HttpSession session) {
//         // Dummy check (walang database)
//         if ("akoadmin".equals(username) && "1234".equals(password)) {
//             model.addAttribute("username", username);
//             return "dashboard";
//         }
//             //
//               if ("admin".equals(username) && "4321".equals(password)) {
//             model.addAttribute("username", username);
//             return "dashboard";

//         } else {
//             return "redirect:/?error"; //babalik sa login if wrong cridentials
//         } 

//     }
    
  
//     @GetMapping("/dashboard")
//     public String dashboard(Model model, HttpSession session) {
//         String username = (String) session.getAttribute("username");
//         model.addAttribute("username", username);
//         model.addAttribute("balance", balance);
//         return "dashboard";
//     }

//     @GetMapping("/deposit")
//     public String showDeposit() {
//         return "deposit";
//     }

//     @PostMapping("/deposit")
//     public String doDeposit(@RequestParam double amount, Model model) {
//         balance += amount;
//         String log = "Deposited ₱" + amount + " on " + LocalDateTime.now();
//         history.add(log);
//         model.addAttribute("message", log);
//         model.addAttribute("balance", balance);
//         return "receipt";
//     }

//     @GetMapping("/withdraw")
//     public String showWithdraw() {
//         return "withdraw";
//     }

//     @PostMapping("/withdraw")
//     public String doWithdraw(@RequestParam double amount, Model model) {
//         if (amount > balance) {
//             model.addAttribute("message", "❌ Insufficient balance.");
//         } else {
//             balance -= amount;
//             String log = "Withdrew ₱" + amount + " on " + LocalDateTime.now();
//             history.add(log);
//             model.addAttribute("message", log);
//         }
//         model.addAttribute("balance", balance);
//         return "receipt";
//     }

//     @GetMapping("/cashin")
//     public String showCashIn() {
//         return "cashin";
//     }

//     @PostMapping("/cashin")
//     public String doCashIn(@RequestParam double amount, Model model) {
//         balance += amount;
//         String log = "Cashed In ₱" + amount + " on " + LocalDateTime.now();
//         history.add(log);
//         model.addAttribute("message", log);
//         model.addAttribute("balance", balance);
//         return "receipt";
//     }

//     @GetMapping("/send")
//     public String showSend() {
//         return "send";
//     }

//     @PostMapping("/send")
//     public String doSend(@RequestParam double amount,
//                         @RequestParam String recipient,
//                         Model model) {
//         if (amount > balance) {
//             model.addAttribute("message", "❌ Insufficient balance.");
//         } else {
//             balance -= amount;
//             String log = "Sent ₱" + amount + " to " + recipient + " on " + LocalDateTime.now();
//             history.add(log);
//             model.addAttribute("message", log);
//         }
//         model.addAttribute("balance", balance);
//         return "receipt";
//     }

//     @GetMapping("/history")
//     public String showHistory(Model model) {
//         model.addAttribute("history", history);
//         model.addAttribute("balance", balance);
//         model.addAttribute("username", "admin"); // or kukuhain sa session if needed
//         return "history";
//     }

// }


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

    // Show login page
    @GetMapping("/")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            userService.login(username, password); // Save logged in user
            model.addAttribute("user", user);

            if (user.isAdmin()) {
                return "redirect:/admin_dashboard";
            } else {
                return "redirect:/dashboard";
            }
        }

        return "redirect:/?error"; // Invalid login
    }

    // Logout and redirect to login page
    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    // Show dashboard for logged-in user
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/";

        if (user.isAdmin()) {
            return "redirect:/admin_dashboard"; // hide the users dashboard sa admin ui
        }

        double balance = loadBalance(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("balance", balance);
        return "dashboard";
    }

    // register new user
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


    // settings page
    @GetMapping("/settings")
    public String showSettings(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);
        return "settings";
    }

    // Handle username change
    @PostMapping("/settings/rename")
    public String renameUsername(@RequestParam String newUsername, Model model) {
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/";

        boolean success = userService.changeUsername(user.getUsername(), newUsername);  // the error is here .changeUsername

        if (success) {
            model.addAttribute("success", "Username changed successfully.");
        } else {
            model.addAttribute("error", "Failed to change username. Username might already exist.");
        }

        model.addAttribute("user", userService.getCurrentUser());
        return "settings";
    }

    // Load balance sa user-specific file
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
