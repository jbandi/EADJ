package org.musicstore.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.musicstore.Greeter;

@ManagedBean @RequestScoped
public class GreetingPresenter {

    @EJB Greeter greeter;

    public String getJsfMessage(){
        return "Greetings from JSF!";
    }

    public String getEjbMessage(){
        return greeter.getMessage();
    }
}

