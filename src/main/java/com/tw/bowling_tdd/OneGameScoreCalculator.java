package com.tw.bowling_tdd;

import java.util.List;

public class OneGameScoreCalculator {

  public static int calculateOneGameTotalScore(List<RoundRecord> records) {

    int totalScore = 0;
    for(RoundRecord record : records) {
      List<Integer> scores = record.getScores();
      totalScore += scores.get(0) + scores.get(1);
    }

    return totalScore;
  }

}
