package tud.proj2.leagues.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import tud.proj2.leagues.domain.Player;

@Transactional(readOnly = true)
public interface PlayerManager extends JpaRepository<Player, Integer> {
    @Transactional
    <S extends Player> S save(S player);

    Player findOneByFirstNameAndLastName(String firstName, String lastName);

    // @Transactional
    Player getOneByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    void delete(Player player);

    List<Player> listAllInDb();

    List<Player> findByValueGreaterThan(double val);

    @Transactional
    List<Player> deleteByFirstNameAndLastName(String firstName, String lastName);
}