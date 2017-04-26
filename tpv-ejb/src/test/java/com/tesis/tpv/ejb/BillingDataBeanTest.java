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

import com.tesis.remote.BillingDataRemote;
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.ejb.builder.BillingDataBuilder;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BillingData;

@RunWith (Arquillian.class)
public class BillingDataBeanTest {
	
	@EJB (mappedName="BillingDataBean")
	BillingDataRemote billingData;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(BillingDataBean.class, BillingDataRemote.class)
				.addPackage(BillingData.class.getPackage())
				.addPackage(BillingDataDTO.class.getPackage())
				.addPackage(BillingDataBuilder.class.getPackage())
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
		/*Guardar*/
		/*BillingDataDTO billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("NESTLE S.A. DE C.V.");
		billingDataDTO.setRfc("NESTLE");
		billingDataDTO.setAddress("Calle Nestle Colonia Nestle");
		billingDataDTO.setRfc("NESTLE1243");
		billingDataDTO.setTelephone("7475621283");
		billingDataDTO.setZipCode("34123");
		billingData.save(billingDataDTO);
		
		billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("COCA COLA S.A. DE C.V.");
		billingDataDTO.setAddress("Calle CocaCola Colonia CocaCola");
		billingDataDTO.setRfc("COCACOLA3212");
		billingDataDTO.setTelephone("7675231456");
		billingDataDTO.setZipCode("3245");
		billingData.save(billingDataDTO);
		
		billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("PASCUAL S.A. DE C.V.");
		billingDataDTO.setAddress("Calle pascual, colonia pascual");
		billingDataDTO.setRfc("PASCUAL9878");
		billingDataDTO.setTelephone("7371234256");
		billingDataDTO.setZipCode("1289");
		billingData.save(billingDataDTO);
		
		billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("JUMEX S.A. DE C.V");
		billingDataDTO.setAddress("Calle jumex colonia jumex");
		billingDataDTO.setRfc("JUMEX5643");
		billingDataDTO.setTelephone("7675234512");
		billingDataDTO.setZipCode("3238");
		billingData.save(billingDataDTO);
		
		billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("GATORADE S.A. DE C.V.");
		billingDataDTO.setAddress("Calle gatorade colonia gatorade");
		billingDataDTO.setRfc("GATORADE2197");
		billingDataDTO.setTelephone("7679828172");
		billingDataDTO.setZipCode("3745");
		billingData.save(billingDataDTO);
		
		billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("1-2-3 S.A. DE C.V");
		billingDataDTO.setAddress("Calle 1-2-3 colonia 1-2-3");
		billingDataDTO.setRfc("1-2-3-4567");
		billingDataDTO.setTelephone("7674128759");
		billingDataDTO.setZipCode("3765");
		billingData.save(billingDataDTO);*/
		
		//billingData.delete(14);
		
		/*update*/
		
		/*BillingDataDTO billingDataDTO=new BillingDataDTO();
		billingDataDTO.setId(13);
		billingDataDTO.setName("PEPSICOLA S.A. DE C.V.");
		billingDataDTO.setAddress("Calle pepsicola colonia pepsicola");
		billingDataDTO.setRfc("PEPSICOLA4576");
		billingDataDTO.setTelephone("7876998877");
		billingDataDTO.setZipCode("4356");
		billingData.update(billingDataDTO);*/
		
		/*get billingData*/
		
		/*BillingDataDTO billingDataDTO=new BillingDataDTO();
		
		billingDataDTO=billingData.getBillingData(13);
		
		if(billingDataDTO!=null){
			System.out.println(billingDataDTO);
		}*/
		
		/*getList billingData*/
		
		/*List<BillingDataDTO> billingDataDTOLst=new ArrayList<BillingDataDTO>();
		
		billingDataDTOLst=billingData.getListBillingData();
		
		if(billingDataDTOLst!=null && billingDataDTOLst.size()>0){
			for(BillingDataDTO billingDataDTO: billingDataDTOLst){
				System.out.println(billingDataDTO);
			}
		}*/
		
	}

}
