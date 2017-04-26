package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface CategoryRemote {
	
	public MessageResponseDTO save(final CategoryDTO categoryDTO);
	public MessageResponseDTO delete(final Integer idCategory);
	public MessageResponseDTO update(final CategoryDTO categoryDTO);
	public CategoryDTO getCategory(final Integer idCategory);
	/*MessageResponseDTO getCategory(Integer idCategory);*/
	public List<CategoryDTO> getList();
	public List<CategoryDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	public Integer numberRecords();
}
