package com.uca.categorymgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.categorymgr.entities.Merchant;
import com.uca.categorymgr.entities.DBOneCustomer;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
