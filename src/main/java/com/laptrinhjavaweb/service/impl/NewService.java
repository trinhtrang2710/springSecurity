package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private INewDAO newDao;

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> list = new ArrayList<>();
		List<NewEntity> entityList = newRepository.findAll(pageable).getContent();
		for (NewEntity item: entityList){
			NewDTO newDTO = new NewDTO();
			newDTO.setTitle(item.getTitle());
			newDTO.setShortDescription(item.getShortDescription());
			list.add(newDTO);
		}
		return list;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

}
