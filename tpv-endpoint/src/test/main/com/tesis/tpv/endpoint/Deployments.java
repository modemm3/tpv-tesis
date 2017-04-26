package com.tesis.tpv.endpoint;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.reflections.Reflections;
import org.reflections.adapters.MetadataAdapter;
import org.reflections.scanners.Scanner;
import org.reflections.serializers.Serializer;
import org.reflections.util.Utils;
import org.reflections.vfs.Vfs;

import com.sun.jersey.api.core.HttpRequestContext;
import com.sun.jersey.core.spi.scanning.ScannerListener;
import com.sun.jersey.core.util.FeaturesAndProperties;
import com.sun.jersey.spi.scanning.AnnotationScannerListener;
import com.tesis.remote.SailDetailRemote;
import com.tesis.tpv.dto.SailDetailDTO;
import com.tesis.tpv.ejb.SailDetailBean;
import com.tesis.tpv.ejb.builder.SailDetailBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Product;
import com.tesis.tpv.jpa.SailDetail;
import com.tesis.tpv.jpa.Sale;

public class Deployments {

	public static Archive<?> createDeployment() {

		WebArchive web= ShrinkWrap.create(WebArchive.class)
				.addClasses(ApplicationConfig.class)
				.addPackage(SailDetailService.class.getPackage())
				.addPackage(SailDetailDTO.class.getPackage())
				.addPackage(SailDetail.class.getPackage())
				.addPackage(SailDetailBuilder.class.getPackage())
				.addPackage(SailDetailRemote.class.getPackage())
				.addPackage(SailDetailBean.class.getPackage())
				.addPackage(Sale.class.getPackage())
				.addPackage(Product.class.getPackage())
				.addPackage(HttpRequestContext.class.getPackage())
				.addPackage(FeaturesAndProperties.class.getPackage()).addPackage(ScannerListener.class.getPackage())
				.addPackage(AnnotationScannerListener.class.getPackage())
				.addPackage(AnnotationScannerListener.class.getPackage())
				.addPackage(BaseEntity.class.getPackage())
				.addPackage(TransferObjectAssembler.class.getPackage())
				.addPackage(Reflections.class.getPackage())
				.addPackage(Serializer.class.getPackage())
				.addPackage(Vfs.class.getPackage())
				.addPackage(Scanner.class.getPackage())
				.addPackage(Utils.class.getPackage())
				.addPackage(MetadataAdapter.class.getPackage())
				.addAsResource("META-INF/persistence.xml");

		return web;

	}

}