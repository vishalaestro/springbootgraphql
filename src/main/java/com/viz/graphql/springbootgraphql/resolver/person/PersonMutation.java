package com.viz.graphql.springbootgraphql.resolver.person;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viz.graphql.springbootgraphql.Exception.ServiceException;
import com.viz.graphql.springbootgraphql.entity.Person;
import com.viz.graphql.springbootgraphql.entity.Phone;
import com.viz.graphql.springbootgraphql.repository.PersonRepository;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Service
public class PersonMutation  {

	@Autowired
	PersonRepository personRepository;
	
	@GraphQLMutation
	public Person addConnection (@GraphQLNonNull Person person) throws ServiceException {
		validatePersonObject(person);
		return personRepository.save(person);
	}
	
	
	private void validatePersonObject(Person person) throws ServiceException {
		if(person.getAddress()==null) {
			throw new ServiceException("Address is mandatory", null);
		}
		if(person.getName()==null) {
			throw new ServiceException("Name is mandatory", null);
		}
		
		if(person.getPhones()==null) {
			throw new ServiceException("Connection Details are mandatory", null);
		}
		
		StringJoiner joiner = new StringJoiner("\n");
		List<Phone> connections = person.getPhones();
		for(Phone phone : connections) {
			if(phone.getNumber()==null) {
				joiner.add("Number is mandatory for "+phone.getType());
			}
		}
		if(!joiner.toString().isEmpty()) {
			throw new ServiceException(joiner.toString(), null);
		}
	}
}
