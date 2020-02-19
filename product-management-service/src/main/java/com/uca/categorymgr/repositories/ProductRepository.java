package com.uca.categorymgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.uca.categorymgr.entities.Product;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

}
