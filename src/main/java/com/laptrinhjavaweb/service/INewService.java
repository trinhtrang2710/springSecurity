package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.model.NewModel;
import org.springframework.data.domain.Pageable;

public interface INewService {
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem();
}
