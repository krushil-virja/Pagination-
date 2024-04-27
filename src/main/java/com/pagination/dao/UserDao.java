package com.pagination.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pagination.Repository.UserRepository;
import com.pagination.entities.User;

@Service
public class UserDao {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User u) {

		return userRepository.save(u);
	}

	  public List<User> allData(){
	  
	  return userRepository.findAll();
	  
	  }
	 

	// pring boot provide a pagination in jpaRepositty we just need to implement
	// pagable object in our method
	
	  
	  public List<User> allData(Integer pageNumber, Integer pageSize) {
		    // Adjust pageNumber to start from 0 if pageSize is greater than 1
		    if (pageSize > 1 && pageNumber > 0) {
		        pageNumber = pageNumber - 1;
		    }

		    PageRequest p = PageRequest.of(pageNumber, pageSize);
		    Page<User> dataList = userRepository.findAll(p);
		    List<User> allUser = dataList.getContent();
		    return allUser;
		}
	  
	  
	  
	  public List<User> findUserWithSorting(String field){
		  
		  return userRepository.findAll(Sort.by(Sort.Direction.ASC , field));
	  }
}
