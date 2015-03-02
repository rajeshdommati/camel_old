
package camel.training;

import java.io.File;

import javax.jms.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.jms.core.SessionCallback;

public class FolderFileTransfer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder() {
		    public void configure() {
		       	from("file:C:/camel/training")
		            .to("file:C:/camel/training/out");
		    }
		});

		ProducerTemplate template = context.createProducerTemplate();
		context.start();
		//for(;;){}
		
		Thread.sleep(2000);

		context.stop();
	}

}
