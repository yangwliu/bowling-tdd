package com.tw.bowling_tdd;

import com.tw.bowling_tdd.exception.RecordsIsEmpty;
import com.tw.bowling_tdd.exception.RecordsNotComplete;
import java.util.List;

public class OneGameScoreCalculator {

  public static int calculateOneGameTotalScore(List<RoundRecord> records) {

    if (records == null || records.isEmpty()) {
      throw new RecordsIsEmpty();
    }

    if (records.size() != 10) {
      throw new RecordsNotComplete();
    }

    int totalScore = 0;
    for(int i = 0; i < 10; i++) {
      int currentRoundScore = 0;
      if (i < 8) {
        currentRoundScore = getCurrentRoundScoreForFirstEightRound(records.get(i), records.get(i + 1), records.get(i + 2));
      }
      if (i == 8) {
        currentRoundScore = getCurrentRoundScoreForNinthRound(records.get(i), records.get(i + 1));
      }
      if (i == 9) {
        currentRoundScore = getCurrentRoundScoreForTenthRound(records.get(i));
      }
      totalScore += currentRoundScore;
    }

    return totalScore;
  }

  private static int getCurrentRoundScoreForTenthRound(RoundRecord currentRoundRecord) {
    return currentRoundRecord.getKnockedTotalBalls();
  }

  private static int getCurrentRoundScoreForNinthRound(RoundRecord currentRoundRecord, RoundRecord nextRound) {
    int currentRoundScores = currentRoundRecord.getKnockedTotalBalls();
    if (currentRoundRecord.getResult() == RoundResultEnum.STRIKE) {
      currentRoundScores += nextRound.getKnockedBalls().get(0) + nextRound.getKnockedBalls().get(1);
    }

    if (currentRoundRecord.getResult() == RoundResultEnum.SPARE) {
      currentRoundScores += nextRound.getKnockedBalls().get(0);
    }
    return currentRoundScores;
  }

  private static int getCurrentRoundScoreForFirstEightRound(RoundRecord currentRoundRecord, RoundRecord nextRoundRecord, RoundRecord nextNextRoundRecord) {
    int currentRoundScores = currentRoundRecord.getKnockedTotalBalls();
    if (currentRoundRecord.getResult() == RoundResultEnum.STRIKE) {
      if (nextNextRoundRecord.getResult() == RoundResultEnum.STRIKE) {
        currentRoundScores += nextNextRoundRecord.getKnockedBalls().get(0) + nextNextRoundRecord.getKnockedBalls().get(0);
      } else {
        currentRoundScores += nextNextRoundRecord.getKnockedTotalBalls();
      }
    }

    if (currentRoundRecord.getResult() == RoundResultEnum.SPARE) {
      currentRoundScores += nextNextRoundRecord.getKnockedBalls().get(0);
    }
    return currentRoundScores;
  }

}
