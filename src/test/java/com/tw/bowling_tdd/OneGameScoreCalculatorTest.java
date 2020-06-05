package com.tw.bowling_tdd;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneGameScoreCalculatorTest {

  @Test
  void should_return_zero_when_calculate_one_game_total_score_given_all_rounds_knocked_zero_balls() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> new RoundRecord(i + 1, RoundResultEnum.NOT_COMPLETE, Arrays.asList(0, 0))).collect(Collectors.toList());

    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(0, actual);
  }

  @Test
  void should_return_sum_of_all_rounds_knocked_balls_when_calculate_one_game_total_score_given_all_rounds_neither_STRIKE_nor_SPARE() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> new RoundRecord(i + 1, RoundResultEnum.NOT_COMPLETE, Arrays.asList(1, 1))).collect(Collectors.toList());

    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(20, actual);
  }

  @Test
  void should_return_300_when_calculate_one_game_total_score_given_all_rounds_are_SPIKE() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> RoundRecord.buildSpikeRoundRecord(i + 1)).collect(Collectors.toList());

    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(300, actual);
  }

  @Test
  void should_return_total_knocked_balls_add_first_two_times_knocked_balls_after_the_strike_round_when_calculate_one_game_total_score_given_one_of_the_first_nine_round_is_strike_and_others_are_neither_strike_nor_spare() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> new RoundRecord(i + 1, RoundResultEnum.NOT_COMPLETE, Arrays.asList(1, 1))).collect(Collectors.toList());
    RoundRecord firstRoundRecord = roundRecords.get(0);
    firstRoundRecord.setResult(RoundResultEnum.STRIKE);
    firstRoundRecord.setKnockedBalls(Arrays.asList(10, 0));
    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(30, actual);
  }
}