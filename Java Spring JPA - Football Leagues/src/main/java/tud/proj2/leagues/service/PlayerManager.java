package tud.proj2.leagues.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tud.proj2.leagues.domain.Player;

public interface PlayerManager extends JpaRepository<Player, Integer> {
    <S extends Player> S save(S player);

    Player findById(int id);

    Player getOneById(int id);

    void delete(Player player);

    List<Player> findByValueGreaterThan(float value);

    List<Player> findByAge(int age);

    List<Player> findByAgeLessThanAndValueGreaterThan(int age, float value);
}