package com.mindhubweb.salvo.model;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayer;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<Score> scores;

    public Player() {

    }

    public Player(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Set<GamePlayer> getGamePlayer() {
        return gamePlayer;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        this.gamePlayer.add(gamePlayer);
    }

    public void setGamePlayer(Set<GamePlayer> gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List<Game> getGames() {
        return gamePlayer.stream().map(sub -> sub.getGame()).collect(toList());
    }

    public Set<Score> getScores() { return scores; }

    public Score getScore(Game game) {
        return this.getScores().stream().filter(
                score -> score.getGame().getId() == game.getId()
        ).findFirst().orElse(null);
    }

    public void setScores(Set<Score> scores) { this.scores = scores; }

    public void addScores(Score score){
        score.setPlayer(this);
        this.scores.add(score);
    }

    public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("email", this.getEmail());
        return dto;
    }
}
