package com.hasindu.redissample.repository;

import com.hasindu.redissample.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hasindu_d
 */
@Repository
public interface UserRepository extends CrudRepository<User,String> {

}
