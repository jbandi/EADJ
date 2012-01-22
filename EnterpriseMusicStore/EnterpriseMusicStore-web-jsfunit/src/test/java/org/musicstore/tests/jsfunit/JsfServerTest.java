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

public class JsfServerTest extends org.apache.cactus.ServletTestCase {

    public void test_greetings_message() throws NamingException, IOException {
        WebClientSpec wcSpec = new WebClientSpec("/faces/index.xhtml");

        JSFSession jsfSession = new JSFSession(wcSpec);

        // A JSFClientSession emulates the browser and lets you test HTML
       JSFClientSession client = jsfSession.getJSFClientSession();

       // A JSFServerSession gives you access to JSF state
       JSFServerSession server = jsfSession.getJSFServerSession();
       assertTrue(server != null);

       // Test navigation to initial viewID
       assertTrue(server.getCurrentViewID() != null);
       assertEquals("/index.xhtml", server.getCurrentViewID());

       // Assert that the prompt component is in the component tree and rendered
       UIComponent prompt = server.findComponent("ejbgreeting");
       assertTrue(prompt.isRendered());

       // Test a managed bean
       assertEquals("Greetings from JSF!", server.getManagedBeanValue("#{greetingPresenter.jsfMessage}"));
    }
}
