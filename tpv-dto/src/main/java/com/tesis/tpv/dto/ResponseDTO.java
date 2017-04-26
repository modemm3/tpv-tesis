package com.tesis.tpv.dto;

import java.io.Serializable;
public class ResponseDTO<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pageInit;
	private Integer pageEnd;
	private Integer numberRecords;
	private Integer numberPage;
	private String dtoLst;
	private MessageResponseDTO messageResponseDTO;

	public Integer getPageInit() {
		return pageInit;
	}
	public void setPageInit(Integer pageInit) {
		this.pageInit = pageInit;
	}
	public Integer getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(Integer pageEnd) {
		this.pageEnd = pageEnd;
	}
	public Integer getNumberRecords() {
		return numberRecords;
	}
	public void setNumberRecords(Integer numberRecords) {
		this.numberRecords = numberRecords;
	}
	public MessageResponseDTO getMessageResponseDTO() {
		return messageResponseDTO;
	}
	public void setMessageResponseDTO(MessageResponseDTO messageResponseDTO) {
		this.messageResponseDTO = messageResponseDTO;
	}
	public String getDtoLst() {
		return dtoLst;
	}
	public void setDtoLst(String dtoLst) {
		this.dtoLst = dtoLst;
	}
	public Integer getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
	}

}
