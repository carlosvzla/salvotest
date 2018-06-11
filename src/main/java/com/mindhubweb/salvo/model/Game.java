package com.mindhubweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Date creationDate;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayer;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    private Set<Score> scores;

    public Game(){
        this.creationDate = new Date();
    }

    public Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<GamePlayer> getGamePlayer() { return gamePlayer; }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        this.gamePlayer.add(gamePlayer);
    }

    public void setGamePlayer(Set<GamePlayer> gamePlayer) {
        this.gamePlayer = gamePlayer;
    }


    @JsonIgnoreProperties(value = "games")
    public List<Player> getPlayers() {
        return gamePlayer.stream().map(sub -> sub.getPlayer()).collect(toList());
    }

    public Set<Score> getScores() { return scores; }

    public void setScores(Set<Score> scores) { this.scores = scores; }

    public void addScores(Score score){
        score.setGame(this);
        this.scores.add(score);
    }

    private List<Map> playerList(List<Player> players) {
        return players.stream()
                .map(gamePlayer -> gamePlayer.toDTO())
                .collect(Collectors.toList());
    }

    public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("crationDate", this.getCreationDate());
        dto.put("gamePlayers", gamePlayerList(this.getGamePlayer()));
        dto.put("scores", GamePlayerScores(this.getScores()));
        return dto;
    }

    private List<Map> gamePlayerList(Set<GamePlayer> gamePlayers) {
        return gamePlayers.stream()
                .map(gamePlayer -> gamePlayer.toDTO())
                .collect(Collectors.toList());
    }

    private List<Map> GamePlayerScores(Set<Score> scores) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        return scores.stream()
                .map(score -> score.toDTO())
                .collect(Collectors.toList());
    }

}
