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
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		System.out.println("phoneDao.getPersonList()실행");
		
		//dao에서 리스트를 가져온다-> 필드에 넣어뒀음
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		
		//컨트롤러가 --> DS 데이터를 보낸다 (어트리뷰트에 담기는것)
		model.addAttribute("personList", personList);
		
		//jsp정보를 리턴한다(view)
		return "list";
	}
	
	
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
	
	@RequestMapping(value="/write2", method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {//저장
		
		System.out.println("phone/write2");

		//저장
		phoneDao.personInsert2(name, hp, company);
		
		//리다이렉트 
		return "redirect:/phone/list";
		
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
	

	
	
}
