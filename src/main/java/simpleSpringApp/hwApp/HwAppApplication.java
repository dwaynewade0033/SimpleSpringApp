package simpleSpringApp.hwApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class HwAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwAppApplication.class, args);
	}
	@Bean
	public DataSource dataSource(){
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin:@localhost:1521:xe")
				.username("shev")
				.password("12345678")
				.build();
	}


}
