package com.tesis.tpv.ejb;

import java.math.BigDecimal;
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

import com.tesis.remote.BillingRemote;
import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.ejb.builder.BillingBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Billing;

@RunWith (Arquillian.class)
public class BillingBeanTest {
	@EJB (mappedName="BillingBean")
	BillingRemote billing;

	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(BillingBean.class, BillingRemote.class)
				.addPackage(BillingDTO.class.getPackage())
				.addPackage(Billing.class.getPackage())
				.addPackage(BillingBuilder.class.getPackage())
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
		//guardar
		Date date=new Date();

		try{
			String fecha="2015-09-05";
			SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
			date=simple.parse(fecha);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		BillingDTO billingDTO=new BillingDTO();
		billingDTO.setBillingDataId(13);
		billingDTO.setBillingDate(date);
		billingDTO.setSaleId(2);
		billingDTO.setSubtotal(new BigDecimal(67.54));
		billingDTO.setTotal(new BigDecimal(4567.3323));
		billing.save(billingDTO);
		
		billingDTO=new BillingDTO();
		billingDTO.setBillingDataId(15);
		billingDTO.setBillingDate(date);
		billingDTO.setSaleId(3);
		billingDTO.setSubtotal(new BigDecimal(432.12));
		billingDTO.setTotal(new BigDecimal(123.122));
		billing.save(billingDTO);
		
		billingDTO=new BillingDTO();
		billingDTO.setBillingDataId(16);
		billingDTO.setBillingDate(date);
		billingDTO.setSaleId(4);
		billingDTO.setSubtotal(new BigDecimal(98.56));
		billingDTO.setTotal(new BigDecimal(234.12));
		billing.save(billingDTO);
		
		//update
		
		/*BillingDTO billingDTO=new BillingDTO();
		billingDTO.setId(3);
		billingDTO.setBillingDataId(18);
		billingDTO.setBillingDate(date);
		billingDTO.setSaleId(5);
		billingDTO.setSubtotal(new BigDecimal(66.66));
		billingDTO.setTotal(new BigDecimal(76.66));
		billing.update(billingDTO);*/
		
		//getBilling
		
		/*BillingDTO billingDTO=new BillingDTO();
		
		billingDTO=billing.getBilling(2);
		System.out.println(billingDTO);*/
		
		//getListBilling
		
		/*List<BillingDTO> getBillingLST=new ArrayList<BillingDTO>();
		getBillingLST=billing.getListBilling();
		
		for(BillingDTO billingDTO:getBillingLST){
			System.out.println(billingDTO);
		}*/
		
		//delete
		
		//billing.delete(2);
	}

}
