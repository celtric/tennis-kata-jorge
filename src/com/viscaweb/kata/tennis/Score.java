package com.viscaweb.kata.tennis;

import java.util.function.Function;

class Score {
    private Points player1Score;
    private Points player2Score;

    enum Points {
        LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE, WON;

        private Points next(Points opponent) {
            switch (this) {
                case LOVE:    return FIFTEEN;
                case FIFTEEN: return THIRTY;
                case THIRTY:  return FORTY;
                case FORTY:   return opponent.equals(FORTY) ? ADVANTAGE : WON;
                default:      throw new RuntimeException("Not implemented");
            }
        }

        private Points opponentScored() {
            return equals(ADVANTAGE) ? FORTY : this;
        }
    }

    Score(Points player1Score, Points player2Score) {
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
        if (player1Score.equals(Points.WON))
            return new Result("Player 1 win", false, "", false);
        else
            return new Result("", false, "", false);
    };

    Function<Score, Result> checkDeuce = o -> {
        if (player1Score.equals(Points.FORTY) && player2Score.equals(Points.FORTY))
            return new Result("", false, "", true);
        else
            return new Result("", false, "", false);
    };

    Function<Score, Result> checkAdvantage = o -> {
        if (player1Score.equals(Points.ADVANTAGE) && player2Score.equals(Points.FORTY))
            return new Result("", true, "Player 1", false);
        else if (player1Score.equals(Points.FORTY) && player2Score.equals(Points.ADVANTAGE))
            return new Result("", true, "Player 2", false);
        else
            return new Result("", false, "", false);
    };
}
