package com.viscaweb.kata.tennis;

class Result {
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
