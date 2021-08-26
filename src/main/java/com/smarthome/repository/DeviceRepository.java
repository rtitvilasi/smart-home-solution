package com.smarthome.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.smarthome.models.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, Integer>{

	public Device findByid(String id);
	public List<Device> findByroomId(String id);
	public List<Device> findByuserId(String id);
}
