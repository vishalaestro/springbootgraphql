package com.viz.graphql.springbootgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viz.graphql.springbootgraphql.Exception.ServiceException;
import com.viz.graphql.springbootgraphql.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{
	public Phone findByNumber(String number) throws ServiceException;
}
