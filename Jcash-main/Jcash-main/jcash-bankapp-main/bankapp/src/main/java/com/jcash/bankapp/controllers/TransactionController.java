package com.jcash.bankapp.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcash.bankapp.model.User;
import com.jcash.bankapp.service.UserService;

@Controller
public class TransactionController {

    @Autowired
    private UserService userService;

    private String getBalanceFilePath(String username) {
        return "data/" + username + "_balance.dat";
    }

    private String getHistoryFilePath(String username) {
        return "data/" + username + "_history.dat";
    }

    private double loadBalance(String username) {
        try (DataInputStream in = new DataInputStream(new FileInputStream(getBalanceFilePath(username)))) {
            return in.readDouble();
        } catch (IOException e) {
            return 0.0; 
        }
    }

    private void saveBalance(String username, double balance) {
        try {
            new File("data").mkdirs(); 
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream(getBalanceFilePath(username)))) {
                out.writeDouble(balance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> loadHistory(String username) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(getHistoryFilePath(username)))) {
            return (List<String>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>(); 
        }
    }

    private void saveHistory(String username, List<String> history) {
        try {
            new File("data").mkdirs(); 
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getHistoryFilePath(username)))) {
                out.writeObject(history);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/deposit")
    public String showDeposit() {
        return "deposit";
    }

    @PostMapping("/deposit")
    public String doDeposit(@RequestParam double amount, Model model) {
        User user = userService.getCurrentUser(); 
        String username = user.getUsername();

        double balance = loadBalance(username);
        balance += amount; 
        saveBalance(username, balance); 

        List<String> history = loadHistory(username);
        String log = "Deposited ₱" + amount + " on " + LocalDateTime.now();
        history.add(log); 
        saveHistory(username, history);

        model.addAttribute("message", log);
        model.addAttribute("balance", balance);
        model.addAttribute("user", user); 
        return "receipt";
    }

   
    @GetMapping("/withdraw")
    public String showWithdraw() {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String doWithdraw(@RequestParam double amount, Model model) {
        User user = userService.getCurrentUser();
        String username = user.getUsername();

        double balance = loadBalance(username);
        if (amount > balance) {
            model.addAttribute("message", "❌ Insufficient balance.");
        } else {
            balance -= amount;
            saveBalance(username, balance);

            List<String> history = loadHistory(username);
            String log = "Withdrew ₱" + amount + " on " + LocalDateTime.now();
            history.add(log);
            saveHistory(username, history);

            model.addAttribute("message", log);
        }

        model.addAttribute("balance", balance);
        model.addAttribute("user", user);
        return "receipt";
    }

    @GetMapping("/cashin")
    public String showCashIn() {
        return "cashin";
    }

    @PostMapping("/cashin")
    public String doCashIn(@RequestParam double amount, Model model) {
        User user = userService.getCurrentUser();
        String username = user.getUsername();

        double balance = loadBalance(username);
        balance += amount;
        saveBalance(username, balance);

        List<String> history = loadHistory(username);
        String log = "Cashed In ₱" + amount + " on " + LocalDateTime.now();
        history.add(log);
        saveHistory(username, history);

        model.addAttribute("message", log);
        model.addAttribute("balance", balance);
        model.addAttribute("user", user);
        return "receipt";
    }

    @GetMapping("/send")
    public String showSend() {
        return "send";
    }

    @PostMapping("/send")
    public String doSend(@RequestParam double amount,
                         @RequestParam String recipient,
                         Model model) {
        User user = userService.getCurrentUser();
        String username = user.getUsername();

        double balance = loadBalance(username);
        if (amount > balance) {
            model.addAttribute("message", "❌ Insufficient balance.");
        } else {
            balance -= amount;
            saveBalance(username, balance);

            List<String> history = loadHistory(username);
            String log = "Sent ₱" + amount + " to " + recipient + " on " + LocalDateTime.now();
            history.add(log);
            saveHistory(username, history);

            model.addAttribute("message", log);
        }

        model.addAttribute("balance", balance);
        model.addAttribute("user", user);
        return "receipt";
    }

    @GetMapping("/transaction")
    public String showHistory(Model model) {
        User user = userService.getCurrentUser();
        String username = user.getUsername();

        List<String> history = loadHistory(username);
        model.addAttribute("transaction", history);
        return "transaction";
    }
}
