/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Article;
import entity.UserHelper;
import java.util.Date;

/**
 *
 * @author piotrek
 */
public class newComment {
    private UserHelper uh;
    private Logged logged;
    private Article article;
    private Date date;
    private String message;
  
    public newComment(){}

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserHelper getUh() {
        return uh;
    }

    public void setUh(UserHelper uh) {
        this.uh = uh;
    }
    public Logged getLogged() {
        return logged;
    }

    public void setLogged(Logged logged) {
        this.logged = logged;
    }
    public String addComment(){
        uh.addComment(article,logged.getLogin(), message);
        return "news";
    }
}
