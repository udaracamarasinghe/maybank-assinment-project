package com.uca.categorymgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.categorymgr.entities.Category;
import com.uca.categorymgr.entities.DBOneCustomer;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
