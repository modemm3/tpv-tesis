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

import com.tesis.remote.SaleRemote;
import com.tesis.tpv.dto.SaleDTO;
import com.tesis.tpv.ejb.builder.EmployeeBuilder;
import com.tesis.tpv.ejb.builder.PaymentTypeBuilder;
import com.tesis.tpv.ejb.builder.SaleBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Sale;

@RunWith (Arquillian.class)
public class SaleBeanTest {
	@EJB (mappedName="SaleBean")
	SaleRemote sale;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(SaleBean.class, SaleRemote.class, SaleBuilder.class)
				.addPackage(SaleDTO.class.getPackage())
				.addPackage(Sale.class.getPackage())
				.addPackage(EmployeeBuilder.class.getPackage())
				.addPackage(PaymentTypeBuilder.class.getPackage())
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
		/*SaleDTO saleDTO=new SaleDTO();
		
		saleDTO.setAmount(new BigDecimal(45.75));
		saleDTO.setDiscount(new BigDecimal(564.23));
		saleDTO.setEmployeeId(2);
		saleDTO.setPaymentTypeId(12);
		sale.save(saleDTO);
		
		saleDTO=new SaleDTO();
		saleDTO.setAmount(new BigDecimal(564.23));
		saleDTO.setDiscount(new BigDecimal(12.232));
		saleDTO.setEmployeeId(3);
		saleDTO.setPaymentTypeId(13);
		sale.save(saleDTO);
		
		saleDTO=new SaleDTO();
		saleDTO.setAmount(new BigDecimal(98.56));
		saleDTO.setDiscount(new BigDecimal(34.54));
		saleDTO.setEmployeeId(4);
		saleDTO.setPaymentTypeId(14);
		sale.save(saleDTO);
		
		saleDTO=new SaleDTO();
		saleDTO.setAmount(new BigDecimal(4342.12));
		saleDTO.setDiscount(new BigDecimal(123.2323));
		saleDTO.setEmployeeId(6);
		saleDTO.setPaymentTypeId(15);
		sale.save(saleDTO);
		
		saleDTO=new SaleDTO();
		saleDTO.setAmount(new BigDecimal(567.344));
		saleDTO.setDiscount(new BigDecimal(341.2));
		saleDTO.setEmployeeId(1);
		saleDTO.setPaymentTypeId(16);
		sale.save(saleDTO);*/
		
		//update
		/*SaleDTO saleDTO=new SaleDTO();
		saleDTO.setId(3);
		saleDTO.setAmount(new BigDecimal(123.123));
		saleDTO.setDiscount(new BigDecimal(123.123));
		saleDTO.setEmployeeId(1);
		saleDTO.setPaymentTypeId(17);
		sale.update(saleDTO);*/
		
		//delete 
		//sale.delete(1);
		
		//get Sale
		/*SaleDTO saleDTO=new SaleDTO();
		
		saleDTO=sale.getSale(5);
		
		System.out.println(saleDTO);*/
		
		//getList Sale
		
		/*List<SaleDTO> saleDTOLst=new ArrayList<SaleDTO>();
		saleDTOLst=sale.getListSale();
		
		for(SaleDTO saleDTO:saleDTOLst){
			System.out.println(saleDTO);
		}*/
	}

}
