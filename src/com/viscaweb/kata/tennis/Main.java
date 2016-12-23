package com.viscaweb.kata.tennis;


import java.util.function.Function;

public class Main {

    static class Score {
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
                return new Result("Player 1 win", Boolean.FALSE, null, Boolean.FALSE);
            else
                return new Result(null, Boolean.FALSE, null, Boolean.FALSE);
        };

        Function<Score, Result> checkDeuce = o -> {
            if (this.player1Score.equals("40") && this.player2Score.equals("40"))
                return new Result(null, Boolean.FALSE, null, Boolean.TRUE);
            else
                return new Result(null, Boolean.FALSE, null, Boolean.FALSE);
        };

        Function<Score, Result> checkAdvantage = o -> {
            if (this.player1Score.equals("A") && this.player2Score.equals("40"))
                return new Result(null, Boolean.TRUE, "Player 1", Boolean.FALSE);
            else if (this.player1Score.equals("40") && this.player2Score.equals("A"))
                return new Result(null, Boolean.TRUE, "Player 2", Boolean.FALSE);
            else
                return new Result(null, Boolean.FALSE, null, Boolean.FALSE);
        };
    }

    static class Result {
        String winner = null;
        Boolean advantage = null;
        String advantageFor = null;
        Boolean deuce = null;

        Result(String winner, Boolean advantage, String advantageFor, Boolean deuce) {
            this.winner = winner;
            this.advantage = advantage;
            this.advantageFor = advantageFor;
            this.deuce = deuce;
        }

        @Override public String toString() {
            return "TEST ----> winner: " + winner + ", advantage: " + advantage
                    + ", advantageFor: " + advantageFor + ", deuce: " + deuce + "\n";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Result) {
                return obj.toString().equals(toString());
            }
            return super.equals(obj);
        }
    }

    static class Game {

        public Result win() {

            final Score finalScore = new Score(40, 0)
                    .sumScoreToPlayer1();

            return finalScore.checkWin.apply(null);
        }

        public Result deuce() {

            final Score finalScore = new Score(0, 0)
                    .sumScoreToPlayer1()
                    .sumScoreToPlayer1()
                    .sumScoreToPlayer1()
                    .sumScoreToPlayer2()
                    .sumScoreToPlayer2()
                    .sumScoreToPlayer2();

            return finalScore.checkDeuce.apply(null);
        }

        public Result advantage() {

            final Score finalScore = new Score(0, 0)
                    .sumScoreToPlayer1()
                    .sumScoreToPlayer1()
                    .sumScoreToPlayer1()
                    .sumScoreToPlayer2()
                    .sumScoreToPlayer2()
                    .sumScoreToPlayer2()
                    .sumScoreToPlayer1();

            return finalScore.checkAdvantage.apply(null);
        }
    }

    public static void main(String[] args) {
        final Game game = new Game();
        System.out.print(game.win());
        System.out.print(game.deuce());
        System.out.print(game.advantage());
    }
}
