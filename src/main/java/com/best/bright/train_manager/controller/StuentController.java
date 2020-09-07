package com.best.bright.train_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.bright.train_manager.dto.StudentInfo;
import com.best.bright.train_manager.dto.StudentRegisterDTO;
import com.best.bright.train_manager.model.Student;
import com.best.bright.train_manager.repository.StudentRepository;
import com.best.bright.train_manager.services.StudentManagerInterface;

@Controller
public class StuentController {
	@Autowired
	private StudentRepository studentRepositroy;
	@Autowired
	private StudentManagerInterface studentManager;
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam String name
	      , @RequestParam String rollNo) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

	    Student n = new Student();
	    n.setName(name);
	    n.setRollNo(rollNo);
	    studentRepositroy.save(n);
	    return "Saved";
	  }
	@GetMapping("/call")
	public @ResponseBody String test() {
		return "Hello Test";
	}
	@GetMapping("/students")
	public @ResponseBody List<Student> getStudentList(){
		return studentRepositroy.findAll();
	}
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("student", new Student());
		return "create_student";
	}
	@PostMapping("/save_student")
	public String saveStudent(@ModelAttribute("student")Student std,Model model) {
		
		studentRepositroy.save(std);
		
		model.addAttribute("students", studentRepositroy.findAll());
		return "student_list";
	}
	@GetMapping("/ajax_save")
	public String ajax_save() {
		return "ajax_save";
	}
	@PostMapping("/ajax_save")
	public @ResponseBody Student save_student(@RequestBody Student std) {
		Student student=studentRepositroy.save(std);
		return student;
	}
	@DeleteMapping("/delete/{id}")
	public @ResponseBody boolean delete_student(@PathVariable("id")Long id) {
		Student std=studentRepositroy.findById(id).get();
		studentRepositroy.delete(std);
		return true;
	}
	@GetMapping("/std_list/{name}")
	public @ResponseBody List<Student> getStudentListByName(@PathVariable(value="name") String name){
		List<Student> stdList= studentRepositroy.findByNameLike(name);
		return stdList;
	}
	
	@GetMapping("/std_list_by_rollno/{rollNo}")
	public @ResponseBody List<Student> getStudentListByRollNo(@PathVariable(value="rollNo") String name){
		List<Student> stdList= studentRepositroy.findByRollNo(name);
		return stdList;
	}
	@GetMapping("/std_list_by_age/{from}/{to}")
	public @ResponseBody List<Student> getStudentListByRollNo(@PathVariable(value="from") int from,@PathVariable(value="to")int to){
		List<Student> stdList= studentRepositroy.findByAgeBetween(from, to);
		return stdList;
	}
	@GetMapping("/find_by_name/{name}")
	public @ResponseBody Student findByName(@PathVariable String name) {
		return studentRepositroy.findByName(name);
	}
	@GetMapping("/all_student")
	@ResponseBody
	public List<StudentInfo> allStudent(){
		return studentManager.getStudentInfo();
	}
	@GetMapping("/register_students")
	public @ResponseBody List<StudentRegisterDTO> getRegisterStudentList(){
		return studentManager.getAllStudentList();
	}
}
