package com.example.PAC_07;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

	long countByLastName(String lastname);

	void deleteByLastName(String lastname);
}