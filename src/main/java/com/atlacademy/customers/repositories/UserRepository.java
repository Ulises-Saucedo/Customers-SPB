package com.atlacademy.customers.repositories;

import com.atlacademy.customers.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE email LIKE %:email% OR address LIKE %:address%")
    List<User> findByEmailOrAddress(@Param("email") String email, @Param("address") String address);

    @Query("SELECT u FROM User u WHERE email = :email AND password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
