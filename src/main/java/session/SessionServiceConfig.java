package session;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by Admin on 01.10.2015.
 */
    @EnableWs
    @Configuration
    public class SessionServiceConfig extends WsConfigurerAdapter {
        @Bean
        public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
            MessageDispatcherServlet servlet = new MessageDispatcherServlet();
            servlet.setApplicationContext(applicationContext);
            servlet.setTransformWsdlLocations(true);
            return new ServletRegistrationBean(servlet, "/ws/*");
        }

        @Bean(name = "sessions")
        public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
            DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
            wsdl11Definition.setPortTypeName("SessionPort");
            wsdl11Definition.setLocationUri("/ws");
            wsdl11Definition.setTargetNamespace("http://fortress/lighthouse/session/service/v1");
            wsdl11Definition.setSchema(countriesSchema);
            return wsdl11Definition;
        }

        @Bean
        public XsdSchema countriesSchema() {
            return new SimpleXsdSchema(new ClassPathResource("lighthouse_service_session_v1_0.xsd"));
        }
    }

