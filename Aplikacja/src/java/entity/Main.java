/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Locale;
import javax.faces.context.FacesContext;

/**
 *
 * @author piotrek
 */
public class Main {
    public Main(){}
    
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
}
