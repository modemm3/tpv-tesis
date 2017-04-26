package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SailDetailDTO;

@Remote
public interface SailDetailRemote {
	
	public MessageResponseDTO save(SailDetailDTO sailDetailDTO);
	public MessageResponseDTO update(SailDetailDTO sailDetailDTO);
	public MessageResponseDTO delete(Integer idSailDetail);
	public SailDetailDTO getSailDetail(Integer idSailDetail);
	public List<SailDetailDTO> getListSailDetail();

}
