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

import com.tesis.remote.CategoryRemote;
import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.ejb.builder.CategoryBuilder;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;

import javassist.bytecode.analysis.Util;

@RunWith(Arquillian.class)
public class CategoryBeanTest {
	
	@EJB(mappedName="CategoryBean")
	CategoryRemote category;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class,"testEJB.jar")
				.addClasses(CategoryBean.class, CategoryRemote.class, CategoryBuilder.class)
				.addPackage(Category.class.getPackage())
				.addPackage(CategoryDTO.class.getPackage())
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
		
		CategoryDTO catDTO=new CategoryDTO();
		
		//Prueba de guardado
	/*	catDTO.setName("legumbres");
		catDTO.setDescription("ejote, higo");
		category.save(catDTO);
		
		catDTO=new CategoryDTO();
		catDTO.setName("embutidos");
		catDTO.setDescription("jamon, salchicha");
		category.save(catDTO);
		
		catDTO=new CategoryDTO();
		catDTO.setName("Lacteos");
		catDTO.setDescription("leche regular, leche deslactosada");
		category.save(catDTO);
		
		catDTO=new CategoryDTO();
		catDTO.setName("Ropa");
		catDTO.setDescription("camisas, vestidos, faldas, pantalones");
		category.save(catDTO);
		
		catDTO=new CategoryDTO();
		catDTO.setName("Calzado");
		catDTO.setDescription("tenis, zapatos de vestir, botas, botines");
		category.save(catDTO);
		
		catDTO=new CategoryDTO();
		catDTO.setName("Verduras");
		catDTO.setDescription("rabanos, lechugas");
		category.save(catDTO);*/
		
		
		//Prueba de update
		/*catDTO=new CategoryDTO();
		catDTO.setId(50);
		catDTO.setName("Detergentes");
		catDTO.setDescription("Salvo, Ariel, Axion");
		category.update(catDTO);*/
		
		//prueba de find 
		/*catDTO=new CategoryDTO();
		catDTO=category.getCategory(49);
		
		System.out.println(catDTO);*/
		
		//prueba de delete
		//category.delete(47);
		
		//Prueba de obtener la lista de categorias
		/*List<CategoryDTO> lstCatDTO=new ArrayList<CategoryDTO>();
		lstCatDTO=category.getList();
		
		System.out.println("Se imprime la lista: ");
		for(CategoryDTO cat: lstCatDTO){
			System.out.println(cat);
		}*/
		
	}
	
}