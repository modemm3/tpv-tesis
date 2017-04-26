package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface EmployeeCategoryRemote {

	MessageResponseDTO save(final EmployeeCategoryDTO employeeCategoryDTO);
	MessageResponseDTO delete(final Integer idEmployeeCategory);
	MessageResponseDTO update(final EmployeeCategoryDTO employeeCategoryDTO);
	EmployeeCategoryDTO getEmployeeCategory(final Integer idEmployeeCategory);
	List<EmployeeCategoryDTO> getListEmployeeCategory();
	List<EmployeeCategoryDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	Integer numberRecords();
}
