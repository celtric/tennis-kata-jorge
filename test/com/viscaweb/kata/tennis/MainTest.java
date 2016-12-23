package com.viscaweb.kata.tennis;

import com.viscaweb.kata.tennis.Score.Points;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test public void should_win_player_1_after_score_at_least_4_times_and_2_more_than_player_2() {
        Result expected = new Result("Player 1 win", false, "", false);

        assertEquals(expected, win());
    }

    @Test public void should_deuce_after_both_players_score_3_times() {
        Result expected = new Result("", false, "", true);

        assertEquals(expected, deuce());
    }

    @Test public void should_be_advantage_after_player_2_score_3_times_and_player_1_score_4_times() {
        Result expected = new Result("", true, "Player 1", false);

        assertEquals(expected, advantage());
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private Result win() {
        final Score finalScore = new Score(Points.FORTY, Points.LOVE)
                .sumScoreToPlayer1();
        return finalScore.checkWin.apply(null);
    }

    private Result deuce() {
        final Score finalScore = new Score(Points.LOVE, Points.LOVE)
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2();
        return finalScore.checkDeuce.apply(null);
    }

    private Result advantage() {
        final Score finalScore = new Score(Points.LOVE, Points.LOVE)
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
