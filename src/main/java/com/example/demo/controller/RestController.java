package com.example.demo.controller;

import com.example.demo.model.dto.Data;
import com.example.demo.model.service.dataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/account")
public class RestController {

	static Logger logger = LoggerFactory.getLogger(RestController.class);

	@Autowired
	dataService dataService;

	@PostMapping("/findById")
	@ApiOperation(value = "findById")
	public ResponseEntity<Map<String, Object>> findById(HttpServletResponse res, HttpServletRequest req, @RequestBody int input){
		logger.debug("controller");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			Optional<Data> data = dataService.findById(input);
			result.put("data", data.get());
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	@PostMapping("/findByData")
	@ApiOperation(value = "findByData")
	public ResponseEntity<Map<String, Object>> findByData(HttpServletResponse res, HttpServletRequest req, @RequestBody String input){
		logger.debug("controller");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			Optional<Data> data = dataService.findByData(input);
			result.put("data", data.get());
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	@PostMapping("/findAll")
	@ApiOperation(value = "findAll")
	public ResponseEntity<Map<String, Object>> findAll(HttpServletResponse res, HttpServletRequest req){
		logger.debug("controller");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			List<Data> data = dataService.findAll();
			result.put("data", data);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}


	private ResponseEntity<Map<String, Object>> handleSuccess(Map<String, Object> data) {
		data.put("status", true);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		logger.error("예외 발생 : ", e);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}