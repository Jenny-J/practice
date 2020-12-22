package com.teletracnavman.interview.codetest.model;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.teletracnavman.interview.codetest.entity.RecordEntity;

@Component
public class RecordConvertor {
	
	private static final Logger logger = LoggerFactory.getLogger(RecordConvertor.class);
	
	//Assumed validation rules
	//1. RecordType, DeviceID, EventDateTime are mandatory fields, EventDateTime value has to be a valid timestamp.
	//2. RecordType length limit 10, DeviceId length limit 20, FieldB length limit 10
	//3. FieldB value has to be a valid Integer or will set to null
	//4. FieldC value has to be a valid Double or will set to null
	public RecordEntity validate (Map<String, Object> payload)
	{
		String recordType = payload.get("RecordType") instanceof String?(String)payload.get("RecordType"):String.valueOf(payload.get("RecordType"));
		String deviceId = payload.get("DeviceId") instanceof String?(String)payload.get("DeviceId"):String.valueOf(payload.get("DeviceId"));
		String eventDateTime = payload.get("EventDateTime") instanceof String?(String)payload.get("EventDateTime"):String.valueOf(payload.get("EventDateTime"));
		Integer fieldA = payload.get("FieldA") instanceof Integer?(Integer)payload.get("FieldA"):null;
		String fieldB = payload.get("FieldB") instanceof String?(String)payload.get("FieldB"):String.valueOf(payload.get("FieldB"));
		Double fieldC = payload.get("FieldC") instanceof Double?(Double)payload.get("FieldC"):null;
		Timestamp edt = null;
		if (!StringUtils.hasLength(recordType) || !StringUtils.hasLength(deviceId) || !StringUtils.hasLength(eventDateTime))
		{
			logger.warn("Validation failed:" + payload);
			return null;
		}
		try{
			TimeZone utc = TimeZone.getTimeZone("UTC");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(utc);
			edt = new Timestamp(sdf.parse((String)eventDateTime).getTime());
		}catch (Exception ex){
			logger.warn("Validation failed:" + payload);
			return null;
		}
		if (recordType.length() > 10 || deviceId.length()>20 || (StringUtils.hasLength(fieldB) && fieldB.length() > 10))
		{
			logger.warn("Validation failed:" + payload);
			return null;			
		}
		RecordEntity recordEntity = new RecordEntity(null, recordType, deviceId, edt, fieldA, fieldB, fieldC);
		return recordEntity;
	
	}

}
