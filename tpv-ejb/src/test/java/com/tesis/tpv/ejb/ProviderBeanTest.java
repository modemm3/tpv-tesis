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

import com.tesis.remote.ProviderRemote;
import com.tesis.tpv.dto.ProviderDTO;
import com.tesis.tpv.ejb.builder.ProviderBuilder;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Provider;

@RunWith (Arquillian.class)
public class ProviderBeanTest {
	
	@EJB (mappedName="ProviderBean")
	ProviderRemote provider;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(ProviderBean.class, ProviderRemote.class)
				.addPackage(Provider.class.getPackage())
				.addPackage(ProviderDTO.class.getPackage())
				.addPackage(ProviderBuilder.class.getPackage())
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
		/*ProviderDTO providerDTO=new ProviderDTO();

		providerDTO.setName("Inesito");
		providerDTO.setAddress("calle uno colonia uno");
		providerDTO.setBusinessName("TIA ROSA");
		providerDTO.setMaternalName("tia rosa uno");
		providerDTO.setPaternalName("tian rosa dos");
		providerDTO.setRfc("inesito1234");
		providerDTO.setTelephone("7576124351");
		providerDTO.setWebPage("TIAROSA.COM.MX");
		provider.save(providerDTO);
		
		providerDTO=new ProviderDTO();
		providerDTO.setName("Teresa");
		providerDTO.setAddress("calle dos colonia dos");
		providerDTO.setBusinessName("PEPSI COLA");
		providerDTO.setMaternalName("pepsi cola uno");
		providerDTO.setPaternalName("pepsi cola dos");
		providerDTO.setRfc("teresa8765");
		providerDTO.setTelephone("7573128976");
		providerDTO.setWebPage("PEPSICOLA.COM.MX");
		provider.save(providerDTO);
		
		providerDTO=new ProviderDTO();
		providerDTO.setName("Pedro");
		providerDTO.setAddress("calle tres colonia tres");
		providerDTO.setBusinessName("JUMEX");
		providerDTO.setMaternalName("jumex uno");
		providerDTO.setPaternalName("jumex dos");
		providerDTO.setRfc("jumex5476");
		providerDTO.setTelephone("7574239876");
		providerDTO.setWebPage("JUMEX.COM.MX");
		provider.save(providerDTO);
		
		providerDTO=new ProviderDTO();
		providerDTO.setName("pablo");
		providerDTO.setAddress("calle cuatro colonia cuatro");
		providerDTO.setBusinessName("LECHE LALA");
		providerDTO.setMaternalName("lala uno");
		providerDTO.setPaternalName("lala dos");
		providerDTO.setRfc("lala4565");
		providerDTO.setTelephone("7578234565");
		providerDTO.setWebPage("LALA.COM.MX");
		provider.save(providerDTO);
		
		providerDTO=new ProviderDTO();
		providerDTO.setName("bety");
		providerDTO.setAddress("calle cinco colonia cinco");
		providerDTO.setBusinessName("SABRITAS");
		providerDTO.setMaternalName("sabritas uno");
		providerDTO.setPaternalName("sabritas dos");
		providerDTO.setRfc("sabritas7654");
		providerDTO.setTelephone("7476230190");
		providerDTO.setWebPage("SABRITAS.COM.MX");
		provider.save(providerDTO);*/
		
		/*update*/
		/*ProviderDTO providerDTO=new ProviderDTO();
		
		providerDTO.setId(10);
		providerDTO.setName("bety y pablo");
		providerDTO.setAddress("calle pablo colonia bety");
		providerDTO.setBusinessName("SABRITAS.COM.MX Y ASOCIADOS");
		providerDTO.setMaternalName("bety uno");
		providerDTO.setPaternalName("pablo uno");
		providerDTO.setRfc("betypablo6723");
		providerDTO.setTelephone("5543231232");
		providerDTO.setWebPage("SABRITAS Y ASOCIADOS");
		provider.update(providerDTO);*/
		
		/*delete*/
		//provider.delete(8);
		
		/*get provider*/
		/*ProviderDTO providerDTO=new ProviderDTO();
		
		providerDTO=provider.getProvider(6);
		
		if(providerDTO!=null){
			System.out.println(providerDTO);
		}*/
		
		/*getList provider*/
		/*List<ProviderDTO> providerDTOLst=new ArrayList<ProviderDTO>();
		
		providerDTOLst=provider.getListProvider();
		
		if(providerDTOLst!=null && providerDTOLst.size()>0){
			for(ProviderDTO providerDTO: providerDTOLst){
				System.out.println(providerDTO);
			}
		}*/
	}
}
