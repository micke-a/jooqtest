package me.mikael.jooqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestJooqtestApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestJooqtestApplication.class).run(args);
	}

}
