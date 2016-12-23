package com.viscaweb.kata.tennis;

import java.util.function.Function;

class Score {
    private Point player1Score = null;
    private Point player2Score = null;

    private Integer player1PlainScore;
    private Integer player2PlainScore;

    enum Point { LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE }

    Score(final Integer player1PlainScore, final Integer player2PlainScore) {
        this.player1PlainScore = player1PlainScore;
        this.player2PlainScore = player2PlainScore;

        this.player1Score = toPoint(player1PlainScore);
        this.player2Score = toPoint(player2PlainScore);
    }

    private Point toPoint(Integer plainScore) {
        switch (plainScore) {
            case 1:  return Point.FIFTEEN;
            case 2:  return Point.THIRTY;
            case 3:  return Point.FORTY;
            case 4:  return Point.ADVANTAGE;
            default: return Point.LOVE;
        }
    }

    Score sumScoreToPlayer1() {
        Integer newPlayer1Score = player1PlainScore;
        Integer newPlayer2Score = player2PlainScore;

        if (player2PlainScore == 4) {
            newPlayer2Score = 3;
        }

        newPlayer1Score++;

        return new Score(newPlayer1Score, newPlayer2Score);
    }

    Score sumScoreToPlayer2() {
        Integer newPlayer1Score = player1PlainScore;
        Integer newPlayer2Score = player2PlainScore;

        if (player1PlainScore == 4) {
            newPlayer1Score = 3;
        }

        newPlayer2Score++;

        return new Score(newPlayer1Score, newPlayer2Score);
    }

    Function<Score, Result> checkWin = o -> {
        if (player1PlainScore >= 3 && player1PlainScore - player2PlainScore >= 2)
            return new Result("Player 1 win", false, "", false);
        else
            return new Result("", false, "", false);
    };

    Function<Score, Result> checkDeuce = o -> {
        if (player1Score.equals(Point.FORTY) && player2Score.equals(Point.FORTY))
            return new Result("", false, "", true);
        else
            return new Result("", false, "", false);
    };

    Function<Score, Result> checkAdvantage = o -> {
        if (player1Score.equals(Point.ADVANTAGE) && player2Score.equals(Point.FORTY))
            return new Result("", true, "Player 1", false);
        else if (player1Score.equals(Point.FORTY) && player2Score.equals(Point.ADVANTAGE))
            return new Result("", true, "Player 2", false);
        else
            return new Result("", false, "", false);
    };
}
