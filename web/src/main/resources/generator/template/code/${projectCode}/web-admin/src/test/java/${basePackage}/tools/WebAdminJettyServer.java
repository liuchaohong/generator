package ${basePackage}.tools;

import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 * 开发调试使用的 Jetty Server
 * @author badqiu
 *
 */
public class WebAdminJettyServer {
	
	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(80, "/");
		server.start();
	}

	/**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		 webContext.setClassLoader(new URLClassLoader(new URL[0], Thread.currentThread().getContextClassLoader()));
		// This webapp will use jsps and jstl. We need to enable the
        // AnnotationConfiguration in order to correctly
        // set up the jsp container
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault( server );
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration" );
        // Set the ContainerIncludeJarPattern so that jetty examines these
        // container-path jars for tlds, web-fragments etc.
        // If you omit the jar that contains the jstl .tlds, the jsp engine will
        // scan for them instead.
        //这一步很重要，要扫描tld文件在jar包中
        webContext.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );
        webContext.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}
	
}
