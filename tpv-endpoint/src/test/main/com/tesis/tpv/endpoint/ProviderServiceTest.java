package com.tesis.tpv.endpoint;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderDTO;

@WarpTest
@RunWith (Arquillian.class)
public class ProviderServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	@Drone
	WebDriver browser;
	
	private Client client;
	
	@Deployment
	public static Archive<?> createTestArchive(){
		return Deployments.createDeployment();
	}
	
	@Before
	public void setUp(){
		client=Client.create(new DefaultClientConfig());
	}

	@Test
	@RunAsClient
	public void testStockCreate() {
		/*WebResource webResource=client.resource(contextPath+ "services/providerService/updateProvider");
		
		MessageResponseDTO response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(MessageResponseDTO.class, updateProvider());*/
		
		/*WebResource webResource=client.resource(contextPath+ "services/providerService/deleteProvider/7");
		
		MessageResponseDTO response=webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(MessageResponseDTO.class);*/
		
		/*WebResource webResource=client.resource(contextPath+"services/providerService/getListProvider");
		
		List<ProviderDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<ProviderDTO>>(){});
		
		for(ProviderDTO provider: response){
			System.out.println("LISTA DE PROVIDER: "+provider);
		}*/
	}
	
	private ProviderDTO createProvider(){
		ProviderDTO providerDTO=new ProviderDTO();
		providerDTO.setAddress("CALLE FLORES COLONIA FLORES C.P.0987");
		providerDTO.setBusinessName("ELEKTRA");
		providerDTO.setMaternalName("FLORES");
		providerDTO.setName("LALO");
		providerDTO.setPaternalName("MARTINEZ");
		providerDTO.setRfc("LALOFM547644");
		providerDTO.setTelephone("7574737290");
		providerDTO.setWebPage("HTTP://LOCALHOST.COM.MX");
		
		return providerDTO;
	}
	
	private ProviderDTO updateProvider(){
		ProviderDTO providerDTO=new ProviderDTO();
		providerDTO.setId(6);
		providerDTO.setAddress("CALLE BALBUENA COLONIA POLANCO");
		providerDTO.setBusinessName("DICO");
		providerDTO.setMaternalName("GARCIA");
		providerDTO.setName("RAMIRO");
		providerDTO.setPaternalName("RIOS");
		providerDTO.setRfc("RAMIRORG213421");
		providerDTO.setTelephone("444444");
		providerDTO.setWebPage("HTTP://DICO.COM.MX");
		return providerDTO;
	}

}
