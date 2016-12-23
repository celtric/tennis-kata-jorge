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

        private Point opponentScored() {
            return equals(ADVANTAGE) ? FORTY : this;
        }
    }

    Score(Point player1Score, Point player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    Score sumScoreToPlayer1() {
        return new Score(player1Score.next(player2Score), player2Score.opponentScored());
    }

    Score sumScoreToPlayer2() {
        return new Score(player1Score.opponentScored(), player2Score.next(player1Score));
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
