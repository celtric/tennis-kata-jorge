package com.viscaweb.kata.tennis;

class Game {

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
