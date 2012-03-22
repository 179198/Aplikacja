/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author wroble
 */
public class UserHelper {
    Session session=null;
    Transaction trans=null;
    
    public UserHelper(){
        this.session= HibernateUtil.getSessionFactory().getCurrentSession();
    }
    public void addUser(String login, String password, String mail,int rule,Date date,Character sex){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            User user=new User(login,password,mail,rule,date,sex);
            session.save(user);
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
    }
    public String getUserPassword(String login){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        User user=null;
        try{
            trans=session.beginTransaction();
            String query="from User where login='"+login+"'";
            Query q=session.createQuery(query);
            user=(User)q.uniqueResult();
        }catch(HibernateException e){
            trans.rollback();
        }
        return user.getPassword();
    }
    public int getUserRule(String login){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        User user=null;
        try{
            trans=session.beginTransaction();
            String query="from User where login='"+login+"'";
            Query q=session.createQuery(query);
            user=(User)q.uniqueResult();
        }catch(HibernateException e){
            trans.rollback();
        }
        return user.getRule();
    }
    public User getUserByLogin(String login){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        User user=null;
        try{
            trans=session.beginTransaction();
            String query="from User where login='"+login+"'";
            Query q=session.createQuery(query);
            user=(User)q.uniqueResult();
        }catch(HibernateException e){
            trans.rollback();
        }
        return user;
    }
    public void deleteUser(User u){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
        trans=session.beginTransaction();
        session.delete(u);
        trans.commit();
        }catch(HibernateException w){
            trans.rollback();
        }
    }
    public void saveChanges(User u){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
        trans=session.beginTransaction();
        session.update(u);
        trans.commit();
        }catch(HibernateException w){
            trans.rollback();
        }
    }
    public void addArticle(User user, Date date, String name, String article){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            Article art=new Article(user,date,name,article);
            session.save(art);
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
    }
    public List getAllArticle(){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        List articles=null;
        try{
            trans=session.beginTransaction();
            Query q=session.createQuery("from Article order by date desc");
            articles=q.list();
        }catch(HibernateException e){
            trans.rollback();
        }
        return articles;
    }
    public Article getArticleById(int id){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        Article art=null;
        try{
            trans=session.beginTransaction();
            String query="from Article where articleID='"+id+"'";
            Query q=session.createQuery(query);
            art=(Article)q.uniqueResult();
        }catch(HibernateException e){
            trans.rollback();
        }
        return art;
    }
    public List<Comment> getAllCommentsByArticle(int id){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        List<Comment> comments=null;
        Article art=null;
        try{
            trans=session.beginTransaction();
            String query="from Comment where articleID='"+id+"'";
            Query q=session.createQuery(query);
            comments=(List<Comment>)q.list();
            
        }catch(HibernateException e){
            trans.rollback();
        }
        return comments;
    }
    public void addComment(Article art,String login,String message){
        User u=getUserByLogin(login);
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            Comment comment=new Comment(u,art,new Date(),message);
            session.save(comment);
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
    }
    public List<User> getAllUser(){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        List<User> users=null;
        try{
            trans=session.beginTransaction();
            String query="from User where login!='admin'";
            Query q=session.createQuery(query);
            users=q.list();
        }catch(HibernateException e){
            trans.rollback();
        }
        return users;
    }
    public List<Comment> getAllCommentsByUser(int userID){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        List<Comment> comments=null;
        try{
            trans=session.beginTransaction();
            String query="from Comment where userID='"+userID+"'";
            Query q=session.createQuery(query);
            comments=q.list();
        }catch(HibernateException e){
            trans.rollback();
        }
        return comments;
    }
    public List<Article> getAllArticleByUser(int userID){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        List<Article> articles=null;
        try{
            trans=session.beginTransaction();
            String query="from Article where userID='"+userID+"'";
            Query q=session.createQuery(query);
            articles=q.list();
        }catch(HibernateException e){
            trans.rollback();
        }
        return articles;
    }
    public void deleteComments(List<Comment> list){
        if(list.size()>0){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            for(Comment o: list){
            session.delete(o);
            }
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
        }
    }
    public void updateComments(List<Comment> list){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            for(Comment o: list){
            session.update(o);
            }
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
    }
    public void deleteArticles(List<Article> list){
        if(list.size()>0){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            for(Article o: list){
                for(Comment c: o.getComments()){
                    session.delete(c);
                }
            session.delete(o);
            }
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
        }
    }
    public void updateArticles(List<Article> list){
        if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
        try{
            trans=session.beginTransaction();
            for(Article o: list){
            session.update(o);
            }
            trans.commit();
        }catch(HibernateException e){
            trans.rollback();
        }
    }
    
    
}
