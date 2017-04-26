package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface EmployeeRemote {
	
	public MessageResponseDTO save(EmployeeDTO employeeDTO);
	public MessageResponseDTO update(EmployeeDTO employeeDTO);
	public MessageResponseDTO delete(Integer idEmployee);
	public EmployeeDTO getEmployee(Integer idEmployee);
	public List<EmployeeDTO> getListEmployee();

}
