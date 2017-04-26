package com.tesis.tpv.ejb;

import java.util.ArrayList;
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

import com.tesis.remote.EmployeeCategoryRemote;
import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.ejb.builder.EmployeeCategoryBuilder;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.EmployeeCategory;

@RunWith(Arquillian.class)
public class EmployeeCategoryBeanTest {
	@EJB(mappedName="EmployeeCategoryBean")
	EmployeeCategoryRemote employeeCategory;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(EmployeeCategoryBean.class, EmployeeCategoryRemote.class)
				.addPackage(EmployeeCategory.class.getPackage())
				.addPackage(EmployeeCategoryDTO.class.getPackage())
				.addPackage(EmployeeCategoryBuilder.class.getPackage())
				.addPackage(Utils.class.getPackage())
				.addPackage(Scanner.class.getPackage())
				.addPackage(Serializer.class.getPackage())
				.addPackage(Vfs.class.getPackage())
				.addPackage(MetadataAdapter.class.getPackage())
				.addPackage(TransferObjectAssembler.class.getPackage())
				.addPackage(Reflections.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test() {
		
		/*Guardar registros*/
		/*EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setName("Lacteos");
		employeeCategory.save(employeeCategoryDTO);
	
		employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setName("jugos");
		employeeCategory.save(employeeCategoryDTO);
		
		employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setName("refrescos");
		employeeCategory.save(employeeCategoryDTO);
		
		employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setName("carnes");
		employeeCategory.save(employeeCategoryDTO);
		
		employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setName("yogurt");
		employeeCategory.save(employeeCategoryDTO);*/
		
		/*update*/
		/*EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO.setId(17);
		employeeCategoryDTO.setName("Dulces");
		employeeCategory.update(employeeCategoryDTO);*/
		
		/*find*/
		/*EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO=employeeCategory.getEmployeeCategory(20);
		System.out.println(employeeCategoryDTO);*/
		
		/*delete*/
		//employeeCategory.delete(20);
		
		/*GetList*/
		/*List<EmployeeCategoryDTO> employeeCategoryDTOLst=new ArrayList<EmployeeCategoryDTO>();
		
		employeeCategoryDTOLst=employeeCategory.getListEmployeeCategory();
		
		System.out.println("Se imprime la lista de EmployeeCategoryBean");
		
		for(EmployeeCategoryDTO employeeCategory:employeeCategoryDTOLst){
			System.out.println(employeeCategory);
		}*/
	}
}
