package com.viscaweb.kata.tennis;

import com.viscaweb.kata.tennis.Score.Points;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test public void should_win_player_1_after_score_at_least_4_times_and_2_more_than_player_2() {
        Score score = new Score(Points.FORTY, Points.LOVE)
                .player1Scored();

        assertTrue(score.hasPlayer1Won());
    }

    @Test public void should_deuce_after_both_players_score_3_times() {
        Score score = new Score(Points.LOVE, Points.LOVE)
                .player1Scored()
                .player1Scored()
                .player1Scored()
                .player2Scored()
                .player2Scored()
                .player2Scored();

        assertTrue(score.isDeuce());
    }

    @Test public void should_be_advantage_after_player_2_score_3_times_and_player_1_score_4_times() {
        Score score = new Score(Points.LOVE, Points.LOVE)
                .player1Scored()
                .player1Scored()
                .player1Scored()
                .player2Scored()
                .player2Scored()
                .player2Scored()
                .player1Scored();

        assertTrue(score.hasPlayer1Advantage());
    }
}
