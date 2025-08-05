package com.jcash.bankapp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jcash.bankapp.model.User;

@Service
public class UserService {

    private static final String DATA_FILE = "users.dat";
    private Map<String, User> users = new HashMap<>();
    private User currentUser;

    public UserService() {
        loadUsers();
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            System.out.println(" No user data found or corrupted. Loading default users...");
            initializeDefaultUsers();
            saveUsers();
        }
    }

    private void initializeDefaultUsers() {
        users.clear();
        users.put("admin", new User("admin", "admin123", true));
        users.put("michael", new User("michael", "pass123", false));
        users.put("maria", new User("maria", "pass123", false));
        users.put("pedro", new User("pedro", "pass123", false));
      
        
    }

    // save user data to file
    public void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public Map<String, User> getAllUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password, false));
        saveUsers();
        return true;
    }

    public boolean changeUsername(String oldUsername, String newUsername) {
        if (!users.containsKey(oldUsername) || users.containsKey(newUsername)) {
            return false; 
        }

        User user = users.remove(oldUsername);
        user.setUsername(newUsername);
        users.put(newUsername, user);

        renameFile("data/" + oldUsername + "_balance.dat", "data/" + newUsername + "_balance.dat");
        renameFile("data/" + oldUsername + "_history.dat", "data/" + newUsername + "_history.dat");

        if (currentUser != null && currentUser.getUsername().equals(oldUsername)) {
            currentUser = user;
        }

        saveUsers();
        return true;
    }

    //  change password
    public boolean changePassword(String username, String newPassword) {
        User user = users.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            saveUsers();
            return true;
        }
        return false;
    }

    private void renameFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        if (oldFile.exists()) {
            boolean success = oldFile.renameTo(newFile);
            if (!success) {
                System.out.println(" Failed to rename: " + oldPath + " → " + newPath);
            }
        }
    }
}
