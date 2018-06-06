package com.mindhubweb.salvo.repository;

import com.mindhubweb.salvo.model.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerRepository extends JpaRepository <GamePlayer, Long> {

}
