package pl.edu.ug.tent.springintro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.ug.tent.springintro.domain.Rodzina;

@Service
public class RodzinaService {

  @Autowired
  @Qualifier("rodzinaborowiak")
  Rodzina borowiak;

  Rodzina getBorowiak() {
    return borowiak;
  }

}
