package com.pagination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pagination.Repository.UserRepository;
import com.pagination.dao.UserDao;
import com.pagination.entities.User;

@Controller
public class userContrller {

	@Autowired
	private UserDao udao;
	
	@Autowired
	private UserRepository userRepository;
	

// ===================================================================== Index page handler ====================================
	@GetMapping("index")
	public String index() {

		return "index";
	}

//=========================================================== add user handler ========================================
	@PostMapping("addUser")
	public String adduser(@ModelAttribute User u, Model m) {
		
		udao.addUser(u);
		return "redirect:showData";
	}
	
	
//====================================================== Show Data page handler ==============================================	
	/*
	 * @GetMapping("showData") public String showData(Model m ) {
	 * 
	 * List<User> Data = udao.allData();
	 * 
	 * m.addAttribute("Data", Data); return "showData"; }
	 */
	/*
	 * @GetMapping("showData") public String showData(Model m,
	 * 
	 * @RequestParam(value = "pageNumber", defaultValue = "1", required = false)
	 * Integer pageNumber,
	 * 
	 * @RequestParam(value = "pageSize", defaultValue = "5", required = false)
	 * Integer pageSize) {
	 * 
	 * List<User> Data = udao.allData(pageNumber, pageSize); m.addAttribute("Data",
	 * Data); return "showData"; }
	 */
	
	
	
	
	
	/*
	 * @GetMapping("/showData/{pageNumber}") public String
	 * showData(@PathVariable(value="pageNumber") int pageNumber, Model m) {
	 * 
	 * 
	 * int pageSize=5;
	 * 
	 * PageRequest p = PageRequest.of(pageNumber, pageSize);
	 * 
	 * Page<User> page = userRepository.findAll(p);
	 * 
	 * List<User> Data = page.getContent();
	 * 
	 * m.addAttribute("curruntPage", pageNumber); m.addAttribute("totalPages",
	 * page.getTotalPages()); m.addAttribute("totalItems", page.getTotalElements());
	 * m.addAttribute("Data", Data); return "showData";
	 * 
	 * }
	 */
	
	/*
	 * @GetMapping("user/{field}") public String findUserWithSorting(@PathVariable
	 * String field) {
	 * 
	 * udao.findUserWithSorting(field); return field;
	 * 
	 * }
	 */
	

	
	@GetMapping("showData")
	public String showData(Model model,
	                       @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
	                       @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {

	    Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
	    Page<User> page = userRepository.findAll(pageable);

	    model.addAttribute("Data", page.getContent());
	    model.addAttribute("totalPages", page.getTotalPages());
	    
	    System.out.println( page.getTotalPages());
	    
	    model.addAttribute("totalItems", page.getTotalElements());
	    System.out.println(page.getTotalElements());
	    model.addAttribute("currentPage", pageNumber);
	    
	    model.addAttribute("pageSize", pageSize);
	    System.out.println(pageNumber);

	    return "showData";
	}
	
	
	@GetMapping("user/{field}")
	public ResponseEntity<?> findUserWithSorting(@PathVariable String field) {
	    // Call the method from your data access object (udao) to find users with sorting
	    List<User> sortedUsers = udao.findUserWithSorting(field);
	    
	    // Return the sorted users as JSON response
	    return ResponseEntity.ok(sortedUsers);
	}
}
