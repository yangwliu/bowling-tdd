package com.tw.bowling_tdd;

import java.util.List;

public class RoundRecord {

  private int roundNumber;
  private RoundResultEnum result;
  private List<Integer> scores;

  public RoundRecord(int roundNumber, RoundResultEnum result, List<Integer> scores) {
    this.roundNumber = roundNumber;
    this.result = result;
    this.scores = scores;
  }

  public int getRoundNumber() {
    return roundNumber;
  }

  public RoundResultEnum getResult() {
    return result;
  }

  public List<Integer> getScores() {
    return scores;
  }
}
