package com.discount.demo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
