package com.viscaweb.kata.tennis;

import com.viscaweb.kata.tennis.Score.Points;
import org.junit.Test;

import static com.viscaweb.kata.tennis.Score.*;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test public void should_win_player_1_after_score_at_least_4_times_and_2_more_than_player_2() {
        Result expected = Result.winner(Player.A);

        assertEquals(expected, win());
    }

    @Test public void should_deuce_after_both_players_score_3_times() {
        Result expected = Result.deuce();

        assertEquals(expected, deuce());
    }

    @Test public void should_be_advantage_after_player_2_score_3_times_and_player_1_score_4_times() {
        Result expected = Result.advantage(Player.A);

        assertEquals(expected, advantage());
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private Result win() {
        return new Score(Points.FORTY, Points.LOVE)
                .sumScoreToPlayer1().result();
    }

    private Result deuce() {
        return new Score(Points.LOVE, Points.LOVE)
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2().result();
    }

    private Result advantage() {
        return new Score(Points.LOVE, Points.LOVE)
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer1()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2()
                .sumScoreToPlayer2()
                .sumScoreToPlayer1()
                .result();
    }
}
