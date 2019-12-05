package com.viz.graphql.springbootgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viz.graphql.springbootgraphql.Exception.ServiceException;
import com.viz.graphql.springbootgraphql.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	public Person findByName(String name) throws ServiceException;
}
