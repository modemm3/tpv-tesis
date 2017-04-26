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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.endpoint.CategoryService;

@WarpTest
@RunWith (Arquillian.class)
public class CategoryServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	@Drone
	WebDriver browser;
	
	private Client client;
	private static final Logger LOG=LoggerFactory.getLogger(CategoryServiceTest.class);

	
	@Deployment
    public static Archive<?> createTestArchive() {

        return Deployments.createDeployment();
    }
	
	@Before
    public void setUp() {

        client = Client.create(new DefaultClientConfig());
    }
	
	@Test
    @RunAsClient
    public void testStockCreate() {

        //WebResource webResource = client.resource(contextPath + "services/categoryService/deleteCategory/52");

       /* MessageResponseDTO response = webResource
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(MessageResponseDTO.class);*/
		
	/*	System.out.println("iniciando test de category service:"+contextPath + "services/categoryService/getCategory/48");
	      WebResource webResource = client.resource(contextPath + "services/categoryService/getCategory/48");

	      CategoryDTO response = webResource
	                .accept(MediaType.APPLICATION_JSON_TYPE)
	                .type(MediaType.APPLICATION_JSON_TYPE)
	                .post(CategoryDTO.class);
	                */
		WebResource webResource=client.resource(contextPath + "services/categoryService/saveCategory");
		
		MessageResponseDTO response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
			 	.type(MediaType.APPLICATION_JSON_TYPE)
				.post(MessageResponseDTO.class, createCategory());
		
		System.out.println(response);
		
		/*WebResource webResource=client.resource(contextPath +"services/categoryService/getListCategory");
		
		
		List<CategoryDTO> response= webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<CategoryDTO>>(){});


        for(CategoryDTO resp: response){
        	System.out.println("Response:"+resp.toString());
        }*/
    }
	private CategoryDTO updateCategory(){
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setId(51);
		categoryDTO.setName("Frutas y legumbres");
		categoryDTO.setDescription("guayaba, manzana, pepino");
		return categoryDTO;
	}
	
	private CategoryDTO createCategory(){
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setName("Ropa de playa uno");
		categoryDTO.setDescription("Hombres, mujeres");
		return categoryDTO;
	}
	
	private Integer deleteCategory(){
		Integer idCategory=52;
		
		return idCategory;
	}

}
