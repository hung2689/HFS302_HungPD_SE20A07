package com.example.CRUDbasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class CruDbasisApplication {

	public static void main(String[] args) {
		// Fix: JVM mặc định dùng "Asia/Saigon" (tên cũ), PostgreSQL không nhận.
		// Phải set trước khi JDBC driver tạo connection.
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(CruDbasisApplication.class, args);
	}

}
