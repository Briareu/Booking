package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.service.IOrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@
}
