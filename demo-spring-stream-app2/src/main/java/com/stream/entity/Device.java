package com.stream.entity;

import java.util.Date;

public class Device {
	
	public static final String SPLIT = ", ";

	private long id;
	
	private String deviceName;
	
	private String deviceType;
	
	private Date createTime;
	
	public Device(long id, String deviceName, String deviceType) {
		this.id=id;
		this.deviceName=deviceName;
		this.deviceType=deviceType;
		this.createTime=new Date();
	}
	
	public Device() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return id+SPLIT+deviceName+SPLIT+deviceType+SPLIT+createTime;
	}
	
}
