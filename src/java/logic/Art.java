/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author piotrek
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.User;
import entity.UserHelper;
import java.util.Date;

public class Art {
    private User user;
    private UserHelper uh;
    private Logged logged;
    private Date date;
    private String name;
    private String article;
    
    public Art(){
    }
     public Logged getLogged() {
        return logged;
    }

    public void setLogged(Logged logged) {
        this.logged = logged;
    }

    public UserHelper getUh() {
        return uh;
    }

    public void setUh(UserHelper uh) {
        this.uh = uh;
    }
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String addArticle(){
        user=logged.getU();
        date=new Date();
        uh.addArticle(user, date, name, article);
        return "news";
    }
    
    
}
