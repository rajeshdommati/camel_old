package camel.training;

import java.io.File;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.BlobMessage;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.jms.core.SessionCallback;

public class JMSFileExample {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder() {
		    public void configure() {
		        from("file:C:/camel/training").to("activemq:queue:test?jmsMessageType=Text");
		        //from("activemq:queue:test").to("file:C:/camel/training");
		    	//from("file:C:/camel/training")
		           // .to("file:C:/camel/training/out");
		    }
		});
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost:8161?broker.persistent=false");
				
		// Note we can explicit name the component
		context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		ProducerTemplate template = context.createProducerTemplate();
		context.start();
		//for(;;){}
		
		Thread.sleep(2000);

		context.stop();
	}

}
