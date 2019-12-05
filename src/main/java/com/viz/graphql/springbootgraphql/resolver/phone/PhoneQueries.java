package com.viz.graphql.springbootgraphql.resolver.phone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viz.graphql.springbootgraphql.Exception.ServiceException;
import com.viz.graphql.springbootgraphql.entity.Phone;
import com.viz.graphql.springbootgraphql.repository.PhoneRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Service
public class PhoneQueries {

	@Autowired
	PhoneRepository phoneRepository;
	
	@GraphQLQuery
	public List<Phone> getAllConnections() throws ServiceException {
		return phoneRepository.findAll();
	}
	
	@GraphQLQuery
	public Phone getConnectionDetails(@GraphQLArgument(name = "phoneNumber") String number) throws ServiceException {
		if(number==null || number.isEmpty()) {
			throw new ServiceException ("Invalid Number",null);
		}
		return phoneRepository.findByNumber(number);
	}
}
