package com.marco.test.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marco.test.model.BalanceResponsePayload;
import com.marco.test.model.TransactionResponsePayload;
import com.marco.test.repository.BalanceResponseRepository;
import com.marco.test.repository.TransactionRepository;

@Controller
public class AccountController {
	
	@Autowired
	BalanceResponseRepository br;
	
	@Autowired
	TransactionRepository tr;
	
	@GetMapping("/")
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		session.setAttribute("utente_log", "Marco");
		mv.addObject("utente_log", session.getAttribute("utente_log"));
		return mv;
	}
	
	@GetMapping("/balance")
	public ModelAndView balance(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<BalanceResponsePayload> balanceList = br.findAll();
		System.out.println(balanceList);
		mv.setViewName("balance");
		mv.addObject("utente_log", session.getAttribute("utente_log"));
		mv.addObject("balanceList", balanceList);
		return mv;
	}
	
	@GetMapping("/transactionsForm")
	public ModelAndView transactionsForm(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("transactionsform");
		return mv;
	}
	
	@GetMapping("/transactions")
	public ModelAndView transactions(HttpSession session) {
		List<TransactionResponsePayload> transactionsList = tr.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("transactionsList", transactionsList);
		mv.setViewName("transactions");
		return mv;
	}
	
}
