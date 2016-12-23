package com.viscaweb.kata.tennis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private Game game;

    @Before public void setUp() throws Exception {
        game = new Game();
    }

    @After public void tearDown() throws Exception {
        game = null;
    }

    @Test public void should_win_player_1_after_score_at_least_4_times_and_2_more_than_player_2() {
        Result expected = new Result("Player 1 win", Boolean.FALSE, null, Boolean.FALSE);

        assertEquals(expected, game.win());
    }

    @Test public void should_deuce_after_both_players_score_3_times() {
        Result expected = new Result(null, Boolean.FALSE, null, Boolean.TRUE);

        assertEquals(expected, game.deuce());
    }

    @Test public void should_be_advantage_after_player_2_score_3_times_and_player_1_score_4_times() {
        Result expected = new Result(null, Boolean.TRUE, "Player 1", Boolean.FALSE);

        assertEquals(expected, game.advantage());
    }
}
