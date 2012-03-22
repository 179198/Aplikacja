/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Date;

/**
 *
 * @author piotrek
 */
public class newUser {
    private String login;
    private String password;
    private String mail;
    private Date date;
    private int rule;
    Character sex;
    private Logged logged;
    
    public newUser(){};

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Logged getLogged() {
        return logged;
    }

    public void setLogged(Logged logged) {
        this.logged = logged;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }
     public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }
    
    public void add(){
        logged.registryA(login, password, mail, rule, date, sex);
        login="";
        password="";
        mail="";
        rule=0;
        date=null;
        sex=null;
    }
}
