package com.viscaweb.kata.tennis;

public class Main {

    public static void main(String[] args) {
        final Game game = new Game();
        System.out.print(game.win());
        System.out.print(game.deuce());
        System.out.print(game.advantage());
    }
}
