package com.viscaweb.kata.tennis;

import java.util.function.Function;

class Score {
    private Point player1Score;
    private Point player2Score;

    enum Point {
        LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE, WON;

        private Point next(Point opponent) {
            switch (this) {
                case LOVE:    return FIFTEEN;
                case FIFTEEN: return THIRTY;
                case THIRTY:  return FORTY;
                case FORTY:   return opponent.equals(FORTY) ? ADVANTAGE : WON;
                default:      throw new RuntimeException("Not implemented");
            }
        }
    }

    Score(Point player1Score, Point player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    Score sumScoreToPlayer1() {
        Point newPlayer1Score = player1Score;
        Point newPlayer2Score = player2Score;

        if (player2Score.equals(Point.ADVANTAGE)) {
            newPlayer2Score = Point.FORTY;
        }

        newPlayer1Score = newPlayer1Score.next(player2Score);

        return new Score(newPlayer1Score, newPlayer2Score);
    }

    Score sumScoreToPlayer2() {
        Point newPlayer1Score = player1Score;
        Point newPlayer2Score = player2Score;

        if (player1Score.equals(Point.ADVANTAGE)) {
            newPlayer1Score = Point.FORTY;
        }

        newPlayer2Score = newPlayer2Score.next(player1Score);

        return new Score(newPlayer1Score, newPlayer2Score);
    }

    Function<Score, Result> checkWin = o -> {
        if (player1Score.equals(Point.WON))
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
