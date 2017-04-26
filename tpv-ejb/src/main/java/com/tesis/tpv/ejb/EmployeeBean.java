package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.tesis.remote.EmployeeRemote;
import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Employee;
import com.tesis.tpv.jpa.EmployeeCategory;

@Stateless (mappedName="EmployeeBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class EmployeeBean implements EmployeeRemote {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Employee employee=new Employee();
		Department department=new Department();
		EmployeeCategory employeeCategory=new EmployeeCategory();
		
		try{
			department.setId(employeeDTO.getDepartmentId());
			employeeCategory.setId(employeeDTO.getEmployeeCategoryId());
			
			employee.setAddress(employeeDTO.getAddress());
			employee.setAdmissionDate(employeeDTO.getAdmissionDate());
			employee.setAge(employeeDTO.getAge());
			employee.setCellPhone(employeeDTO.getCellPhone());
			employee.setDepartment(department);
			employee.setEmployeeCategory(employeeCategory);
			employee.setMaternalName(employeeDTO.getMaternalName());
			employee.setName(employeeDTO.getName());
			employee.setPaternalName(employeeDTO.getPaternalName());
			employee.setTelephone(employeeDTO.getTelephone());
			
			entityManager.persist(employee);
			messageResponseDTO.setCode(1);
			messageResponseDTO.setMessage("success");
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO update(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Employee employee=new Employee();
		Department department=new Department();
		EmployeeCategory employeeCategory=new EmployeeCategory();
		
		try{
			employee=entityManager.find(employee.getClass(), employeeDTO.getId());
			
			if(employee!=null){
				employee.setId(employeeDTO.getId());
				employee.setAddress(employeeDTO.getAddress());
				employee.setAdmissionDate(employeeDTO.getAdmissionDate());
				employee.setAge(employeeDTO.getAge());
				employee.setCellPhone(employeeDTO.getCellPhone());
				
				department.setId(employeeDTO.getDepartmentId());
				employee.setDepartment(department);
				
				employeeCategory.setId(employeeDTO.getEmployeeCategoryId());
				employee.setEmployeeCategory(employeeCategory);
				
				employee.setMaternalName(employeeDTO.getMaternalName());
				employee.setName(employeeDTO.getName());
				employee.setPaternalName(employeeDTO.getPaternalName());
				employee.setTelephone(employeeDTO.getTelephone());
				entityManager.persist(employee);
				
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("El registro con id: "+ employeeDTO.getId()+ " no se encuentra");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idEmployee) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Employee employee=new Employee();
		
		try{
			employee=entityManager.find(employee.getClass(), idEmployee);
			
			if(employee!=null){
				entityManager.remove(employee);
				
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("El registro con id: "+ idEmployee + " no se encuentra");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public EmployeeDTO getEmployee(Integer idEmployee) {
		// TODO Auto-generated method stub
		EmployeeDTO employeeDTO=new EmployeeDTO();
		Employee employee=new Employee();
		
		employee=entityManager.find(employee.getClass(), idEmployee);
		
		if(employee!=null){
			employeeDTO=TransferObjectAssembler.getInstance().assembleTO(employeeDTO.getClass(), employee);
		}
		
		return employeeDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeDTO> getListEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeDTO> employeeDTOLst=new ArrayList<EmployeeDTO>();
		List<Employee> employeeLst=new ArrayList<Employee>();
		EmployeeDTO employeeDTO=new EmployeeDTO();
		
		employeeLst=entityManager.createQuery("SELECT e FROM Employee e").getResultList();
		
		if(employeeLst!=null && employeeLst.size()>0){
			for(Employee employee:employeeLst){
				employeeDTO=TransferObjectAssembler.getInstance().assembleTO(employeeDTO.getClass(), employee);
				employeeDTOLst.add(employeeDTO);
			}
		}
		
		return employeeDTOLst;
	}

}
