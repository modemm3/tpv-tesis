package com.tesis.tpv.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tesis.remote.EmployeeRemote;
import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Path ("employeeService")
public class EmployeeService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	@EJB (mappedName="EmployeeBean")
	EmployeeRemote employee;
	
	@Path ("/saveEmployee")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO saveEmployee(EmployeeDTO employeeDTO){
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		messageResponseDTO=employee.save(employeeDTO);
		return messageResponseDTO;
	}
	
	@Path ("/updateEmployee")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO updateEmployee(EmployeeDTO employeeDTO){
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		messageResponseDTO=employee.update(employeeDTO);
		return messageResponseDTO;
	}
	
	@Path ("/deleteEmployee/{idEmployee}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public MessageResponseDTO deleteEmployee(@PathParam("idEmployee")Integer idEmployee){
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		messageResponseDTO=employee.delete(idEmployee);
		return messageResponseDTO;
	}

	@Path ("/getEmployee/{idEmployee}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public EmployeeDTO getEmployee(@PathParam("idEmployee")Integer idEmployee){
		EmployeeDTO employeeDTO=new EmployeeDTO();
		employeeDTO=employee.getEmployee(idEmployee);
		return employeeDTO;
	}
	
	@Path ("/getListEmployee")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<EmployeeDTO> getListEmployee(){
		List<EmployeeDTO> employeeLst=new ArrayList<EmployeeDTO>();
		employeeLst=employee.getListEmployee();
		return employeeLst;
	}

}
