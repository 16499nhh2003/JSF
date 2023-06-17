/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.bean;

import com.dht.pojo.User;
import com.dht.service.UserService;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

/**
 *
 * @author Huy Hòa Nguyễn
 */
@ManagedBean
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    private static UserService userService = new UserService();

    private String name;
    private String email;
    private String username;
    private String password;
    @Transient
    private String confirmPassword;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public String register() {
        if (!this.password.isEmpty() && this.getPassword().equals(this.getConfirmPassword())) {
            User u = new User();

            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);
            u.setUsername(username);

            if (userService.addUser(u) == true) {
                return "login?faces-redirect=true";
            }
        }
        return "register";
    }

    public String login() {
        User u = userService.login(username, password);
        if (u != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
            return "index?faces-redirect=true";
        }
        return "login";
    }
    
    public String checkLogin(){
         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null ){
            return "index?faces-redirect=true"; 
         }
         return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        return "login?faces-redirect=true";
    }

    /**
     * @return the userService
     */
    public static UserService getUserService() {
        return userService;
    }

    /**
     * @param aUserService the userService to set
     */
    public static void setUserService(UserService aUserService) {
        userService = aUserService;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
