package org.musicstore.tests.jsfunit;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.jboss.jsfunit.framework.WebClientSpec;
import org.jboss.jsfunit.jsfsession.JSFClientSession;
import org.jboss.jsfunit.jsfsession.JSFServerSession;
import org.jboss.jsfunit.jsfsession.JSFSession;
import org.musicstore.Greeter;
import org.musicstore.model.entities.Album;
import org.w3c.dom.Element;

public class BrowseGenreTest extends org.apache.cactus.ServletTestCase {

    public void test_browse_genre_by_url() throws NamingException, IOException {
        JSFSession jsfSession = new JSFSession("/faces/albumSearch.xhtml?genre=Pop");

        JSFServerSession server = jsfSession.getJSFServerSession();
        JSFClientSession client = jsfSession.getJSFClientSession();

        UIComponent prompt = server.findComponent("msg_nothing");
        assertFalse(prompt.isRendered());

        List<Album> albums = (List<Album>) server.getManagedBeanValue("#{searchPresenter.result}");
        assertNotNull(albums);
        assertEquals(2, albums.size());

        String pageText = client.getPageAsText();
        assertTrue(pageText.contains("Torch Songs"));
        assertTrue(pageText.contains("Beautiful Garbage"));

    }

    public void test_browse_genre_interactive() throws NamingException, IOException {
        JSFSession jsfSession = new JSFSession("/faces/index.xhtml");

        JSFServerSession server = jsfSession.getJSFServerSession();
        JSFClientSession client = jsfSession.getJSFClientSession();

        client.setValue("q", "Hells Bells");
        client.click("search");

        assertEquals("/albumSearch.xhtml", server.getCurrentViewID());

        List<Album> albums = (List<Album>) server.getManagedBeanValue("#{searchPresenter.result}");
        assertNotNull(albums);
        assertEquals(1, albums.size());

        String pageText = client.getPageAsText();
        assertTrue(pageText.contains("Hells Bells"));
        Element element = client.getElement("album-list");
        assertTrue(element.getTextContent().contains("Hells Bells"));

        HtmlPage page = (HtmlPage)client.getContentPage();
        List<HtmlLink> albumLinks = (List<HtmlLink>)page.getByXPath(".//ul[@id='album-list']/li/a");
        assertEquals(1, albumLinks.size());
    }
}
