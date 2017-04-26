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
import com.tesis.remote.DepartmentRemote;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.ejb.builder.DepartmentBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Department;

@RunWith(Arquillian.class)
public class DepartmentBeanTest {
	@EJB(mappedName="DepartmentBean")
	DepartmentRemote department;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(DepartmentBean.class, DepartmentRemote.class, DepartmentBuilder.class)
				.addPackage(Department.class.getPackage())
				.addPackage(DepartmentDTO.class.getPackage())
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
		
		DepartmentDTO departmentDTO=new DepartmentDTO();
		/*Guardar registros*/
		/*departmentDTO.setName("Lacteos");
		department.save(departmentDTO);
		departmentDTO=new DepartmentDTO();
		departmentDTO.setName("Embutidos");
		department.save(departmentDTO);
		departmentDTO=new DepartmentDTO();
		departmentDTO.setName("Jugos");
		department.save(departmentDTO);
		departmentDTO=new DepartmentDTO();
		departmentDTO.setName("Vinos y licores");
		department.save(departmentDTO);
		departmentDTO=new DepartmentDTO();
		departmentDTO.setName("Carnes");
		department.save(departmentDTO);*/
		
		/*Update*/
		/*departmentDTO=new DepartmentDTO();
		departmentDTO.setId(13);
		departmentDTO.setName("Panaderia");
		department.update(departmentDTO);*/
		
		/*Find*/
		/*departmentDTO=new DepartmentDTO();
		departmentDTO=department.getDepartment(9);
		
		System.out.println(departmentDTO);*/
		
		/*Delete*/
		//department.delete(6);
		
		/*GetList*/
		/*List<DepartmentDTO> lstDepartmentDTO=new ArrayList<DepartmentDTO>();
		
		lstDepartmentDTO=department.getListDepartment();
		
		for(DepartmentDTO department: lstDepartmentDTO){
			System.out.println(department);
		}*/

	}

}
