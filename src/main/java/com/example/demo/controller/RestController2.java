package com.example.demo.controller;

import com.example.demo.model.dto.Data;
import com.example.demo.model.dto.Member;
import com.example.demo.model.dto.Team;
import com.example.demo.model.service.MemberService;
import com.example.demo.model.service.TeamService;
import com.example.demo.model.service.dataService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = { "*" })
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/member")
public class RestController2 {

	static Logger logger = LoggerFactory.getLogger(RestController2.class);

	@Autowired
	TeamService tService;

	@Autowired
	MemberService mService;

	@PostMapping("/findAllTeam")
	@ApiOperation(value = "findAll")
	public ResponseEntity<Map<String, Object>> findAllTeam(HttpServletResponse res, HttpServletRequest req){
		logger.debug("findAll");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			List<Team> teams = tService.findAll();
			result.put("result", teams);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}

	@PostMapping("/findAllMember")
	@ApiOperation(value = "findAll")
	public ResponseEntity<Map<String, Object>> findAllMember(HttpServletResponse res, HttpServletRequest req){
		logger.debug("findAll");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			List<Member> members = mService.findAll();
			result.put("result", members);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}

	@PostMapping("/addMember")
	@ApiOperation(value = "findAll")
	public ResponseEntity<Map<String, Object>> addMember(HttpServletResponse res, HttpServletRequest req){
		logger.debug("findAll");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {



			List<Member> members = mService.findAll();
			result.put("result", members);
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