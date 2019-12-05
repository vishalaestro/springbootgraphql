package com.viz.graphql.springbootgraphql;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viz.graphql.springbootgraphql.entity.Call;
import com.viz.graphql.springbootgraphql.entity.Person;
import com.viz.graphql.springbootgraphql.entity.Phone;
import com.viz.graphql.springbootgraphql.entity.PhoneType;
import com.viz.graphql.springbootgraphql.repository.PersonRepository;

@SpringBootApplication
public class SpringbootGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGraphqlApplication.class, args);
	}
	
	@Autowired
	PersonRepository personRepository;
	
	@PostConstruct
	private void insertBaseData() {
		
		for (int i =0;i<50;i++) {
			Person person = new Person();
			person.setName("User"+i+1);
			person.setNickName("Nickname"+i+1);
			person.setAddress("Address"+i+1);
			person.setCreatedOn(new Date());
			person.setVersion(1);
			
			Phone phone = new Phone();
			phone.setNumber("988458787"+i);
			phone.setType(PhoneType.MOBILE);
			phone.setPerson(person);
			
			person.getPhones().add(phone);
			
			Call call1 = new Call();
			call1.setDuration(30);
			call1.setPhone(phone);
			call1.setTimestamp(new Date());
			
			Call call2 = new Call();
			call2.setDuration(20);
			call2.setPhone(phone);
			call2.setTimestamp(new Date());
			
			phone.getCalls().add(call1);
			phone.getCalls().add(call2);
			personRepository.save(person);
		}
		
	}

}
