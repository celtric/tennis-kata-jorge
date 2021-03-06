package com.viscaweb.kata.tennis;

final class Score {
    private final Points player1Points;
    private final Points player2Points;

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

    Score(Points player1Points, Points player2Points) {
        this.player1Points = player1Points;
        this.player2Points = player2Points;
    }

    Score player1Scored() {
        return new Score(player1Points.next(player2Points), player2Points.opponentScored());
    }

    Score player2Scored() {
        return new Score(player1Points.opponentScored(), player2Points.next(player1Points));
    }

    boolean hasPlayer1Won() {
        return player1Points.equals(Points.WON);
    }

    boolean isDeuce() {
        return player1Points.equals(Points.FORTY) && player2Points.equals(Points.FORTY);
    }

    boolean hasPlayer1Advantage() {
        return player1Points.equals(Points.ADVANTAGE);
    }
}
