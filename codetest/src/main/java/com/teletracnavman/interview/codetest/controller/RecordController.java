package com.teletracnavman.interview.codetest.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teletracnavman.interview.codetest.entity.RecordEntity;
import com.teletracnavman.interview.codetest.model.RecordConvertor;
import com.teletracnavman.interview.codetest.repository.RecordRepository;

@Controller
@RequestMapping(path = "/")
public class RecordController {

	private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private RecordConvertor recordConvertor;

	@RequestMapping(path = "/device", method = RequestMethod.POST)
	public ResponseEntity<String> getDeviceId(@RequestBody Map<String, Object> payload) 
	{

		RecordEntity recordEn = recordConvertor.validate(payload);
		if (recordEn != null)
			recordRepository.save(recordEn);
		return ResponseEntity.ok((String)payload.get("DeviceId"));
	}

	@RequestMapping(path = "/nocontent", method = RequestMethod.POST)
	public  ResponseEntity<String> noContent(@RequestBody Map<String, Object> payload) 
	{
		//Not sure if the payload needs to be saved, saved anyway.
		RecordEntity recordEn = recordConvertor.validate(payload);
		if (recordEn != null)
			recordRepository.save(recordEn);

		return  ResponseEntity.noContent().build();

	}

	@RequestMapping(path = "/echo", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, Object> payload) {

		RecordEntity recordEn = recordConvertor.validate(payload);
		if (recordEn != null)
			recordRepository.save(recordEn);
		return ResponseEntity.ok(payload);

	}
}
