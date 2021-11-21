package com.bingo.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoCard {
  private boolean isBingo = false;
  private final int bingoSize;
  private final int maxBingoNumber;
  private final BingoChecker bingoChecker;
  private final int[][] numberRows;

  public BingoCard(int size, int maxBingoNumber, int[][] numberRows) {
    this.bingoSize = size;
    this.maxBingoNumber = maxBingoNumber;

    if (numberRows != null) {
      this.numberRows = numberRows;
    } else {
      this.numberRows = new int[size][size];
      generateNumbers();
    }

    bingoChecker = new BingoChecker(this.numberRows);
  }

  public Boolean isBingo() {
    return isBingo;
  }

  public void checkBingo() {
    if (bingoChecker.isBingo())
      isBingo = true;
  }

  public void markCalled(int num) {
    for (int i = 0; i < bingoSize; i++)
      for (int j = 0; j < bingoSize; j++)
        if (numberRows[i][j] == num) {
          numberRows[i][j] = -1;

          if (!isBingo)
            checkBingo();
          break;
        }
  }

  public String prettyPrint() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int[] number : numberRows) {
      StringJoiner joiner = new StringJoiner("\t");

      for (int cardNumber : number)
        joiner.add(cardNumber == -1 ? "XX" : String.valueOf(cardNumber));

      String numberRow = joiner.toString();
      stringBuilder.append(numberRow).append("\n");
    }

    return stringBuilder.toString();
  }

  private void generateNumbers() {
    List<Integer> numberList = IntStream.range(1, maxBingoNumber + 1).boxed().collect(Collectors.toList());
    Collections.shuffle(numberList, new Random());

    for (int i = 0; i < bingoSize; i++)
      for (int j = 0; j < bingoSize; j++)
        numberRows[i][j] = numberList.get(i * bingoSize + j);
  }
}
