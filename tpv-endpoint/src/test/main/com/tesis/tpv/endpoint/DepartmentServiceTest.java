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
import com.tesis.tpv.dto.DepartmentDTO;

@WarpTest
@RunWith (Arquillian.class)
public class DepartmentServiceTest {
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
		WebResource webResource=client.resource(contextPath+"services/departmentService/getListDepartment");
		
		List<DepartmentDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<DepartmentDTO>>(){});
		
		for(DepartmentDTO department:response){
			System.out.println("Response de Department: "+department);
		}
	}
	
	private DepartmentDTO createDepartment(){
		DepartmentDTO departmentDTO=new DepartmentDTO();
		departmentDTO.setName("Accesorios");
		return departmentDTO;
	}
	
	private DepartmentDTO updateDepartment(){
		DepartmentDTO departmentDTO=new DepartmentDTO();
		departmentDTO.setId(10);
		departmentDTO.setName("SALCHICHONERIA");
		return departmentDTO;
	}

}
