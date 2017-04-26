package com.tesis.tpv.ejb.builder.config;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.jpa.BaseEntity;

public abstract class AbstractDTOBuilder {

	public abstract BaseDTO createDTO(final BaseEntity entity);
}
