package com.teletracnavman.interview.codetest.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table (name="record")
public class RecordEntity {
	
	public RecordEntity(Long recordId, String recordType, String deviceId, Timestamp eventDateTime, Integer fieldA,
			String fieldB, Double fieldC) {
		super();
		this.recordId = recordId;
		this.recordType = recordType;
		this.deviceId = deviceId;
		this.eventDateTime = eventDateTime;
		this.fieldA = fieldA;
		this.fieldB = fieldB;
		this.fieldC = fieldC;
	}
	public RecordEntity() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long recordId;
	
	private String recordType;
	
	private String deviceId;
	private Timestamp eventDateTime;
	private Integer fieldA;
	private String fieldB;
	private Double fieldC;
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Timestamp getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Timestamp eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public Integer getFieldA() {
		return fieldA;
	}
	public void setFieldA(Integer fieldA) {
		this.fieldA = fieldA;
	}
	public String getFieldB() {
		return fieldB;
	}
	public void setFieldB(String fieldB) {
		this.fieldB = fieldB;
	}
	public Double getFieldC() {
		return fieldC;
	}
	public void setFieldC(Double fieldC) {
		this.fieldC = fieldC;
	}
	

}
