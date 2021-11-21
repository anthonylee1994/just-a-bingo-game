package com.bingo.test;

public record BingoChecker(int[][] numberRows) {

  public Boolean isBingo() {
    int[] matchRow;

    for (int[] row : numberRows)
      if (isBingo(row))
        return true;

    // all columns
    for (int i = 0; i < numberRows.length; i++) {
      matchRow = new int[numberRows.length];
      for (int j = 0; j < numberRows.length; j++)
        matchRow[j] = numberRows[j][i];

      if (isBingo(matchRow))
        return true;
    }

    // all slash
    matchRow = new int[numberRows.length];
    for (int i = 0; i < numberRows.length; i++)
      matchRow[i] = numberRows[i][i];
    if (isBingo(matchRow))
      return true;

    matchRow = new int[numberRows.length];
    for (int i = 0; i < numberRows.length; i++)
      matchRow[i] = numberRows[i][numberRows.length - i - 1];

    return isBingo(matchRow);
  }

  public Boolean isBingo(int[] row) {
    int matchedCount = 0;

    for (int num : row)
      if (num == -1)
        matchedCount++;

    return matchedCount == row.length;
  }
}
