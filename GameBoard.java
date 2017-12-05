import java.util.ArrayList;
import java.util.List;

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
 * In GameBoard.java, the board of the game is processed and re-processed inorder to find valid
 * coordinates, whether it be horizontally, diagonally, or vertically. The game board is also drawn up
 * and the disks are changed for each move made. This class is used more as a utility to the game itself,
 * processing all moves and drawing up what the user sees.
 *
 * The GameBoard class has a default constructor that takes the int value of the size of the board. Based
 * on that value, a char array of those sizes is created, and the center of the board is also calculated
 * and the disks are places in the center (in the appropriate order 'W' 'B', 'B' 'W'. The getLocations method
 * executes every possible valid move on the board, by going through each cell, empty, or filled, and verifying
 * possible or valid moves. If there is a valid location, the coordinate is added to the positions list.
 *
 * The finalResult method is used to determine the result, if found. The result checks to see who won the game,
 * or tied the game based on the game requirements. If non of the game requirements are met, the game continues until
 * it is. The updateScore method increments the scores based on the number of disks for each respective color
 * there are on the board. The move place method is used to changes the values of the disks once a move
 * has been made.
 *
 * All of these methods are used on a per-turn basis in the Game class, each being used to shape the game
 * and the board.
 **/

//GameBoard class
class GameBoard {

  //set class variables
  private char[][] board;
  int whiteScore = 0;
  int blackScore = 0;
  private int balance;
  private int size;

  /**
   * default constructor for GameBoard class
   *
   * @param size - the inputted size for the dimension of the board
   */
  GameBoard(int size) {
    //set input from user for the size as the class variable
    this.size = size;
    //create new 2d char array, using the inputted size as the array sizes
    board = new char[size][size];
    //calculate where the center of the board to place the disks
    int center1 = size / 2;
    int center2 = (size / 2) - 1;

    //create the board using an underscore to fill empty spots
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        board[i][j] = '_';
      }
    }
    //set 'W' and 'B' disks in the center spots of the board with the calculated center coordinates
    board[center1][center1] = 'W';
    board[center1][center2] = 'B';
    board[center2][center1] = 'B';
    board[center2][center2] = 'W';
  }

  /**
   * Traverses through each square of the board to find valid moves. Multiple if and while loops are run
   * to check if the value of the square on the grid is either 'B', 'W', or '_'. The method increments and
   * decrements indexes of the outer and inner loops, holding the values in temp variables p and q. The
   * method locates valid positions horizontally, vertically, and diagonally. When a valid location is found,
   * it is added to the ArrayList.
   *
   * @param blackPlayer - the black player disk value  'B'
   * @param whitePlayer - the white player disk value 'W'
   */
  ArrayList<Coordinate> getLocations(char blackPlayer, char whitePlayer) {
    //empty array list of valid coordinates
    ArrayList<Coordinate> position = new ArrayList<>();
    //for loop to traverse through board
    for (int i = 0; i < size; ++i) {
      //inner for loop to traverse through other axis of board
      for (int j = 0; j < size; ++j) {
        //if the board at indexed coordinate i, j is equal to 'W'
        if (board[i][j] == whitePlayer) {
          //set p and q to indexed value of i and j
          int p = i;
          int q = j;

          //if index i -1 and j-1 is greater than or equal to 0 and that coordinate is equal to '_' on the grid
          if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == '_') {
            //increment i and j
            i++;
            j++;
            //while loop to run until i and j are equal to size - 1, and the board at coordinates i,j equal 'W'
            while (i < size - 1 && j < size - 1 && board[i][j] == whitePlayer) {
              //while those conditions are true, increment i and j again
              i++;
              j++;
            }

            //if index i -1 and j-1 is less than or equal to size - 1 and the coordinate for i,j is equal to 'B' on the grid
            if (i <= size - 1 && j <= size - 1 && board[i][j] == blackPlayer) {
              //add position p-1, q-1 as a valid coordinate in the position list
              position.add(new Coordinate(p - 1, q - 1));
            }
          }

          //set value for p and q into i and j respectively
          i = p;
          j = q;

          //check if i -1 is greater than 0 and the board at coordinate i-1,j is equal to '_'
          if (i - 1 >= 0 && board[i - 1][j] == '_') {
            //increment i
            i++;
            //while i is less than size-1 and the board at i,j is equal to 'W'
            while (i < size - 1 && board[i][j] == whitePlayer) {
              //while those conditions are true, increment i again
              i++;
            }

            //if i is less than or equal to size-1 and at board i,j is equal to 'B'
            if (i <= size - 1 && board[i][j] == blackPlayer) {
              //add position p-1, q as a valid coordinate in the position list
              position.add(new Coordinate(p - 1, q));
            }
          }

          //set value for p back into i
          i = p;

          //if i-1 is greater than or equal to 0, and j+1 is less than or equal to size-1, and the board at those coordinates equal '_'
          if (i - 1 >= 0 && j + 1 <= size - 1 && board[i - 1][j + 1] == '_') {
            //increment i and decrement j
            i++;
            j--;
            //while index i is less than size-1 and j is greater than 0, and the board at i,j is equal to 'W'
            while (i < size - 1 && j > 0 && board[i][j] == whitePlayer) {
              //while those conditions are true, increment i and decrement j again
              i++;
              j--;
            }

            //if i is less than or equal to size -1 and j is greater than or equal to 0 and the board at i,j is equal to 'B'
            if (i <= size - 1 && j >= 0 && board[i][j] == blackPlayer) {
              //add position p-1, q+1 as a valid coordinate in the position list
              position.add(new Coordinate(p - 1, q + 1));
            }

            //set value of p and q into i and j
            i = p;
            j = q;

            //if j-1 is greater than or equal to 0, and board at i,j-1 is equal to '_'
            if (j - 1 >= 0 && board[i][j - 1] == '_') {
              //increment j
              j++;
              //while j is less than size-1 and the board at i,j is equal to 'W'
              while (j < size - 1 && board[i][j] == whitePlayer) {
                //while those conditions are true, increment j again
                j++;
              }

              //if j is less than or equal to size-1 and board at i,j is equal to 'B'
              if (j <= size - 1 && board[i][j] == blackPlayer) {
                //add position p, q-1 as a valid coordinate in the position list
                position.add(new Coordinate(p, q - 1));
              }
            }

            //set value of q into j
            j = q;

            //if j+1 is less than or equal to size-1 and the board at i,j+1 is equal to '_'
            if (j + 1 <= size - 1 && board[i][j + 1] == '_') {
              //decrement j
              j--;
              //while j is greater than 0 and the board at i,j is equal to 'W'
              while (j > 0 && board[i][j] == whitePlayer) {
                //while those conditions are true, decrement j again
                j--;
              }

              //if j is greater than or equal to 0 and the board at i,j is equal to 'B'
              if (j >= 0 && board[i][j] == blackPlayer) {
                //add position p, q+1 as a valid coordinate in the position list
                position.add(new Coordinate(p, q + 1));
              }
            }

            //set value of q into j
            j = q;

            //if i+1 is less than or equal to size-1 and j-1 is greater than or equal to 0, and the board at i+1,j-1 is equal to '_'
            if (i + 1 <= size - 1 && j - 1 >= 0 && board[i + 1][j - 1] == '_') {
              //decrement i and increment j
              i--;
              j++;

              //while i is greater than 0, and j is less than size-1 and the board i,j is equal to 'W'
              while (i > 0 && j < size - 1 && board[i][j] == whitePlayer) {
                //while those conditions are true, decrement i and increment j  again
                i--;
                j++;
              }
              //if i is greater than or equal to 0 and j is less than or equal to size-1 and the board at i,j is equal to 'B'
              if (i >= 0 && j <= size - 1 && board[i][j] == blackPlayer) {
                //add position p+1, q-1 as a valid coordinate in the position list
                position.add(new Coordinate(p + 1, q - 1));
              }
            }

            //set values of p and q into i and j
            i = p;
            j = q;

            //if i+1 is less than or equal to size-1 and the board at i+1,j is equal to '_'
            if (i + 1 <= size - 1 && board[i + 1][j] == '_') {
              //decrement i
              i--;
              //while i is greater than 0 and the board at i,j is equal to 'W'
              while (i > 0 && board[i][j] == whitePlayer) {
                //while those conditions are true, decrement i again
                i--;
              }
              //if i is greater than or equal to 0 and the board at i,j is equal to 'B'
              if (i >= 0 && board[i][j] == blackPlayer) {
                //add position p+1, q as a valid coordinate in the position list
                position.add(new Coordinate(p + 1, q));
              }
            }

            //set value of p into i
            i = p;

            //if i+1 is less than or equal to size -1 and j+1 is less than size-1 and the board at i+1,j+1 is equal to '_'
            if (i + 1 <= size - 1 && j + 1 <= size - 1 && board[i + 1][j + 1] == '_') {
              //decrement i and j
              i--;
              j--;
              //while i and j are greater than 0 and the board at i,j is equal to 'W'
              while (i > 0 && j > 0 && board[i][j] == whitePlayer) {
                //while those conditions are true, decrement i and j again
                i--;
                j--;
              }
              //if i is greater than or equal to 0 and j is greater than or equal to 0 and the board at i,j is equal to 'B'
              if (i >= 0 && j >= 0 && board[i][j] == blackPlayer) {
                //add position p+1, q as a valid coordinate in the position list
                position.add(new Coordinate(p + 1, q + 1));
              }
            }
            //finish loop by setting value of p and q into i and j
            i = p;
            j = q;
          } //end of if
        }//end of if
      }//end of inner loop
    }//end of outer loop
    return position;
  }

  /**
   * displays the board on the console
   *
   * @param board - the GameBoard object
   */
  void displayBoard(GameBoard board) {
    //new line for good display
    System.out.print("\n");
    //for loop to iterate the size of the board, and display the x-axis
    for (int i = 1; i <= size; i++) {
      System.out.print(" " + i);
    }
    //another new line
    System.out.println();
    //outer for loop to iterate through board
    for (int i = 0; i < size; i++) {
      //print the y-axis
      System.out.print(i + 1 + " ");
      //inner for loop iterate through board
      for (int j = 0; j < size; j++) {
        //print the board at i,j plus the space
        System.out.print(board.board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  /**
   * finds the final result
   *
   * @param blackLocations - list of valid black locations
   * @param whiteLocations - list of valid white locations
   */
  int finalResult(List<Coordinate> blackLocations, List<Coordinate> whiteLocations) {
    //update the score
    updateScore();

    //if the balance is 0 (no more squares on the board), or both whiteLocations and blackLocations lists are empty, then the game is over
    if (balance == 0 || whiteLocations.isEmpty() && blackLocations.isEmpty()) {
      //if the whiteScore is greater than the blackScore
      if (whiteScore > blackScore) {
        //White wins
        return 1;
        //else if blackScore is greater than whiteScore
      } else if (blackScore > whiteScore) {
        //Black wins
        return -1;
      } else {
        //else the game is a tie
        return 0;
      }
    }

    //if the value of whiteScore is 0 or the value of the blackScore is 0, then the game is over, and cannot be a tie
    if (whiteScore == 0 || blackScore == 0) {
      //if whiteScore is greater than 0, then White wins
      if (whiteScore > 0) {
        return 1;
        //else if blackScore is greater than 0, then Black wins
      } else if (blackScore > 0) {
        return -1;
      }
    }
    //default return -2...to keep the game going
    return -2;
  }

  /**
   * move the places of the coordinates to match the changed value
   *
   * @param coordinate - the coordinate object
   * @param blackPlayer - the char of the main players
   * @param whitePlayer - the char of the secondary player
   */
  void movePlace(Coordinate coordinate, char blackPlayer, char whitePlayer) {
    //set i as the x value of the coordinate
    int i = coordinate.x;
    //set j as the y value of the coordinate
    int j = coordinate.y;

    //set the value of the board at i,j to main player
    board[i][j] = blackPlayer;

    //set the values of i and j to p and q
    int p = i;
    int q = j;
    //if i-1 is greater than or equal to 0 and j-1
    if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == whitePlayer) {
      i--;
      j--;
      while (i > 0 && j > 0 && board[i][j] == whitePlayer) {
        i--;
        j--;
      }
      if (i >= 0 && j >= 0 && board[i][j] == blackPlayer) {
        while (i != p - 1 && j != q - 1) {
          board[++i][++j] = blackPlayer;
        }
      }
    }

    //segment 2
    i = p;
    j = q;
    if (i - 1 >= 0 && board[i - 1][j] == whitePlayer) {
      i--;
      while (i > 0 && board[i][j] == whitePlayer) {
        i--;
      }
      if (i >= 0 && board[i][j] == blackPlayer) {
        while (i != p - 1) {
          board[++i][j] = blackPlayer;
        }
      }
    }

    //segment 3
    i = p;
    if (i - 1 >= 0 && j + 1 <= size - 1 && board[i - 1][j + 1] == whitePlayer) {
      i--;
      j++;
      while (i > 0 && j < size - 1 && board[i][j] == whitePlayer) {
        i--;
        j++;
      }
      if (i >= 0 && j <= size - 1 && board[i][j] == blackPlayer) {
        while (i != p - 1 && j != q + 1) {
          board[++i][--j] = blackPlayer;
        }
      }
    }

    //segment 4
    i = p;
    j = q;
    if (j - 1 >= 0 && board[i][j - 1] == whitePlayer) {
      j--;
      while (j > 0 && board[i][j] == whitePlayer) {
        j--;
      }
      if (j >= 0 && board[i][j] == blackPlayer) {
        while (j != q - 1) {
          board[i][++j] = blackPlayer;
        }
      }
    }

    //segment 5
    j = q;
    if (j + 1 <= size - 1 && board[i][j + 1] == whitePlayer) {
      j++;
      while (j < size - 1 && board[i][j] == whitePlayer) {
        j++;
      }
      if (j <= size - 1 && board[i][j] == blackPlayer) {
        while (j != q + 1) {
          board[i][--j] = blackPlayer;
        }
      }
    }

    //segment 6
    j = q;
    if (i + 1 <= size - 1 && j - 1 >= 0 && board[i + 1][j - 1] == whitePlayer) {
      i++;
      j--;
      while (i < size - 1 && j > 0 && board[i][j] == whitePlayer) {
        i++;
        j--;
      }
      if (i <= size - 1 && j >= 0 && board[i][j] == blackPlayer) {
        while (i != p + 1 && j != q - 1) {
          board[--i][++j] = blackPlayer;
        }
      }
    }

    //segment 7
    i = p;
    j = q;
    if (i + 1 <= size - 1 && board[i + 1][j] == whitePlayer) {
      i++;
      while (i < size - 1 && board[i][j] == whitePlayer) {
        i++;
      }
      if (i <= size - 1 && board[i][j] == blackPlayer) {
        while (i != p + 1) {
          board[--i][j] = blackPlayer;
        }
      }
    }

    //segment 8
    i = p;
    if (i + 1 <= size - 1 && j + 1 <= size - 1 && board[i + 1][j + 1] == whitePlayer) {
      i++;
      j++;
      while (i < size - 1 && j < size - 1 && board[i][j] == whitePlayer) {
        i++;
        j++;
      }
      if (i <= size - 1 && j <= size - 1 && board[i][j] == blackPlayer) {
        while (i != p + 1 && j != q + 1) {
          board[--i][--j] = blackPlayer;
        }
      }
    }
  }

  /**
   * update the scores of the black and white teams
   */
  void updateScore() {
    //reset counters to 0
    whiteScore = 0;
    blackScore = 0;
    //outer loop to iterate through the x-axis
    for (int i = 0; i < size; ++i) {
      //inner loop to iterate through the y-axis
      for (int j = 0; j < size; ++j) {
        //if the board at location i,j is equal to 'W'
        if (board[i][j] == 'W') {
          //iterate the whiteScore
          whiteScore++;
          //if the board at i,j is equal to 'B'
        } else if (board[i][j] == 'B') {
          //iterate the blackScore
          blackScore++;
        } else {
          //iterate the remaining balance of spaces
          balance++;
        }
      }
    }
  }
}