package com.bingo.test;

public class BingoPlayer {
  private boolean isBingoCalled = false;
  private final String name;
  private final BingoCard bingoCard;

  public BingoPlayer(String name, int bingoSize, int maxBingoNumber, int[][] initialBingoNumbers) {
    this.name = name;
    this.bingoCard = new BingoCard(bingoSize, maxBingoNumber, initialBingoNumbers);
  }

  public Boolean isBingoCalled() {
    return isBingoCalled;
  }

  public void showCard() {
    System.out.println(this.name + "'s Card:");
    System.out.println(this.bingoCard.prettyPrint());
  }

  public void markCalled(int num) {
    bingoCard.markCalled(num);
    notifyHost();
  }

  public void callBingo() {
    System.out.println(name + " Bingo!");
    isBingoCalled = true;
  }

  public void notifyHost() {
    if (bingoCard.isBingo()) {
      if (!isBingoCalled)
        callBingo();
    } else
      showCard();
  }
}
