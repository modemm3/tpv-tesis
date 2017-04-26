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

import com.tesis.remote.TypeDiscountRemote;
import com.tesis.tpv.dto.TypeDiscountDTO;
import com.tesis.tpv.ejb.builder.TypeDiscountBuilder;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.TypeDiscount;

@RunWith(Arquillian.class)
public class TypeDiscountBeanTest {
	@EJB(mappedName="TypeDiscountBean")
	TypeDiscountRemote typeDiscount;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(TypeDiscountBean.class, TypeDiscountRemote.class)
				.addPackage(TypeDiscount.class.getPackage())
				.addPackage(TypeDiscountDTO.class.getPackage())
				.addPackage(TypeDiscountBuilder.class.getPackage())
				.addPackage(Utils.class.getPackage())
				.addPackage(Scanner.class.getPackage())
				.addPackage(Serializer.class.getPackage())
				.addPackage(Vfs.class.getPackage())
				.addPackage(MetadataAdapter.class.getPackage())
				.addPackage(TransferObjectAssembler.class.getPackage())
				.addPackage(Reflections.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test() {
		/*Guardar*/
		/*TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setName("Verano 1");
		typeDiscountDTO.setDescription("descuentos de verano 1");
		typeDiscount.save(typeDiscountDTO);
		
		typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setName("Decembrina 1");
		typeDiscountDTO.setDescription("temporada decembrina 1");
		typeDiscount.save(typeDiscountDTO);
		
		typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setName("Cuesta enero 1");
		typeDiscountDTO.setDescription("temporada de enero 1");
		typeDiscount.save(typeDiscountDTO);
		
		typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setName("Buen fin 1");
		typeDiscountDTO.setDescription("Temporada de noviembre 1");
		typeDiscount.save(typeDiscountDTO);
		
		typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setName("Otoño 1");
		typeDiscountDTO.setDescription("Temporada de marzo-abril 1");
		typeDiscount.save(typeDiscountDTO);*/
		
		/*update*/
		/*TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setId(16);
		typeDiscountDTO.setName("Primavera");
		typeDiscountDTO.setDescription("Descuentos de primavera");
		typeDiscount.update(typeDiscountDTO);*/
		
		/*delete*/
		//typeDiscount.delete(13);
		
		/*find*/
		/*TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO=typeDiscount.getTypeDiscount(15);
		
		System.out.println(typeDiscountDTO);*/
		
		/*getList*/
		
		/*List<TypeDiscountDTO> typeDiscountDTOLst=new ArrayList<TypeDiscountDTO>();
		typeDiscountDTOLst=typeDiscount.getListTypeDiscount();
		
		if(typeDiscountDTOLst.size()>0){
			for(TypeDiscountDTO typeDiscountDTO: typeDiscountDTOLst){
				System.out.println(typeDiscountDTO);
			}
		}*/
		
	}
}
