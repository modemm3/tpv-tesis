package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SaleDTO;

@Remote
public interface SaleRemote {
	
	public MessageResponseDTO save(SaleDTO saleDTO);
	public MessageResponseDTO update(SaleDTO saleDTO);
	public MessageResponseDTO delete(Integer idSave);
	public SaleDTO getSale(Integer idSave);
	public List<SaleDTO> getListSale();

}
