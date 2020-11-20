package Config;

import javax.swing.*;



/**
 * Created by Axel Jeansson
 * Date: 2020-11-12
 * Time: 13:47
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Player {
    private String username;
    private ImageIcon avatar;
    private int userScore;
    private int matchesPlayed;
    private int wins;
    private int losses;
    private int questionsAnswered;
    private int correctAnswers;
    private Player opponent;

    public Player(String username, int userScore, ImageIcon avatar) {
        this.username = username;
        this.userScore = userScore;
        this.avatar = avatar;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUsername() {
        return username;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }
}