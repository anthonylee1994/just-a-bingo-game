package com.bingo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BingoGame {
  private final int bingoSize;
  private final int maxBingoNumber;
  private final ArrayList<Integer> bingoNumbers = new ArrayList<>();
  private final Scanner scanner = new Scanner(System.in);
  private final BingoPlayer[] bingoPlayers = new BingoPlayer[2];

  public BingoGame(int size, int maxBingoNumber) {
    this.bingoSize = size;
    this.maxBingoNumber = maxBingoNumber;
    initializePlayers();
  }

  private void initializePlayers() {
    for (int i = 0; i < bingoPlayers.length; i++)
      bingoPlayers[i] = new BingoPlayer("Player" + (i + 1), bingoSize, maxBingoNumber, null);

    /* Testing code
    bingoPlayers[0] = new BingoPlayer("Player1", bingoSize, maxBingoNumber, new int[][]{
      new int[]{24, 2, 8, 1, 25},
      new int[]{12, 16, 7, 17, 15},
      new int[]{5, 6, 20, 19, 13},
      new int[]{14, 23, 22, 4, 3},
      new int[]{10, 18, 11, 21, 9},
    });

    bingoPlayers[1] = new BingoPlayer("Player2", bingoSize, maxBingoNumber, new int[][]{
      new int[]{24, 21, 17, 15, 6},
      new int[]{10, 3, 8, 18, 20},
      new int[]{14, 7, 16, 12, 5},
      new int[]{25, 23, 13, 19, 11},
      new int[]{22, 4, 9, 1, 2},
    });
     */
  }


  public void validateNumber(int num) throws Exception {
    if (num < 0 || num > maxBingoNumber) {
      throw new Exception("The number must be between 1 to 25, please call again!");
    } else if (bingoNumbers.contains(num)) {
      throw new Exception("The number " + num + " is repeated, please call again!");
    }
  }

  public void showPlayerCards() {
    for (BingoPlayer player : bingoPlayers)
      player.showCard();
  }

  public Boolean isAllBingo() {
    return Arrays.stream(bingoPlayers).allMatch(BingoPlayer::isBingoCalled);
  }

  public int gameHostCall() {
    System.out.println("Game Host call (0 to exit): ");
    return scanner.nextInt();
  }

  public void play() {
    this.showPlayerCards();

    int bingoNumber;

    do {
      bingoNumber = gameHostCall();
      try {
        validateNumber(bingoNumber);

        for (BingoPlayer player : bingoPlayers)
          player.markCalled(bingoNumber);

        bingoNumbers.add(bingoNumber);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } while (bingoNumber != 0 && !isAllBingo());
  }
}
