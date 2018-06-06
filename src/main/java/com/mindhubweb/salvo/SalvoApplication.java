package com.mindhubweb.salvo;

import com.mindhubweb.salvo.model.*;
import com.mindhubweb.salvo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository
										, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository
										, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
		return (args) -> {
			// save a couple of customers
			Player jBauer = new Player("j.bauer@ctu.gov", "24");
			Player cObrian = new Player("c.obrian@ctu.gov", "42");
			Player kBauer = new Player("kim_bauer@gmail.com", "kb");
			Player tAlmeida = new Player("t.almeida@ctu.gov", "mole");

			playerRepository.save(jBauer);
			playerRepository.save(cObrian);
			playerRepository.save(kBauer);
			playerRepository.save(tAlmeida);

			Game game1 = new Game(Date.from(new Date().toInstant().plusSeconds(3600)));
			Game game2 = new  Game(Date.from(new Date().toInstant().plusSeconds(7200)));
			Game game3 = new Game();
			Game game4 = new Game();
			Game game5 = new Game();
			Game game6 = new Game();
			Game game7 = new Game();
			Game game8 = new Game();

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);
			gameRepository.save(game5);
			gameRepository.save(game6);
			gameRepository.save(game7);
			gameRepository.save(game8);


			GamePlayer gameplayer1 = new GamePlayer(game1, jBauer);
			GamePlayer gameplayer2 = new GamePlayer(game1, cObrian);
			GamePlayer gameplayer3 = new GamePlayer(game2, jBauer);
			GamePlayer gameplayer4 = new GamePlayer(game2, cObrian);
			GamePlayer gameplayer5 = new GamePlayer(game3, cObrian);
			GamePlayer gameplayer6 = new GamePlayer(game3, tAlmeida);
			GamePlayer gameplayer7 = new GamePlayer(game4, cObrian);
			GamePlayer gameplayer8 = new GamePlayer(game4, jBauer);
			GamePlayer gameplayer9 = new GamePlayer(game5, tAlmeida);
			GamePlayer gameplayer10 = new GamePlayer(game5, jBauer);
			GamePlayer gameplayer11 = new GamePlayer(game6, kBauer);
			GamePlayer gameplayer12 = new GamePlayer(game7, tAlmeida);
			GamePlayer gameplayer13 = new GamePlayer(game8, kBauer);
			GamePlayer gameplayer14 = new GamePlayer(game8, tAlmeida);

			gamePlayerRepository.save(gameplayer1);
			gamePlayerRepository.save(gameplayer2);
			gamePlayerRepository.save(gameplayer3);
			gamePlayerRepository.save(gameplayer4);
			gamePlayerRepository.save(gameplayer5);
			gamePlayerRepository.save(gameplayer6);
			gamePlayerRepository.save(gameplayer7);
			gamePlayerRepository.save(gameplayer8);
			gamePlayerRepository.save(gameplayer9);
			gamePlayerRepository.save(gameplayer10);
			gamePlayerRepository.save(gameplayer11);
			gamePlayerRepository.save(gameplayer12);
			gamePlayerRepository.save(gameplayer13);
			gamePlayerRepository.save(gameplayer14);

			List<String> ship1l = new ArrayList<>(Arrays.asList("E1","F1","G1"));
			Ship ship1 = new Ship("submarine", gameplayer1, ship1l);
			shipRepository.save(ship1);
			List<String> ship2l = new ArrayList<>(Arrays.asList("B4", "B5"));
			Ship ship2 = new Ship("patrolboat", gameplayer1, ship2l);
			shipRepository.save(ship2);
			List<String> ship3l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship3 = new Ship("destroyer", gameplayer2, ship3l);
			shipRepository.save(ship3);
			List<String> ship4l = new ArrayList<>(Arrays.asList("F1", "F2"));
			Ship ship4 = new Ship("patrolboat", gameplayer2, ship4l);
			shipRepository.save(ship4);
			List<String> ship5l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship5 = new Ship("destroyer", gameplayer3, ship5l);
			shipRepository.save(ship5);
			List<String> ship6l = new ArrayList<>(Arrays.asList("C6", "C7"));
			Ship ship6 = new Ship("patrolboat", gameplayer3, ship6l);
			shipRepository.save(ship6);
			List<String> ship7l = new ArrayList<>(Arrays.asList("A2", "A3", "A4"));
			Ship ship7 = new Ship("submarine", gameplayer4, ship7l);
			shipRepository.save(ship7);
			List<String> ship8l = new ArrayList<>(Arrays.asList("G6", "H6"));
			Ship ship8 = new Ship("patrolboat", gameplayer4, ship8l);
			shipRepository.save(ship8);
			List<String> ship9l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship9 = new Ship("destroyer", gameplayer5, ship9l);
			shipRepository.save(ship9);
			List<String> ship10l = new ArrayList<>(Arrays.asList("C6", "C7"));
			Ship ship10 = new Ship("patrolboat", gameplayer5, ship10l);
			shipRepository.save(ship10);
			List<String> ship11l = new ArrayList<>(Arrays.asList("A2", "A3", "A4"));
			Ship ship11 = new Ship("submarine", gameplayer6, ship11l);
			shipRepository.save(ship11);
			List<String> ship12l = new ArrayList<>(Arrays.asList("G6", "H6"));
			Ship ship12 = new Ship("patrolboat", gameplayer6, ship12l);
			shipRepository.save(ship12);
			List<String> ship13l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship13 = new Ship("destroyer", gameplayer7, ship13l);
			shipRepository.save(ship13);
			List<String> ship14l = new ArrayList<>(Arrays.asList("C6", "C7"));
			Ship ship14 = new Ship("patrolboat", gameplayer7, ship14l);
			shipRepository.save(ship14);
			List<String> ship15l = new ArrayList<>(Arrays.asList("A2", "A3", "A4"));
			Ship ship15 = new Ship("submarine", gameplayer8, ship15l);
			shipRepository.save(ship15);
			List<String> ship16l = new ArrayList<>(Arrays.asList("G6", "H6"));
			Ship ship16 = new Ship("patrolboat", gameplayer8, ship16l);
			shipRepository.save(ship16);
			List<String> ship17l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship17 = new Ship("destroyer", gameplayer9, ship17l);
			shipRepository.save(ship17);
			List<String> ship18l = new ArrayList<>(Arrays.asList("C6", "C7"));
			Ship ship18 = new Ship("patrolboat", gameplayer9, ship18l);
			shipRepository.save(ship18);
			List<String> ship19l = new ArrayList<>(Arrays.asList("A2", "A3", "A4"));
			Ship ship19 = new Ship("submarine", gameplayer10, ship19l);
			shipRepository.save(ship19);
			List<String> ship20l = new ArrayList<>(Arrays.asList("G6", "H6"));
			Ship ship20 = new Ship("patrolboat", gameplayer10, ship20l);
			shipRepository.save(ship20);
			List<String> ship21l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship21 = new Ship("destroyer", gameplayer11, ship21l);
			shipRepository.save(ship21);
			List<String> ship22l = new ArrayList<>(Arrays.asList("C6", "C7"));
			Ship ship22 = new Ship("patrolboat", gameplayer11, ship22l);
			shipRepository.save(ship22);
			List<String> ship23l = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			Ship ship23 = new Ship("destroyer", gameplayer13, ship23l);
			shipRepository.save(ship23);
			List<String> ship24l = new ArrayList<>(Arrays.asList("C6", "C7"));
			Ship ship24 = new Ship("patrolboat", gameplayer13, ship24l);
			shipRepository.save(ship24);
			List<String> ship25l = new ArrayList<>(Arrays.asList("A2", "A3", "A4"));
			Ship ship25 = new Ship("submarine", gameplayer14, ship25l);
			shipRepository.save(ship25);
			List<String> ship26l = new ArrayList<>(Arrays.asList("G6", "H6"));
			Ship ship26 = new Ship("patrolboat", gameplayer14, ship26l);
			shipRepository.save(ship26);

			Salvo salvo1 = new Salvo(gameplayer1, 1, new ArrayList<>(Arrays.asList("B5", "C5", "F1")));
			salvoRepository.save(salvo1);
			Salvo salvo2 = new Salvo(gameplayer2, 1, new ArrayList<>(Arrays.asList("B4", "B5", "B6")));
			salvoRepository.save(salvo2);
			Salvo salvo3 = new Salvo(gameplayer1, 2, new ArrayList<>(Arrays.asList("F2", "D5")));
			salvoRepository.save(salvo3);
			Salvo salvo4 = new Salvo(gameplayer2, 2, new ArrayList<>(Arrays.asList("E1", "H3", "A2")));
			salvoRepository.save(salvo4);
			Salvo salvo5 = new Salvo(gameplayer3, 1, new ArrayList<>(Arrays.asList("A2", "A4", "G6")));
			salvoRepository.save(salvo5);
			Salvo salvo6 = new Salvo(gameplayer4, 1, new ArrayList<>(Arrays.asList("B5", "D5", "C7")));
			salvoRepository.save(salvo6);
			Salvo salvo7 = new Salvo(gameplayer3, 2, new ArrayList<>(Arrays.asList("A3", "H6")));
			salvoRepository.save(salvo7);
			Salvo salvo8 = new Salvo(gameplayer4, 2, new ArrayList<>(Arrays.asList("C5", "C6")));
			salvoRepository.save(salvo8);
			Salvo salvo9 = new Salvo(gameplayer5, 1, new ArrayList<>(Arrays.asList("G6", "H6", "A4")));
			salvoRepository.save(salvo9);
			Salvo salvo10 = new Salvo(gameplayer6, 1, new ArrayList<>(Arrays.asList("H1", "H2", "H3")));
			salvoRepository.save(salvo10);
			Salvo salvo11 = new Salvo(gameplayer5, 2, new ArrayList<>(Arrays.asList("A2", "A3", "D8")));
			salvoRepository.save(salvo11);
			Salvo salvo12 = new Salvo(gameplayer6, 2, new ArrayList<>(Arrays.asList("E1", "F2", "G3")));
			salvoRepository.save(salvo12);
			Salvo salvo13 = new Salvo(gameplayer7, 1, new ArrayList<>(Arrays.asList("A3", "A4", "F7")));
			salvoRepository.save(salvo13);
			Salvo salvo14 = new Salvo(gameplayer8, 1, new ArrayList<>(Arrays.asList("B5", "C6", "H1")));
			salvoRepository.save(salvo14);
			Salvo salvo15 = new Salvo(gameplayer7, 2, new ArrayList<>(Arrays.asList("A2", "G6", "H6")));
			salvoRepository.save(salvo15);
			Salvo salvo16 = new Salvo(gameplayer8, 2, new ArrayList<>(Arrays.asList("C5", "C7", "D5")));
			salvoRepository.save(salvo16);
			Salvo salvo17 = new Salvo(gameplayer9, 1, new ArrayList<>(Arrays.asList("A1", "A2", "A3")));
			salvoRepository.save(salvo17);
			Salvo salvo18 = new Salvo(gameplayer10, 1, new ArrayList<>(Arrays.asList("B5", "B6", "C7")));
			salvoRepository.save(salvo18);
			Salvo salvo19 = new Salvo(gameplayer9, 2, new ArrayList<>(Arrays.asList("G6", "G7", "G8")));
			salvoRepository.save(salvo19);
			Salvo salvo20 = new Salvo(gameplayer10, 2, new ArrayList<>(Arrays.asList("C6", "D6", "E6")));
			salvoRepository.save(salvo20);
			Salvo salvo21 = new Salvo(gameplayer10, 3, new ArrayList<>(Arrays.asList("H1", "H8")));
			salvoRepository.save(salvo21);

			Score score1 = new Score(game1, jBauer, 1.0);
			scoreRepository.save(score1);
			Score score2 = new Score(game1, cObrian, 0.0);
			scoreRepository.save(score2);
			Score score3 = new Score(game2, jBauer, 0.5);
			scoreRepository.save(score3);
			Score score4 = new Score(game2, cObrian, 0.5);
			scoreRepository.save(score4);
			Score score5 = new Score(game3, cObrian, 1.0);
			scoreRepository.save(score5);
			Score score6 = new Score(game3, tAlmeida, 0.0);
			scoreRepository.save(score6);
			Score score7 = new Score(game4, cObrian, 0.5);
			scoreRepository.save(score7);
			Score score8 = new Score(game4, jBauer, 0.5);
			scoreRepository.save(score8);
		};
	}
}
