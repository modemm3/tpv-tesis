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

import com.tesis.remote.ProviderProductRemote;
import com.tesis.tpv.dto.ProviderProductDTO;
import com.tesis.tpv.ejb.builder.ProviderProductBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.ProviderProduct;

@RunWith (Arquillian.class)
public class ProviderProductBeanTest {
	@EJB (mappedName="ProviderProductBean")
	private ProviderProductRemote providerProduct;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(ProviderProductBean.class, ProviderProductRemote.class)
				.addPackage(ProviderProduct.class.getPackage())
				.addPackage(ProviderProductBuilder.class.getPackage())
				.addPackage(ProviderProductDTO.class.getPackage())
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
		/*Date fecha=new Date();
		String fech="2012-01-01";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try{
		fecha=format.parse(fech);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		/*ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		providerProductDTO.setBuyDate(fecha);
		providerProductDTO.setPrice(new BigDecimal(999.098));
		providerProductDTO.setProductId(9);
		providerProductDTO.setProviderId(10);
		providerProduct.save(providerProductDTO);
		
		providerProductDTO=new ProviderProductDTO();
		providerProductDTO.setBuyDate(fecha);
		providerProductDTO.setPrice(new BigDecimal(888.0987));
		providerProductDTO.setProductId(10);
		providerProductDTO.setProviderId(9);
		providerProduct.save(providerProductDTO);
		
		providerProductDTO=new ProviderProductDTO();
		providerProductDTO.setBuyDate(fecha);
		providerProductDTO.setPrice(new BigDecimal(555.4444));
		providerProductDTO.setProductId(8);
		providerProductDTO.setProviderId(7);
		providerProduct.save(providerProductDTO);
		
		providerProductDTO=new ProviderProductDTO();
		providerProductDTO.setBuyDate(fecha);
		providerProductDTO.setPrice(new BigDecimal(232.12));
		providerProductDTO.setProductId(7);
		providerProductDTO.setProviderId(6);
		providerProduct.save(providerProductDTO);*/
		
		//update
		/*ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		providerProductDTO.setId(4);
		providerProductDTO.setBuyDate(fecha);
		providerProductDTO.setPrice(new BigDecimal(111.111111));
		providerProductDTO.setProductId(3);
		providerProductDTO.setProviderId(10);
		providerProduct.update(providerProductDTO);*/
		
		//getProviderProduct
		/*ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		
		providerProductDTO=providerProduct.getProviderProduct(3);
		System.out.println("DATA: "+providerProductDTO);*/
		
		//getListProviderProduct
		/*List<ProviderProductDTO> providerProductDTOLst=new ArrayList<ProviderProductDTO>();
		
		providerProductDTOLst=providerProduct.getListProviderProduct();
		
		for(ProviderProductDTO providerProductDTO:providerProductDTOLst){
			System.out.println("DATALST: "+providerProductDTO);
		}*/
		
		//delete
		//providerProduct.delete(1);
		
	}

}
