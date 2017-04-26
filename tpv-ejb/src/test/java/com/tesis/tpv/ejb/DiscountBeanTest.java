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

import com.tesis.remote.DiscountRemote;
import com.tesis.tpv.dto.DiscountDTO;
import com.tesis.tpv.ejb.builder.DiscountBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Discount;

@RunWith (Arquillian.class)
public class DiscountBeanTest {
	@EJB (mappedName="DiscountBean")
	private DiscountRemote discount;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(DiscountBean.class, DiscountRemote.class)
				.addPackage(DiscountDTO.class.getPackage())
				.addPackage(DiscountBuilder.class.getPackage())
				.addPackage(Discount.class.getPackage())
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
		//save
		/*Date dateEnd=new Date();
		Date dateStart=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		String fecha="20-02-2015";
		String fecha1="20-02-2000";
		try{
		dateEnd=format.parse(fecha);
		dateStart=format.parse(fecha1);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		/*DiscountDTO discountDTO=new DiscountDTO();
		discountDTO.setAmount(new BigDecimal(3412.122));
		discountDTO.setEndDate(dateEnd);
		discountDTO.setName("Primavera");
		discountDTO.setProductId(7);
		discountDTO.setStartDate(dateStart);
		discountDTO.setStatus(false);
		discountDTO.setTypeDiscountId(16);
		discount.save(discountDTO);
		
		discountDTO=new DiscountDTO();
		discountDTO.setAmount(new BigDecimal(9876.4532));
		discountDTO.setEndDate(dateEnd);
		discountDTO.setName("Decembrina");
		discountDTO.setProductId(8);
		discountDTO.setStartDate(dateStart);
		discountDTO.setStatus(true);
		discountDTO.setTypeDiscountId(14);
		discount.save(discountDTO);
		
		discountDTO=new DiscountDTO(); 	
		discountDTO.setAmount(new BigDecimal(3421.111));
		discountDTO.setEndDate(dateEnd);
		discountDTO.setName("Otoño");
		discountDTO.setProductId(4);
		discountDTO.setStartDate(dateStart);
		discountDTO.setStatus(false);
		discountDTO.setTypeDiscountId(17);
		discount.save(discountDTO);
		
		discountDTO=new DiscountDTO();
		discountDTO.setAmount(new BigDecimal(44332.223));
		discountDTO.setEndDate(dateEnd);
		discountDTO.setName("Enero");
		discountDTO.setProductId(10);
		discountDTO.setStartDate(dateStart);
		discountDTO.setStatus(true);
		discountDTO.setTypeDiscountId(15);
		discount.save(discountDTO);*/
		
		//update
		/*DiscountDTO discountDTO=new DiscountDTO();
		discountDTO.setId(1);
		discountDTO.setEndDate(dateEnd);
		discountDTO.setStartDate(dateStart);
		discountDTO.setName("Primaverasssss");
		discountDTO.setProductId(8);
		discountDTO.setStatus(true);
		discountDTO.setTypeDiscountId(14);
		discount.update(discountDTO);*/
		
		//getDiscount
		
		/*DiscountDTO discountDTO=new DiscountDTO();
		
		discountDTO=discount.getDiscount(2);
		System.out.println("DATA DISCOUNT: "+ discountDTO);*/
		
		//getListDiscount
		
		/*List<DiscountDTO> discountDTOLst=new ArrayList<DiscountDTO>();
		
		discountDTOLst=discount.getListDiscount();
		
		for(DiscountDTO discountDTO: discountDTOLst){
			System.out.println("LstDISCOUNT: "+ discountDTO);
		}*/
		
		//delete
		//discount.delete(1);
		
	}

}
