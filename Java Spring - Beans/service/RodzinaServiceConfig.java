package pl.edu.ug.tent.springintro.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.ug.tent.springintro.domain.Dziecko;
import pl.edu.ug.tent.springintro.domain.Matka;
import pl.edu.ug.tent.springintro.domain.Ojciec;
import pl.edu.ug.tent.springintro.domain.Rodzina;

@Configuration
public class RodzinaServiceConfig {

  @Bean
  public Rodzina rodzinaborowiak() {
    Dziecko AB = new Dziecko("Antek", "Borowiak", 2010, null, null);
    Ojciec SB = new Ojciec("Stary", "Borowiak", 1960, AB);
    Matka MB = new Matka("Stara", "Borowiak", 1970, AB);
    AB.setDad(SB);
    AB.setMom(MB);
    return new Rodzina(SB, MB, AB);
  }

}
