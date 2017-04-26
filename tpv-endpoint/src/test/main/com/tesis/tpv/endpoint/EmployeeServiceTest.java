package com.tesis.tpv.endpoint;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
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
import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@WarpTest
@RunWith (Arquillian.class)
public class EmployeeServiceTest {
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
	public void test() {
		/*WebResource webResource=client.resource(contextPath+"services/employeeService/getListEmployee");
		
		List<EmployeeDTO> responseList=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<EmployeeDTO>>(){});
		
		System.out.println("REGISTRO DEL LISTADO EMPLEADO");

		for(EmployeeDTO response: responseList){
		System.out.println("Direccion: "+ response.getAddress()+ " CellPhone: "+ response.getCellPhone()+
				" Maternar name: "+ response.getMaternalName()+ " Name: "+ response.getName() +
				" Paternal name"+ response.getPaternalName()+ " Telephone: "+ response.getTelephone() +
				" Age: "+response.getAge()+ " DepartmentID: "+ response.getDepartmentId()+ 
				" EmployeeCategpyID: "+ response.getEmployeeCategoryId()+ " Id: "+ response.getId()+
				" AdmissionDate: "+response.getAdmissionDate());
		
		}*/
	}
	
	private EmployeeDTO createEmployee(){
	//	DepartmentDTO departmentDTO=new DepartmentDTO();
//		EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		Calendar cal=Calendar.getInstance();
		cal.set(2015, 9, 12);
		Date date=cal.getTime();
		//departmentDTO.setId(14);
	//	employeeCategoryDTO.setId(24);
		EmployeeDTO empl=new EmployeeDTO();
		empl.setAddress("Calle 8 colonia pue deleg. xochimilco");
		empl.setAdmissionDate(date);
		empl.setAge(34);
		empl.setCellPhone("74718923934");
		empl.setDepartmentId(14);
		empl.setEmployeeCategoryId(24);
		empl.setMaternalName("Mejia");
		empl.setName("Mode");
		empl.setPaternalName("Martienez");
		empl.setTelephone("5561729120");
		
		return empl;
	}
	
	private EmployeeDTO createUpdateEmployee(){
		Calendar cal=Calendar.getInstance();
		cal.set(2015, 8, 24);
		
		Date d=new Date();
		d=cal.getTime();
		EmployeeDTO emplDTO=new EmployeeDTO();
		emplDTO.setId(3);
		emplDTO.setAdmissionDate(d);
		emplDTO.setAddress("Calle Tres Colonia 3 CP 3333");
		emplDTO.setAge(40);
		emplDTO.setCellPhone("55555555");
		emplDTO.setDepartmentId(14); //12
		emplDTO.setEmployeeCategoryId(23); //19
		emplDTO.setMaternalName("Tres Materno");
		emplDTO.setName("TRES");
		emplDTO.setPaternalName("Tres Paterno");
		emplDTO.setTelephone("666666666");
		return emplDTO;
	}

}
