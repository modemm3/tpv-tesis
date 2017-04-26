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

import com.tesis.remote.PaymentTypeRemote;
import com.tesis.tpv.dto.PaymentTypeDTO;
import com.tesis.tpv.ejb.builder.PaymentTypeBuilder;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.PaymentType;

@RunWith(Arquillian.class)
public class PaymentTypeBeanTest {
	@EJB(mappedName="PaymentTypeBean")
	PaymentTypeRemote paymentType;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(PaymentTypeBean.class, PaymentTypeRemote.class)
				.addPackage(PaymentType.class.getPackage())
				.addPackage(PaymentTypeDTO.class.getPackage())
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
		
		/*save*/
		/*PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Efectivo");
		paymentType.save(paymentTypeDTO);
		
		paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Cheque");
		paymentType.save(paymentTypeDTO);
		
		paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Credito");
		paymentType.save(paymentTypeDTO);
		
		paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Debito");
		paymentType.save(paymentTypeDTO);
		
		paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Transferencia Interbancaria");
		paymentType.save(paymentTypeDTO);
		
		paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Deposito");
		paymentType.save(paymentTypeDTO);
		
		paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Debito uno");
		paymentType.save(paymentTypeDTO);*/
		
		/*delete*/
		//paymentType.delete(18);
		
		/*update*/
		/*PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setId(18);
		paymentTypeDTO.setName("Debito dos	");
		
		paymentType.update(paymentTypeDTO);*/
		
		/*get PaymentTypeDTO*/
		/*PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		
		paymentTypeDTO=paymentType.getPaymentType(16);
		
		if(paymentTypeDTO!=null){
			System.out.println(paymentTypeDTO);
		}*/
		
		/*getLst paymentTypeDTO*/
	/*	int i=1;
		List<PaymentTypeDTO> paymentTypeDTOLst=new ArrayList<PaymentTypeDTO>();
		paymentTypeDTOLst=paymentType.getListPaymentType();
		
		if(paymentTypeDTOLst!=null && paymentTypeDTOLst.size()>0){
			for(PaymentTypeDTO paymentTypeDTO: paymentTypeDTOLst ){
				System.out.println(i+".- "+paymentTypeDTO);
			}
		}*/
	}
}
