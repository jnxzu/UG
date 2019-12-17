package pl.edu.ug.tent.springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import pl.edu.ug.tent.springintro.domain.Rodzina;

@SpringBootApplication
@ImportResource("classpath:fam.xml")
public class SpringintroApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringintroApplication.class, args);

		Rodzina borowiaki = (Rodzina) context.getBean("rodzinaborowiak");
		System.out.println(borowiaki);
	}

}
