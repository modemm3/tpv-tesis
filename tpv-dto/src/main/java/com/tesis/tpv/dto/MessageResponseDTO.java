package com.tesis.tpv.dto;

public class MessageResponseDTO implements BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idTransaction;
	private int code;
	private String message;
	
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageResponseDTO [idTransaction=");
		builder.append(idTransaction);
		builder.append(", code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
