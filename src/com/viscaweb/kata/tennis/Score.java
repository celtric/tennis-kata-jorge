package com.viscaweb.kata.tennis;

import java.util.function.Function;

class Score {
    private String player1Score = null;
    private String player2Score = null;

    private Integer player1PlainScore;
    private Integer player2PlainScore;

    Score(final Integer player1PlainScore, final Integer player2PlainScore) {
        this.player1PlainScore = player1PlainScore;
        this.player2PlainScore = player2PlainScore;

        this.player1Score = doStringScore(player1PlainScore);
        this.player2Score = doStringScore(player2PlainScore);
    }

    private String doStringScore(Integer plainScore) {
        switch (plainScore) {
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            case 4:
                return "A";
            default:
                return "0";
        }
    }

    Score sumScoreToPlayer1() {
        Integer newPlayer1Score = this.player1PlainScore;
        Integer newPlayer2Score = this.player2PlainScore;

        if (this.player2PlainScore == 4) {
            newPlayer2Score = 3;
        }

        newPlayer1Score++;

        return new Score(newPlayer1Score, newPlayer2Score);
    }

    Score sumScoreToPlayer2() {
        Integer newPlayer1Score = this.player1PlainScore;
        Integer newPlayer2Score = this.player2PlainScore;

        if (this.player1PlainScore == 4) {
            newPlayer1Score = 3;
        }

        newPlayer2Score++;

        return new Score(newPlayer1Score, newPlayer2Score);
    }

    Function<Score, Result> checkWin = o -> {
        if (this.player1PlainScore >= 3 && this.player1PlainScore - this.player2PlainScore >= 2)
            return new Result("Player 1 win", false, "", false);
        else
            return new Result("", false, "", false);
    };

    Function<Score, Result> checkDeuce = o -> {
        if (this.player1Score.equals("40") && this.player2Score.equals("40"))
            return new Result("", false, "", true);
        else
            return new Result("", false, "", false);
    };

    Function<Score, Result> checkAdvantage = o -> {
        if (this.player1Score.equals("A") && this.player2Score.equals("40"))
            return new Result("", true, "Player 1", false);
        else if (this.player1Score.equals("40") && this.player2Score.equals("A"))
            return new Result("", true, "Player 2", false);
        else
            return new Result("", false, "", false);
    };
}
