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
