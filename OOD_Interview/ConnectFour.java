
// Basics
// The game will be played by only two players, player vs player
// The game board should be of variable dimensions
// The target is to connect N discs in a row (vertically, horizontally or diagonally)
// N is a variable (e.g. connect 4, 5, 6, etc)
// There should be a score tracking system
// After a player reaches the target score, they are the winner

import java.io.*;
import java.util.*;


enum Piece{
  YELLOW,RED,EMPTY
}

// Player class
class Player{
  String name;
  Piece color;
  public Player(String name, Piece color){
    this.name = name;
    this.color = color;
  }
  public String getName(){
    return this.name;
  }
  public Piece getColor(){
    return this.color;
  }
}
// Grid class to maintain the state of the board and rules
class Grid{
  Piece[][] board;
  int targetN;
  public Grid(int m, int n, int targetN){
    this.board = new Piece[m][n];
    this.targetN= targetN;
    this.resetBoard();
  }
  public int placePiece(Piece piece, int column){
    if (piece == Piece.EMPTY || column < 0 || column > board[0].length){
      throw new RuntimeException();
    }
    for(int row = 0; row < board.length;row++){
        if (board[row][column] == Piece.EMPTY){
          board[row][column] = piece;
          return row;
        } 
      }
    return -1;
  }
  public boolean checkWin(int row, int col){
    Piece newPiece = board[row][col];
  
    int count = 0;
    for(int r = 0; r < board.length; r++){
      int c = r-(row-col);
      if(c>=0 &&c < board[0].length && board[r][c] == newPiece){
        count ++;
        if(targetN == count){
          return true;
        }
      }else{
      count = 0;
      }
    }
    count = 0;
    for(int r = 0; r < board.length; r++){
      int c = row+col-r;
      if(c >= 0 && c < board[0].length && board[r][c] == newPiece){
        count ++;
        if(targetN == count){
          return true;
        }
      }else{
      count = 0;
      }
    }
    count = 0;
    for(int r = 0; r < board.length; r++){
      
      if(col < board[0].length && board[r][col] == newPiece){
        count ++;
        if(targetN == count){
          return true;
        }
      }else{
      count = 0;
      }
    }
    count = 0;
    for(int c = 0; c < board[0].length; c++){
      
      if(row < board.length && board[row][c] == newPiece){
        count ++;
        if(targetN == count){
          return true;
        }
      }else{
      count = 0;
      }
    }

    return false;
  }
  public Piece[][] getBoard(){
    return this.board;
  }
  public void resetBoard(){
    for(int row = 0; row < board.length;row++){
      for(int col = 0; col < board[0].length; col++){
        board[row][col] = Piece.EMPTY;
      }
    }   
  }

}
// controller class, manage players,grid,playmove, score system, gameloop 
// can i put inputoutput in the controller class？
// it should normally be in the View class, Model View Controller
// 先实现正常的controller功能
//注意这里除了play是public以外，剩下的都是private，防止外部错误调用

// java的IO操作：system.in是标准输入流（键盘输入），system.out是标准输出流（console输出）
//通过创建scanner类来接受input，scanner构造器里传输入流，告诉scanner从哪里读，可以是标准输入流systemin也可以是一个文件或者一个字符串，
// nextInt()拿到整数，nextLine()拿到一行字符串

// score system： 就是单纯的一个hashmap，key是玩家，value是score，哪个玩家checkwin赢了就score+1，
// 然后如果哪个玩家先到了targetscore，哪个玩家就是最终的winner：维护一个maxscore，每次一个玩家获胜就尝试更新这个maxscore: maxScore = math.max(this.score.get(winner.getName()), maxScore)
class Game{
  Player[] players;
  Grid grid;
  int connectN;
  Scanner scanner;
  HashMap<Player, Integer> score;
  int targetScore;
  public Game(Grid grid, Player p1, Player p2, int targetScore){
    scanner = new Scanner(System.in);
    this.players = new Player[]{p1,p2};
    this.grid = grid;
    this.targetScore = targetScore;
    this.score = new HashMap<>();
    for(int i = 0; i< players.length; i++){
      score.put(players[i],0);
    }

  }
  private void printBoard(){
    System.out.println("Board:");
    Piece[][] grid = this.grid.getBoard();
    for(int i = grid.length-1; i >= 0; i--){
      // 一行一行打印
      String row = "";
      for(Piece piece: grid[i]){
        if(piece == Piece.EMPTY){
          row += "0 ";
        }else if(piece == Piece.RED){
          row += "R ";
        }else{
          row +="Y ";
        }
      }
      System.out.println(row);
    }
    System.out.println();
  }
  private int[] playOneMove(Player player){
    this.printBoard();
    System.out.println(player.getName() + "turn");
    int colCount = this.grid.board[0].length;
    System.out.println("enter column number from 0 to " + (colCount - 1) + "to add a piece");
    int moveColumn = scanner.nextInt();
    int moveRow = this.grid.placePiece(player.getColor(),moveColumn);
    return new int[]{moveRow,moveColumn};
  }
  private Player playOneRound(){
    while(true){
      for(int i = 0 ; i < this.players.length; i++){
       int[] newPiece = this.playOneMove(players[i]); 
       int row = newPiece[0];
       int col = newPiece[1];
       if (row == -1){
        throw new RuntimeException("invalid piece to play");
       }
       if(this.grid.checkWin(row, col)){
          System.out.println(players[i].getName() + " win this round~");
          return players[i];
       }
      }
    }
  }
  public void play(){
    int maxScore = 0;
    Player winner = null;
    while(maxScore < targetScore){
      winner = this.playOneRound();
      this.score.put(winner,this.score.get(winner)+1);
      maxScore = Math.max(this.score.get(winner), maxScore);
      this.grid.resetBoard();
    }
    System.out.println("winner is " + winner.getName()+ "!!!!!");
    
  }

}









/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    Player player1 = new Player("player1", Piece.RED);
    Player player2 = new Player("player2", Piece.YELLOW);
    Grid grid = new Grid(8,8,3);
    Game game = new Game(grid, player1, player2, 2);
    game.play();

    
  }
}
