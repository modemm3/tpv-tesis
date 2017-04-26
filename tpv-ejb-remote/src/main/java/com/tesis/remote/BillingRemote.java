package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface BillingRemote {
	
	public MessageResponseDTO save(BillingDTO billingDTO);
	public MessageResponseDTO update(BillingDTO billingDTO);
	public MessageResponseDTO delete(Integer idBilling);
	public BillingDTO getBilling(Integer idBilling);
	public List<BillingDTO> getListBilling();

}
