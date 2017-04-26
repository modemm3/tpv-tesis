package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProductDTO;

@Remote
public interface ProductRemote {
	
	MessageResponseDTO save(final ProductDTO productDTO);
	MessageResponseDTO update(final ProductDTO productDTO);
	MessageResponseDTO delete(final Integer idProduct);
	ProductDTO getProduct(final Integer idProduct);
	ProductDTO getProductByCode(final String code);
	List<ProductDTO> getProductList();
	List<ProductDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	Integer numberRecords();
	
}
