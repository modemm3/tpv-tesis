package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.DiscountDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface DiscountRemote {
	
	MessageResponseDTO save(final DiscountDTO discountDTO);
	MessageResponseDTO update(final DiscountDTO discountDTO);
	MessageResponseDTO delete(final Integer idDiscount);
	DiscountDTO getDiscount(final Integer idDiscount);
	List<DiscountDTO> getListDiscount();
	List<DiscountDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	Integer numberRecords();
}
