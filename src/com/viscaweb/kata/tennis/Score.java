package com.viscaweb.kata.tennis;

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

    enum Player { NONE, A, B }

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

    Result result() {
        if (player1Score.equals(Points.WON)) {
            return Result.winner(Player.A);
        } else if (player2Score.equals(Points.WON)) {
            return Result.winner(Player.B);
        } else if (player1Score.equals(Points.FORTY) && player2Score.equals(Points.FORTY)) {
            return Result.deuce();
        } else if (player1Score.equals(Points.ADVANTAGE) && player2Score.equals(Points.FORTY)) {
            return Result.advantage(Player.A);
        } else if (player1Score.equals(Points.FORTY) && player2Score.equals(Points.ADVANTAGE)) {
            return Result.advantage(Player.B);
        } else {
            return Result.inPlay();
        }
    }
}
