package com.uca.categorymgr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.dtos.MerchantDto;
import com.uca.categorymgr.entities.Merchant;
import com.uca.categorymgr.repositories.MerchantRepository;
import com.uca.categorymgr.utili.services.ConverterService;
import com.uca.categorymgr.utili.services.HashingService;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private HashingService hashingService;

	public Boolean isavilable(Long merId) {
		return merchantRepository.existsById(merId);
	}

	public MerchantDto findMerchantById(Long merId) {
		Optional<Merchant> findById = merchantRepository.findById(merId);

		if (!findById.isPresent()) {
			return null;
		}

		return converterService.convert(findById.get());
	}

}
