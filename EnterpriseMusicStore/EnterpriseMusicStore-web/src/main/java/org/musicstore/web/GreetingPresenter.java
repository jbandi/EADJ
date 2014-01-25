package org.musicstore.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.musicstore.Greeter;

@Named
@RequestScoped
public class GreetingPresenter {

    @EJB Greeter greeter;

    public String getJsfMessage(){
        return "Greetings from JSF!";
    }

    public String getEjbMessage(){
        return greeter.getMessage();
    }
}

