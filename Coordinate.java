/**
 * Name: Neda Khakpour
 * Class: 2336.501
 * Running the program: With all the java files together in the same folder or workspace,
 * execute Project.java to run the program.
 *
 *
 * -Analysis-
 * This project implements a strategy board game played by two players, Black and
 * White. It is played on an N by N board. The winner is the player who has more
 * discs of his color than his opponent at the end of the game. This will happen when
 * neither of the two players has a legal move or there are no spaces left on the board.
 *
 * At the beginning of the game, two black discs and two white discs are placed in the
 * middle of the board. Black always begins, and the two players subsequently take turns moving.
 *
 * At each turn, a player must place a disc of his color on one of the empty squares of
 * the board, adjacent to an opponent's disc. No two matching colors are connected
 * vertically or horizontally so a miniature-chequered pattern is made.
 *
 * Both players take turns to make their move which consists of placing one piece
 * down in a legally acceptable position and then turning any of the opposing player’s
 * pieces where applicable. A legal move is one that consists of, for example, a black
 * piece being placed on the board that creates a straight line (vertical, horizontal
 * or diagonal which diagonal is optional for this project) made up of a black piece
 * at either end and only white pieces in between. When a player achieves this, they
 * must complete the move by turning any white pieces in between the two black so that
 * they line becomes entirely black. This turning action must be completed for every legal
 * turning line that is created with the placing of the new piece. Players will then continue
 * to move alternately until they get to the end of the game and a winner is decided. This decision
 * is reached by identifying which of the two opponents has the most pieces on the board.
 *
 * -Design-
 * In Coordinate.java, contains the class for a coordinate object. It has 2 class variables, x and y.
 * The constructor is used to get the x and y coordinates for the class to use to create a cell.
 * The three methods, toString(), equals(), and hashCode() are overridden to work for this project
 * and display coordinates.
 **/

public class Coordinate {

  //declare class variables
  int x;
  int y;

  /**
   * Coordinate class constructor
   */
  Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * toString method for Coordinate Class
   */
  @Override
  public String toString() {
    return "(" + (y + 1) + ", " + (x + 1) + ")";
  }

  /**
   * equals method for Coordinate class
   */
  @Override
  public boolean equals(Object obj) {
    return obj.hashCode() == this.hashCode();
  }

  /**
   * hashCode method for Coordinate class
   */
  @Override
  public int hashCode() {
    return Integer.parseInt(Integer.toString(x) + "" + Integer.toString(y));
  }
}