package com.tesis.tpv.endpoint;

import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@WarpTest
@RunWith (Arquillian.class)
public class EmployeeCategoryServiceTest {
	@ArquillianResource
	private URL contextPath;
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
		/*WebResource webResource=client.resource(contextPath+"services/employeeCategoryService/getListEmployeeCategory");
		
		List<EmployeeCategoryDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(new GenericType<List<EmployeeCategoryDTO>>(){});
		
		for(EmployeeCategoryDTO employeeCat:response){
			System.out.println("Lista de categorias del empleado : "+employeeCat);
		}*/
	}
	
	public EmployeeCategoryDTO createEmployeeCategory(){
		EmployeeCategoryDTO employeeCategory=new EmployeeCategoryDTO();
		employeeCategory.setName("Recepcionista");
		return employeeCategory;
	}
	
	public EmployeeCategoryDTO createUpEmployeeCategory(){
		EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setId(16);
		employeeCategoryDTO.setName("Jefe de seguridad");
		return employeeCategoryDTO;
	}

}
