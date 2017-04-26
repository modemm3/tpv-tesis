package com.tesis.tpv.ejb.builder.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.jpa.BaseEntity;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuilderConfiguration {

	Class<? extends BaseEntity> entityClass();
	Class<? extends BaseDTO> dtoClass();

}
