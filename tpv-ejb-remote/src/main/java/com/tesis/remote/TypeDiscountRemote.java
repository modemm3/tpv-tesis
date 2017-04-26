package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.TypeDiscountDTO;

@Remote
public interface TypeDiscountRemote {
	MessageResponseDTO save(final TypeDiscountDTO typeDiscountDTO);
	MessageResponseDTO delete(final Integer idTypeDiscount);
	MessageResponseDTO update(final TypeDiscountDTO typeDiscountDTO);
	TypeDiscountDTO getTypeDiscount(final Integer idTypeDiscount);
	List<TypeDiscountDTO> getListTypeDiscount();
	List<TypeDiscountDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	Integer numberRecords();
}
