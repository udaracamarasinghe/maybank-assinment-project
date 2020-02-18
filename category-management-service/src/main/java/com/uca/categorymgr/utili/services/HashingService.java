package com.uca.categorymgr.utili.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class HashingService {

	public String hash(String string) {
		return DigestUtils.sha256Hex(string);
	}
}
