package com.mindhubweb.salvo.controller;

import com.mindhubweb.salvo.model.*;
import com.mindhubweb.salvo.repository.GamePlayerRepository;
import com.mindhubweb.salvo.repository.GameRepository;
import com.mindhubweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/games")
    public Map<String,Object> getAllGames(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        if(isGuest(authentication))
            dto.put("player", "Guest");
        else
            dto.put("player", loggedInToDTO(getLoggedPlayer(authentication)));
        dto.put("games",getAllGames());
        return dto;
    }

    @RequestMapping("/game_view/{gamePlayerID}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerID, Authentication authentication) {

        GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerID);
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayerList(gamePlayer.getGame().getGamePlayer()));
        dto.put("ships",shipLocationsList(gamePlayer.getShips()));
        dto.put("salvoes", salvoesList(gamePlayer.getGame()));
        return dto;
    }

    @RequestMapping("/leaderBoard")
    public List<Map<String,Object>> getScoreView(){
        List<Map<String,Object>> finalList = new ArrayList<>();
        this.getAllPlayers().forEach(player -> {
            if(!player.getScores().isEmpty()) {
                Map<String, Object> dto = new LinkedHashMap<>();
                Map<String, Object> dtoScore = new LinkedHashMap<>();
                dtoScore.put("total", this.getPlayerAllScore(player));
                dtoScore.put("won", this.getPlayerAllWins(player));
                dtoScore.put("lost", this.getPlayerAllLost(player));
                dtoScore.put("tied", this.getPlayerAllTied(player));
                dto.put("name",player.getEmail());
                dto.put("score",dtoScore);
                finalList.add(dto);
            }
        });
        return finalList;
    }

    @RequestMapping(value="/players", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> players(@RequestParam String email, @RequestParam String password) {

        if (email.isEmpty())
            return new ResponseEntity<>(makeMap("error", "No name"), HttpStatus.BAD_REQUEST);

        if (playerRepository.findByEmail(email) != null)
            return new ResponseEntity<>(makeMap("error", "Username already exists"), HttpStatus.CONFLICT);

        Player newPlayer = playerRepository.save(new Player(email,password));
        return new ResponseEntity<>(makeMap("email", newPlayer.getEmail()), HttpStatus.CREATED);
    }

    private Double getPlayerAllScore(Player player){
        Double total = player.getScores()
                            .stream()
                            .mapToDouble( score -> score.getScore()).sum();
        return total;
    }

    private Long getPlayerAllWins(Player player){
        Long wins = player.getScores()
                            .stream()
                            .filter(score -> score.getScore() == 1d)
                            .count();
        return wins;
    }

    private Long getPlayerAllLost(Player player){
        Long lost = player.getScores()
                .stream()
                .filter(score -> score.getScore() == 0d)
                .count();
        return lost;
    }

    private Long getPlayerAllTied(Player player){
        Long tied = player.getScores()
                .stream()
                .filter(score -> score.getScore() == 0.5d)
                .mapToDouble(i -> i.getScore().doubleValue()).count();
        return tied;
    }

    private List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    private List<Map> getAllGames(){
        return gameRepository.findAll().stream().map(game -> game.toDTO())
                .collect(Collectors.toList());
    }

    private List<Map> shipLocationsList(Set<Ship> ships) {
        return ships.stream()
                .map(ship -> ship.toDTO())
                .collect(Collectors.toList());
    }

    private List<Map> gamePlayerList(Set<GamePlayer> gamePlayers) {
        return gamePlayers.stream()
                .map(gamePlayer -> gamePlayer.toDTO())
                .collect(Collectors.toList());
    }

    private List<Map> salvoesList(Game game) {
        List<Map> finalList = new ArrayList<>();
        game.getGamePlayer().forEach(gamePlayer -> finalList.addAll(salvoLocationsList(gamePlayer.getSalvoes())));
        return finalList;
    }

    private List<Map> salvoLocationsList(Set<Salvo> salvos) {
        return salvos.stream()
                .map(salvo -> salvo.toDTO())
                .collect(Collectors.toList());
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private Map<String, Object> loggedInToDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getId());
        dto.put("name", player.getEmail());
        return dto;
    }

    private Player getLoggedPlayer(Authentication authentication) {
        return playerRepository.findByEmail(authentication.getName());
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
