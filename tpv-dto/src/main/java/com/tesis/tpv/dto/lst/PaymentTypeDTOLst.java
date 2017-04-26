package com.tesis.tpv.dto.lst;

import java.util.List;

import com.tesis.tpv.dto.PaymentTypeDTO;

public class PaymentTypeDTOLst {
	private Integer initialPage;
	private Integer endPage;
	private Integer numberRecords;
	private List<PaymentTypeDTO> paymentLst;
	
	public Integer getNumberRecords() {
		return numberRecords;
	}
	public void setNumberRecords(Integer numberRecords) {
		this.numberRecords = numberRecords;
	}
	public Integer getInitialPage() {
		return initialPage;
	}
	public void setInitialPage(Integer initialPage) {
		this.initialPage = initialPage;
	}
	public Integer getEndPage() {
		return endPage;
	}
	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}
	public List<PaymentTypeDTO> getPaymentLst() {
		return paymentLst;
	}
	public void setPaymentLst(List<PaymentTypeDTO> paymentLst) {
		this.paymentLst = paymentLst;
	}

}
