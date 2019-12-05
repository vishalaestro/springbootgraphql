package com.viz.graphql.springbootgraphql.resolver.person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viz.graphql.springbootgraphql.Exception.ServiceException;
import com.viz.graphql.springbootgraphql.entity.Person;
import com.viz.graphql.springbootgraphql.entity.Phone;
import com.viz.graphql.springbootgraphql.repository.PersonRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Service
public class PersonQueries  {
	
	@Autowired
	PersonRepository personRepository;

	@GraphQLQuery
	public List<Person> getPersons() throws ServiceException {
		return personRepository.findAll();
	}
	
	@GraphQLQuery
	public Person getPersonByName(@GraphQLArgument(name = "name") String name) throws ServiceException {
		return personRepository.findByName(name);
	}
	
	@GraphQLQuery
	public Person getPersonById(@GraphQLArgument(name = "id") Long id) throws ServiceException {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			return personRepository.findById(id).get();
		}else {
			throw new ServiceException("Person Does not Exist", null);
		}
	}
	
	@GraphQLQuery
	public List<Phone> getConnectionsForPerson(@GraphQLArgument(name="id") Long id) throws ServiceException {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			return personRepository.findById(id).get().getPhones();
		}else {
			 throw new ServiceException("No Connection for this Person", null);
		}
		
	}
}
