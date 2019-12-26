package tud.proj2.leagues.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import tud.proj2.leagues.domain.Player;

public interface PlayerManager extends JpaRepository<Player, Integer> {
    <S extends Player> S save(S player);

    Player findOneByFirstNameAndLastName(String firstName, String lastName);

    Player getOneByFirstNameAndLastName(String firstName, String lastName);

    void delete(Player player);

    List<Player> listAllInDb();

    List<Player> findByValueGreaterThan(double val);

    @Transactional
    List<Player> deleteByFirstNameAndLastName(String firstName, String lastName);
}