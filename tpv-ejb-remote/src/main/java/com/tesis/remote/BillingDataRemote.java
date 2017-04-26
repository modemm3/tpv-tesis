package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface BillingDataRemote {
	public MessageResponseDTO save(final BillingDataDTO billingDataDTO);
	public MessageResponseDTO update(final BillingDataDTO billingDataDTO);
	public MessageResponseDTO delete(final Integer idBillingData);
	public BillingDataDTO getBillingData(final Integer idBillingData);
	public List<BillingDataDTO> getListBillingData();
	public List<BillingDataDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	public Integer numberRecords();
}
