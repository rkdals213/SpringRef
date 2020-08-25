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
	@ApiOperation(value = "findAllTeam")
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
	@ApiOperation(value = "findAllMember")
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
	@ApiOperation(value = "addMember")
	public ResponseEntity<Map<String, Object>> addMember(HttpServletResponse res, HttpServletRequest req, @RequestBody Member member){
		logger.debug("findAll");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			System.out.println(member.getTeam().getId());
			System.out.println(member.getTeam().getName());
			Team team = tService.findById(member.getTeam().getId());
//			Team team = tService.findByName(member.getTeam().getName());
			team.addMember(member);

			Member mem = mService.addMember(member);
			result.put("result", mem);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}

	@DeleteMapping("/dropTeam/{team_id}")
	@ApiOperation(value = "dropTeam")
	public ResponseEntity<Map<String, Object>> dropTeam(HttpServletResponse res, HttpServletRequest req, @PathVariable int team_id){
		logger.debug("findAll");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			tService.deleteById(team_id);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}

	@DeleteMapping("/dropMember/{member_id}")
	@ApiOperation(value = "dropTeam")
	public ResponseEntity<Map<String, Object>> dropMember(HttpServletResponse res, HttpServletRequest req, @PathVariable int member_id){
		logger.debug("dropMember");
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> result = new HashMap<>();
		try {
			mService.deleteById(member_id);
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