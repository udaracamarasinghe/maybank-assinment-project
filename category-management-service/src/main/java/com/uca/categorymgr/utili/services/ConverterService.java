package com.uca.categorymgr.utili.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.dtos.CustomerDto;
import com.uca.categorymgr.entities.DBOneCustomer;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;

	public CustomerDto convertToDtoDBOne(DBOneCustomer post) {
		CustomerDto customerDto = modelMapper.map(post, CustomerDto.class);
		// customerDto.setSubmissionDate(post.getSubmissionDate(),
		// userService.getCurrentUser().getPreference().getTimezone());
		return customerDto;
	}

	public DBOneCustomer convertToDtoDBOne(CustomerDto makeCustomer) {
		DBOneCustomer customer = modelMapper.map(makeCustomer, DBOneCustomer.class);
		return customer;
	}

}
