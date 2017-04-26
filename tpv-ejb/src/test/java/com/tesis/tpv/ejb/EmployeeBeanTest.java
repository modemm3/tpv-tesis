package com.tesis.tpv.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.adapters.MetadataAdapter;
import org.reflections.scanners.Scanner;
import org.reflections.serializers.Serializer;
import org.reflections.util.Utils;
import org.reflections.vfs.Vfs;
import com.tesis.remote.EmployeeRemote;
import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.ejb.builder.DepartmentBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Employee;

@RunWith (Arquillian.class)
public class EmployeeBeanTest {
	@EJB (mappedName="EmployeeBean")
	EmployeeRemote employee;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(EmployeeBean.class, EmployeeRemote.class)
				.addPackage(Employee.class.getPackage())
				.addPackage(DepartmentBuilder.class.getPackage())
				.addPackage(EmployeeDTO.class.getPackage())
				.addPackage(Utils.class.getPackage())
				.addPackage(Scanner.class.getPackage())
				.addPackage(Serializer.class.getPackage())
				.addPackage(Vfs.class.getPackage())
				.addPackage(MetadataAdapter.class.getPackage())
				.addPackage(TransferObjectAssembler.class.getPackage())
				.addPackage(Reflections.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}

	@Test
	public void test() {
		/*save*/
	//	try{
			/*String fecha="13-11-2015";
			
			Date dateAdmission=new Date();
			SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
			dateAdmission=format.parse(fecha);
			EmployeeDTO employeeDTO;*/
			
			/*employeeDTO=new EmployeeDTO();
			employeeDTO.setAddress("Calle cuahutemoc colonia cuahutemoc");
			employeeDTO.setAdmissionDate(dateAdmission);
			employeeDTO.setAge(31);
			employeeDTO.setCellPhone("7475236153");
			employeeDTO.setDepartmentId(12);
			employeeDTO.setEmployeeCategoryId(19);
			employeeDTO.setMaternalName("cuahutemoc materno");
			employeeDTO.setPaternalName("cuahutemoc paterno");
			employeeDTO.setTelephone("5567982314");
			employeeDTO.setName("Cuahutemoc");
			employee.save(employeeDTO);
			
		    employeeDTO=new EmployeeDTO();
			employeeDTO.setAddress("Calle Domingo colonia Domingo");
			employeeDTO.setAdmissionDate(dateAdmission);
			employeeDTO.setAge(56);
			employeeDTO.setCellPhone("747523634212");
			employeeDTO.setDepartmentId(9);
			employeeDTO.setEmployeeCategoryId(16);
			employeeDTO.setMaternalName("Domingo materno");
			employeeDTO.setPaternalName("Domingo paterno");
			employeeDTO.setTelephone("558982314");
			employeeDTO.setName("Domingo");
			employee.save(employeeDTO);
			
		    employeeDTO=new EmployeeDTO();
			employeeDTO.setAddress("Calle Sabado colonia Sabado");
			employeeDTO.setAdmissionDate(dateAdmission);
			employeeDTO.setAge(45);
			employeeDTO.setCellPhone("32891756434");
			employeeDTO.setDepartmentId(11);
			employeeDTO.setEmployeeCategoryId(17);
			employeeDTO.setMaternalName("Sabado materno");
			employeeDTO.setPaternalName("Sabado paterno");
			employeeDTO.setTelephone("5543235467");
			employeeDTO.setName("Sabado");
			employee.save(employeeDTO);
			
		    employeeDTO=new EmployeeDTO();
			employeeDTO.setAddress("Calle Lunes colonia Lunes");
			employeeDTO.setAdmissionDate(dateAdmission);
			employeeDTO.setAge(45);
			employeeDTO.setCellPhone("32891756434");
			employeeDTO.setDepartmentId(8);
			employeeDTO.setEmployeeCategoryId(19);
			employeeDTO.setMaternalName("Lunes materno");
			employeeDTO.setPaternalName("Lunes paterno");
			employeeDTO.setTelephone("5543235467");
			employeeDTO.setName("Sor juana Ines");
			employee.save(employeeDTO);*/
			
			//update
			/*employeeDTO=new EmployeeDTO();
			employeeDTO.setId(1);
			employeeDTO.setName("Luis");
			employeeDTO.setAddress("Calle luna colonia estrella");
			employeeDTO.setAdmissionDate(dateAdmission);
			employeeDTO.setAge(33);
			employeeDTO.setCellPhone("7574341111");
			employeeDTO.setDepartmentId(7);
			employeeDTO.setEmployeeCategoryId(18);
			employeeDTO.setMaternalName("Garcia");
			employeeDTO.setPaternalName("Benitez");
			employeeDTO.setTelephone("5578954211");
			employee.update(employeeDTO);*/
			
		
	/*	}catch(Exception e){
			e.printStackTrace();
		}*/
		
		//delete
		//employee.delete(5);
		
		//get employee
/*		EmployeeDTO employeeDTO=new EmployeeDTO();
		employeeDTO=employee.getEmployee(6);
		
		System.out.println(employeeDTO);*/
		
		/*get EmployeeLst*/
		/*List<EmployeeDTO> employeeDTOLst=new ArrayList<EmployeeDTO>();
		
		employeeDTOLst=employee.getListEmployee();
		
		for(EmployeeDTO employeeDTO:employeeDTOLst){
			System.out.println(employeeDTO);
		}*/
	}

}
