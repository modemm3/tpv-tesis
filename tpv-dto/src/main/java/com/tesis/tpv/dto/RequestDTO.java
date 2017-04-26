package com.tesis.tpv.dto;

public class RequestDTO{

	private Integer initPage;
	private Integer endPage;
	private String dataTO;
	
	public Integer getInitPage() {
		return initPage;
	}
	public void setInitPage(Integer initPage) {
		this.initPage = initPage;
	}
	public Integer getEndPage() {
		return endPage;
	}
	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}
	public String getDataTO() {
		return dataTO;
	}
	public void setDataTO(String dataTO) {
		this.dataTO = dataTO;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestDTO [initPage=");
		builder.append(initPage);
		builder.append(", endPage=");
		builder.append(endPage);
		builder.append(", dataTO=");
		builder.append(dataTO);
		builder.append("]");
		return builder.toString();
	}

}
