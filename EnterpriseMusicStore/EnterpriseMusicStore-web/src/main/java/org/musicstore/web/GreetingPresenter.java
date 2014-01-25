package org.musicstore.web;

import org.musicstore.Greeter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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

