package com.tw.bowling_tdd;

import java.util.List;

public class OneGameScoreCalculator {

  public static int calculateOneGameTotalScore(List<RoundRecord> records) {

    int totalScore = 0;
    for(int i = 0; i < 10; i++) {
      RoundRecord record = records.get(i);
      totalScore += record.getKnockedTotalBalls();
      if (record.getResult() == RoundResultEnum.STRIKE) {
        if (i == 8) {
          totalScore += records.get(i + 1).getKnockedBalls().get(0) + records.get(i + 1).getKnockedBalls().get(1);
        }
        if (i < 8) {
          RoundRecord nextRoundRecord = records.get(i + 1);
          if (nextRoundRecord.getResult() == RoundResultEnum.STRIKE) {
            totalScore += nextRoundRecord.getKnockedTotalBalls() + records.get(i + 2).getKnockedBalls().get(0);
          } else {
            totalScore += nextRoundRecord.getKnockedTotalBalls();
          }
        }
      }
    }

    return totalScore;
  }

}
