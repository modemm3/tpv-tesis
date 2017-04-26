package com.tesis.remote;

import java.util.List;
import javax.ejb.Remote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.PaymentTypeDTO;

@Remote
public interface PaymentTypeRemote {
	
	public MessageResponseDTO save(PaymentTypeDTO paymentTypeDTO);
	public MessageResponseDTO delete(Integer idPaymentType);
	public MessageResponseDTO update(PaymentTypeDTO paymentTypeDTO);
	public PaymentTypeDTO getPaymentType(Integer idPaymentType);
	public List<PaymentTypeDTO> getListPaymentType();
	public List<PaymentTypeDTO> getPagination(Integer initialRecord, Integer finalRecord);
	public Integer numberRecords();
}
