/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author wroble
 */
import entity.Article;
import entity.Comment;
import entity.User;
import entity.UserHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


public class Logged {
    private String login;
    private String password;
    private String mail;
    private Date date;
    private int rule;
    private Character sex;
    private UserHelper uh;
    private User u;
    private boolean logged=false;
    private boolean admin=false;
    private boolean moderator=false;
    private boolean user=false;
    private boolean exist=false;
    private boolean notExist=false;
    //manageUser
    private List<Comment> comments;
    private List<Article> articles;
  
    public Logged(){}
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
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
    public boolean isLogged(){
        return logged;
    }
    public void setLogged(boolean logged){
        this.logged=logged;
    }
    public UserHelper getUh() {
        return uh;
    }
    public void setUh(UserHelper uh) {
        this.uh = uh;
    }
    public void setSex(Character sex){
        this.sex=sex;
    }
    public Character getSex(){
        return sex;
    }
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }
    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
    public boolean isNotExist() {
        return notExist;
    }

    public void setNotExist(boolean notExist) {
        this.notExist = notExist;
    }
    //do sprawdzenia, wywala nullpointer przy dobrych wartościach
    public void checkLogin(String val){
        exist=false;
        notExist=true;
        List<User> users=uh.getAllUser();
        for(User u:users){
            if(u.getLogin().equals(val)){exist=true;notExist=false;}
        }
        if(val.equals("admin")){exist=true;notExist=false;}
    }
 
    public String author(){
        checkLogin(this.getLogin());
        if(exist){
        String pass=uh.getUserPassword(this.getLogin());
        int rola=uh.getUserRule(this.getLogin());
        if(this.getPassword().equals(pass)){
            this.startSession();
            logged=true;
            if(rola==0)user=true;
            else if(rola==1)moderator=true;
            else admin=true;
            exist=false;
            return "news";
        }else{
            login="";password="";
        }
        return null;
        }else return null;
    }
    public void startSession(){
        u=uh.getUserByLogin(login);
        sex=u.getSex();
        mail=u.getMail();
        date=u.getDate();
        rule=u.getRule();
    }
    public String reset(){
        u=null;
        sex=null;
        login="";
        password="";
        logged=false;moderator=false;admin=false;
        mail="";
        date=null;
        rule=0;
        return "news";
    }
    public String deleteUser(){
        uh.deleteUser(u);
        u=null;
        reset();
        return "news";
    }
    public String saveChange(){
        u.setDate(date);
        u.setLogin(login);
        u.setMail(mail);
        u.setPassword(password);
        u.setRule(rule);
        u.setSex(sex);
        uh.saveChanges(u);
        return "changeData";
    }
    public String registry0(){
        checkLogin(login);
        if(exist)return null;
        rule=0;
        uh.addUser(login, password, mail, rule,date,sex);
        return "logowanie";
    }
    public String registryA(String login, String password,String mail, int rule, Date date, Character sex){
        uh.addUser(login, password, mail, rule,date,sex);
        return "addUser";
    }
    public List<User> getAllUser(){
        return uh.getAllUser();
    }
    //manageUser method
    public String editComments(){
        FacesContext fc=FacesContext.getCurrentInstance();
        String param=fc.getExternalContext().getRequestParameterMap().get("userID");
        int p=Integer.parseInt(param);
        this.setComments(uh.getAllCommentsByUser(p));
        return "editComment";
    }
    public String editArticles(){
        FacesContext fc=FacesContext.getCurrentInstance();
        String param=fc.getExternalContext().getRequestParameterMap().get("userID");
        int p=Integer.parseInt(param);
        this.setArticles(uh.getAllArticleByUser(p));
        return "editArticle";
    }
    public String deleteComment(){
        List<Comment> com=new ArrayList<Comment>();
        for(Comment c: comments){
            if(c.isDelete())com.add(c);
        }
        uh.deleteComments(com);
        return "manageUser";
    }public String updateComment(){
        List<Comment> com=new ArrayList<Comment>();
        for(Comment c: comments){
            if(c.isMarked())com.add(c);
        }
        uh.updateComments(com);
        return "manageUser";
    }
    public String updateArticle(){
        List<Article> art=new ArrayList<Article>();
        for(Article c: articles){
            if(c.isMarked())art.add(c);
        }
        uh.updateArticles(art);
        return "manageUser";
    }
    public String deleteArticle(){
        List<Article> art=new ArrayList<Article>();
        for(Article c: articles){
            if(c.isDelete())art.add(c);
        }
        uh.deleteArticles(art);
        return "manageUser";
    }
}
