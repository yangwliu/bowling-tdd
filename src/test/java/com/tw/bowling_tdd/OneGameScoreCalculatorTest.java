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

}