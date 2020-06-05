package com.tw.bowling_tdd;

import java.util.Arrays;
import java.util.List;

public class RoundRecord {

  private int roundNumber;
  private RoundResultEnum result;
  private List<Integer> knockedBalls;

  public RoundRecord(int roundNumber, RoundResultEnum result, List<Integer> knockedBalls) {
    this.roundNumber = roundNumber;
    this.result = result;
    this.knockedBalls = knockedBalls;
  }

  public static RoundRecord buildSpikeRoundRecord(int roundNumber) {
    if (roundNumber < 10) {
      return new RoundRecord(roundNumber, RoundResultEnum.STRIKE, Arrays.asList(10, 0));
    }
    return new RoundRecord(roundNumber, RoundResultEnum.STRIKE, Arrays.asList(10, 10, 10));
  }

  public int getRoundNumber() {
    return roundNumber;
  }

  public RoundResultEnum getResult() {
    return result;
  }

  public List<Integer> getKnockedBalls() {
    return knockedBalls;
  }

  public int getKnockedTotalBalls() {
    return knockedBalls.stream().reduce(0, Integer::sum);
  }

  public void setRoundNumber(int roundNumber) {
    this.roundNumber = roundNumber;
  }

  public void setResult(RoundResultEnum result) {
    this.result = result;
  }

  public void setKnockedBalls(List<Integer> knockedBalls) {
    this.knockedBalls = knockedBalls;
  }
}
