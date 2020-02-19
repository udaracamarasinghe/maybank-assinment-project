package com.uca.categorymgr.utili.services;

import java.util.Base64;

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
		return Base64.getEncoder().encodeToString(string.getBytes());
	}

	public String hash(Long longV) {
		return hash(String.valueOf(longV));
	}

	public Long decode(String string) {
		string = new String(Base64.getDecoder().decode(string.getBytes()));

		return Long.valueOf(string);
	}
}
