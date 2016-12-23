package com.viscaweb.kata.tennis;

import com.viscaweb.kata.tennis.Score.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test public void should_win_player_1_after_score_at_least_4_times_and_2_more_than_player_2() {
        Result expected = new Result("Player 1 win", Boolean.FALSE, "", Boolean.FALSE);

        assertEquals(expected, win());
    }

    @Test public void should_deuce_after_both_players_score_3_times() {
        Result expected = new Result("", Boolean.FALSE, "", Boolean.TRUE);

        assertEquals(expected, deuce());
    }

    @Test public void should_be_advantage_after_player_2_score_3_times_and_player_1_score_4_times() {
        Result expected = new Result("", Boolean.TRUE, "Player 1", Boolean.FALSE);

        assertEquals(expected, advantage());
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private Result win() {
        final Score finalScore = new Score(Point.FORTY, Point.LOVE)
                .sumScoreToPlayer1();
        return finalScore.checkWin.apply(null);
    }

    private Result deuce() {
        final Score finalScore = new Score(Point.LOVE, Point.LOVE)
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2();
        return finalScore.checkDeuce.apply(null);
    }

    private Result advantage() {
        final Score finalScore = new Score(Point.LOVE, Point.LOVE)
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
