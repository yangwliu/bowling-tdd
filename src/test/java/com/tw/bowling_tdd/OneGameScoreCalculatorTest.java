package com.tw.bowling_tdd;

import com.tw.bowling_tdd.exception.RecordsIsEmpty;
import com.tw.bowling_tdd.exception.RecordsNotComplete;
import java.util.ArrayList;
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

  @Test
  void should_return_total_knocked_balls_when_calculate_one_game_total_score_given_all_of_the_first_nine_round_are_neither_strike_nor_spare_and_the_tenth_round_is_strike() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> new RoundRecord(i + 1, RoundResultEnum.NOT_COMPLETE, Arrays.asList(1, 1))).collect(Collectors.toList());
    RoundRecord lastRoundRecord = roundRecords.get(9);
    lastRoundRecord.setResult(RoundResultEnum.STRIKE);
    lastRoundRecord.setKnockedBalls(Arrays.asList(10, 1, 1));
    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(30, actual);
  }

  @Test
  void should_return_total_knocked_balls_add_first_time_knocked_ball_after_spare_round_when_calculate_one_game_total_score_given_one_of_the_first_nine_round_is_spare_and_the_others_are_neither_strike_nor_spare() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> new RoundRecord(i + 1, RoundResultEnum.NOT_COMPLETE, Arrays.asList(1, 1))).collect(Collectors.toList());
    RoundRecord lastRoundRecord = roundRecords.get(0);
    lastRoundRecord.setResult(RoundResultEnum.SPARE);
    lastRoundRecord.setKnockedBalls(Arrays.asList(3, 7));
    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(29, actual);
  }

  @Test
  void should_return_total_knocked_balls_when_calculate_one_game_total_score_given_first_nine_round_are_neither_spare_nor_strike_and_the_tenth_is_spare() {
    //given
    List<RoundRecord> roundRecords = IntStream.range(0, 10).mapToObj(i -> new RoundRecord(i + 1, RoundResultEnum.NOT_COMPLETE, Arrays.asList(1, 1))).collect(Collectors.toList());
    RoundRecord lastRoundRecord = roundRecords.get(9);
    lastRoundRecord.setResult(RoundResultEnum.SPARE);
    lastRoundRecord.setKnockedBalls(Arrays.asList(3, 7, 1));
    //when
    int actual = OneGameScoreCalculator.calculateOneGameTotalScore(roundRecords);

    //then
    assertEquals(29, actual);
  }

  @Test
  void should_throw_records_is_empty_exception_when_calculate_one_game_total_score_given_records_is_null() {
    assertThrows(RecordsIsEmpty.class, () -> OneGameScoreCalculator.calculateOneGameTotalScore(null));
  }

  @Test
  void should_throw_records_is_empty_exception_when_calculate_one_game_total_score_given_records_is_empty() {
    assertThrows(RecordsIsEmpty.class, () -> OneGameScoreCalculator.calculateOneGameTotalScore(new ArrayList<>()));
  }

  @Test
  void should_throw_records_not_complete_exception_when_calculate_one_game_total_score_given_records_is_empty() {
    assertThrows(RecordsNotComplete.class, () -> OneGameScoreCalculator.calculateOneGameTotalScore(IntStream.range(0, 9).mapToObj(i -> RoundRecord.buildSpikeRoundRecord(i + 1)).collect(
        Collectors.toList())));
  }
}