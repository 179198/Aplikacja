/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import entity.Article;
import entity.Comment;
import entity.UserHelper;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author piotrek
 */
public class Main {
    UserHelper uh;
    Article art;
    List<Comment> comments;
 
    public Main(){}
    
    public Article getArt() {
        return art;
    }
    public void setArt(Article art) {
        this.art = art;
    }
    
    public String changeLocalePl(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("pl"));
        return null;
    }
    public String changeLocaleEn(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.ENGLISH);
        return null;
    }
    public List<Article> getAllArticles(){
        return uh.getAllArticle();
    }
    public Article oneArticle(){
        FacesContext fc=FacesContext.getCurrentInstance();
        Map<String,String> params=fc.getExternalContext().getRequestParameterMap();
        int id=Integer.parseInt(params.get("id"));
        return uh.getArticleById(id);
    }
    public String shorter(String text){
        return text.substring(0, 200)+"...";
    }
    public String one(){
        art=oneArticle();
        return "more";
    }
    public UserHelper getUh() {
        return uh;
    }

    public void setUh(UserHelper uh) {
        this.uh = uh;
    }
    public List<Comment> getComments() {
        return uh.getAllCommentsByArticle(art.getArticleId());
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}