package com.viscaweb.kata.tennis;

import com.viscaweb.kata.tennis.Score.Player;

class Result {
    private final Player winner;
    private final Boolean advantage;
    private final Player advantageFor;
    private final Boolean deuce;

    private Result(Player winner, Boolean advantage, Player advantageFor, Boolean deuce) {
        this.winner = winner;
        this.advantage = advantage;
        this.advantageFor = advantageFor;
        this.deuce = deuce;
    }

    static Result winner(Player winner) {
        return new Result(winner, false, Player.NONE, false);
    }

    static Result deuce() {
        return new Result(Player.NONE, false, Player.NONE, true);
    }

    static Result advantage(Player advantageFor) {
        return new Result(Player.NONE, false, advantageFor, false);
    }

    static Result inPlay() {
        return new Result(Player.NONE, false, Player.NONE, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Result) {
            Result other = (Result) obj;
            return other.winner.equals(winner)
                    && other.advantage.equals(advantage)
                    && other.advantageFor.equals(advantageFor)
                    && other.deuce.equals(deuce);
        }
        return super.equals(obj);
    }
}
