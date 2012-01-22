package org.musicstore.tests.jsfunit;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.jboss.jsfunit.framework.WebClientSpec;
import org.jboss.jsfunit.jsfsession.JSFClientSession;
import org.jboss.jsfunit.jsfsession.JSFServerSession;
import org.jboss.jsfunit.jsfsession.JSFSession;
import org.musicstore.Greeter;
import org.w3c.dom.Element;

public class JsfClientTest extends org.apache.cactus.ServletTestCase {

    public void test_greetings_message() throws NamingException, IOException {
        WebClientSpec wcSpec = new WebClientSpec("/faces/index.xhtml");

        JSFSession jsfSession = new JSFSession(wcSpec);

        // A JSFClientSession emulates the browser and lets you test HTML
        JSFClientSession client = jsfSession.getJSFClientSession();
        Element element = client.getElement("jsfgreeting");
        assertEquals("Greetings from JSF!", element.getTextContent());

        String pageText = client.getPageAsText();
        assertTrue(pageText.contains("Welcome"));

    }
}
