/*package com.tesis.tpv.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class CategoryTest {
	private static final Logger LOG=LoggerFactory.getLogger(CategoryTest.class);

	@PersistenceContext
	EntityManager em;
	@Inject
	UserTransaction utx;

	private void insertData(){*/
		/*try {
			utx.begin();
			em.joinTransaction();
			LOG.info("startTransacction CategoryTest");
			Category category=new Category();

			category.setName("Categoria uno");
			category.setDescription("Descripcion categoria");
			em.persist(category);
			utx.commit();
			em.clear();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}*/
	/*}
	
	@Deployment
	public static JavaArchive createDeployment(){*/
		/*return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(Category.class.getPackage())
				.addAsResource("test-persistence.xml","META-INF/persistence.xml").
				addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");*/
		
	/*	return null;
	}
	
	@Test
	public void test() {
		insertData();
	}

}*/
