package com.viscaweb.kata.tennis;

class Result {
    private final String winner;
    private final Boolean advantage;
    private final String advantageFor;
    private final Boolean deuce;

    Result(String winner, Boolean advantage, String advantageFor, Boolean deuce) {
        this.winner = winner;
        this.advantage = advantage;
        this.advantageFor = advantageFor;
        this.deuce = deuce;
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
