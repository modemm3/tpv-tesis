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

import com.tesis.remote.SailDetailRemote;
import com.tesis.tpv.dto.SailDetailDTO;
import com.tesis.tpv.ejb.builder.SailDetailBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.SailDetail;

@RunWith (Arquillian.class)
public class SailDetailBeanTest {
	@EJB (mappedName="SailDetailBean")
	private SailDetailRemote sailDetail;

	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(SailDetailBean.class, SailDetailRemote.class)
				.addPackage(SailDetailBuilder.class.getPackage())
				.addPackage(SailDetailDTO.class.getPackage())
				.addPackage(SailDetail.class.getPackage())
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
		/*SailDetailDTO sailDetailDTO=new SailDetailDTO();
		sailDetailDTO.setProductId(3);
		sailDetailDTO.setQuantity(new BigDecimal(444.444));
		sailDetailDTO.setSaleId(2);
		sailDetail.save(sailDetailDTO);
		
		sailDetailDTO=new SailDetailDTO();
		sailDetailDTO.setProductId(4);
		sailDetailDTO.setQuantity(new BigDecimal(555.555));
		sailDetailDTO.setSaleId(4);
		sailDetail.save(sailDetailDTO);
		
		sailDetailDTO=new SailDetailDTO();
		sailDetailDTO.setProductId(5);
		sailDetailDTO.setQuantity(new BigDecimal(666.666));
		sailDetailDTO.setSaleId(5);
		sailDetail.save(sailDetailDTO);
		
		sailDetailDTO=new SailDetailDTO();
		sailDetailDTO.setProductId(6);
		sailDetailDTO.setQuantity(new BigDecimal(777.777));
		sailDetailDTO.setSaleId(3);
		sailDetail.save(sailDetailDTO);*/
		
		//update
		/*SailDetailDTO sailDetailDTO=new SailDetailDTO();
		sailDetailDTO.setId(1);
		sailDetailDTO.setProductId(9);
		sailDetailDTO.setQuantity(new BigDecimal(111.111));
		sailDetailDTO.setSaleId(5);
		sailDetail.update(sailDetailDTO);*/
		
		//getSailDetail
		/*SailDetailDTO sailDetailDTO=new SailDetailDTO();
		sailDetailDTO=sailDetail.getSailDetail(2);
		
		System.out.println(sailDetailDTO);*/
		
		//getListSailDetail
		
		/*List<SailDetailDTO> sailDetailLst=new ArrayList<SailDetailDTO>();
		
		sailDetailLst=sailDetail.getListSailDetail();
		
		for(SailDetailDTO sailDetailDTO:sailDetailLst){
			System.out.println(sailDetailDTO);
		}*/
		
		//remove
		//sailDetail.delete(4);
		
	}

}
