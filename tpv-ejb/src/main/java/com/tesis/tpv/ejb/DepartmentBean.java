package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tesis.remote.DepartmentRemote;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Department;

@Stateless(mappedName="DepartmentBean")
@TransactionManagement(TransactionManagementType.CONTAINER)

public class DepartmentBean implements DepartmentRemote{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(DepartmentDTO departmentDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			Department department=new Department();
			department.setName(departmentDTO.getName());
			entityManager.persist(department);
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
	public MessageResponseDTO delete(Integer idDepartment) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		
		try{
			Department department=new Department();
			department=entityManager.find(department.getClass(), idDepartment);
			
			if(department!=null){
				entityManager.remove(department);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
				messageResponseDTO.setIdTransaction(idDepartment);
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro: "+ idDepartment);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO update(DepartmentDTO departmentDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		
		try{
			Department department=new Department();
			department=entityManager.find(department.getClass(), departmentDTO.getId());
			
			if(department!=null){
				department.setId(departmentDTO.getId());
				department.setName(departmentDTO.getName());
				entityManager.persist(department);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
				
		return messageResponseDTO;
	}

	@Override
	public DepartmentDTO getDepartment(Integer idDepartment) {
		Department department=new Department();
		DepartmentDTO departmentDTO=new DepartmentDTO();
		
		department=entityManager.find(department.getClass(), idDepartment);
		
		if(department!=null){
			departmentDTO=TransferObjectAssembler.getInstance().assembleTO(departmentDTO.getClass(), department);
		}
		
		return departmentDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentDTO> getListDepartment() {
		List<Department> departmentLst=new ArrayList<Department>();
		List<DepartmentDTO> departmentDTOLst=new ArrayList<DepartmentDTO>();
		DepartmentDTO departmentDTO=new DepartmentDTO();
		
		departmentLst=entityManager.createNamedQuery("Department.getAll").getResultList();
		
		for(Department department:departmentLst){
			departmentDTO=TransferObjectAssembler.getInstance().assembleTO(departmentDTO.getClass(), department);
			departmentDTOLst.add(departmentDTO);
		}
		
		return departmentDTOLst;
	}

	@Override
	public List<DepartmentDTO> getPagination(Integer pageInit, Integer pageEnd) {
		DepartmentDTO departmentDTO= new DepartmentDTO();
		List<DepartmentDTO> departmentDTOLst=new ArrayList<DepartmentDTO>();
		Query query=entityManager.createNamedQuery("Department.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<Department> departmentLst=query.getResultList();
		
		if(departmentLst!=null && departmentLst.size()>0){
			for(Department department:departmentLst){
				departmentDTO=TransferObjectAssembler.getInstance().assembleTO(departmentDTO.getClass(), department);
				departmentDTOLst.add(departmentDTO);
			}
		}
		return departmentDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("Department.getCount");
		count=((Long)query.getSingleResult()).intValue();
		return count;
	}

}
