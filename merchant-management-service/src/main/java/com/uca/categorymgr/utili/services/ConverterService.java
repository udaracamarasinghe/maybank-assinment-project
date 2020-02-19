package com.uca.categorymgr.utili.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.dtos.MerchantDto;
import com.uca.categorymgr.entities.Merchant;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;

	public MerchantDto convert(Merchant merchant) {
		MerchantDto merchantDto = modelMapper.map(merchant, MerchantDto.class);
		return merchantDto;
	}

}
