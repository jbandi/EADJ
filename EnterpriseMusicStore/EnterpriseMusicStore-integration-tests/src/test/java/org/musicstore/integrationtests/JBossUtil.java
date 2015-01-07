package org.musicstore.integrationtests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JBossUtil {

    public static InitialContext createInitialContext() throws NamingException {
        final Properties ejbProperties = new Properties();
        ejbProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        ejbProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        ejbProperties.put("remote.connections", "1");
        ejbProperties.put("remote.connection.1.host", "localhost");
        ejbProperties.put("remote.connection.1.port", "4447");
        ejbProperties.put("org.jboss.ejb.client.scoped.context", "true"); // Not needed when EJBClientContext.setSelector is called programatically. ATTENTION: Client-Interceptor registration below does not work with this property! BUG?

        return new InitialContext(ejbProperties);
    }
}
