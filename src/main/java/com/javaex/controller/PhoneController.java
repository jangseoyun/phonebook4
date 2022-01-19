package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	//필드 
	@Autowired
	private PhoneDao phoneDao;
	//생성자
	//메소드g,s
	
	//메소드 일반
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		System.out.println("phone/writeForm");
		//포워드할 주소
		return "writeForm";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {//저장
		
		System.out.println("phone/write");
		System.out.println(personVo);
		
		//저장
		phoneDao.personInsert(personVo);
		
		//리다이렉트 
		return "redirect:/phone/list";
		
	}
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		System.out.println("phone/list");
		
		//dao에서 리스트를 가져온다-> 필드에 넣어뒀음
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		
		//컨트롤러가 --> DS 데이터를 보낸다 (어트리뷰트에 담기는것)
		model.addAttribute("personList", personList);
		
		//jsp정보를 리턴한다(view)
		return "list";
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("personId") int personId) {
		
		System.out.println("phone/delete");
		
		phoneDao.personDelete(personId);
		
		return "redirect:/phone/list";
		
	}
	
	@RequestMapping(value="/updateForm", method = {RequestMethod.GET, RequestMethod.POST} )
	public String updateForm(@RequestParam("personId") int personId, Model model) {
		
		System.out.println("phone/updateForm");
		
		PersonVo personVo = phoneDao.getPerson(personId);
		
		model.addAttribute("personVo", personVo);
		
		return "updateForm";
	}
	
	@RequestMapping(value="/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		
		System.out.println("/phone/update");
		
		phoneDao.personUpdate(personVo);
		
		System.out.println("update : " +personVo);
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/test", method = {RequestMethod.GET, RequestMethod.POST})
	public String test(@RequestParam(value="name") String name, 
						@RequestParam(value="age") int age) {
		
		System.out.println(name);
		System.out.println(age);
		
		return "";
	}
	
	//파라미터 
	@RequestMapping(value="/view", method = {RequestMethod.GET, RequestMethod.POST})
	public String view(@RequestParam(value="no") int no) {
		
		System.out.println(no +"번글 가져오기");
		
		return "";
	}
	
	//PathVariable -> 파라미터가 아니다. {}위치는 상관없음
	@RequestMapping(value="/view/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String viewNo(@PathVariable("no") int no) {
		
		System.out.println(no +"번글 가져오기");
		
		return "";
	}
	
	//네이버 블로그 
	//localhost:8088/phonebook3/phone/aaa
	@RequestMapping(value="/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blog(@PathVariable("id") String id) {
		
		System.out.println(id + "님의 블로그 가져오기");
		
		return "";
	}
	
	/*
	@RequestMapping(value="/phone/write", method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {//파라미터를 따로 독립적으로 받아와야하는 경우 사용 
		
		System.out.println("phone/write");
		System.out.println(name+"/"+hp+"/"+ company);
		
		//저장
		PersonVo personVo = new PersonVo(name,hp,company);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		
		return "";
		
	}
	*/
	
	
	
	
}
