package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface DepartmentRemote {
	MessageResponseDTO save(final DepartmentDTO departmentDTO);
	MessageResponseDTO delete(final Integer idDepartment);
	MessageResponseDTO update(final DepartmentDTO departmentDTO);
	DepartmentDTO getDepartment(final Integer idDepartment);
	List<DepartmentDTO> getListDepartment();
	List<DepartmentDTO> getPagination(final Integer pageInit, final Integer pageEnd);
	Integer numberRecords();
}
