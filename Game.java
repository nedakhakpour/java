import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
 * In Game.java, the game is played and run. The class constructor takes in two arguments, mode and
 * the GameBoard object, and sets them to the class variables of the two. The start() method chooses
 * between the 4 different game modes: 1 for player vs player, 2 for computer vs player, 3 for player vs
 * computer and 4 for computer vs computer. Depending on the option selected in the main menu, one of
 * the methods will execute.
 *
 * They all run virtually the same format, each abiding by the strategy game rules. Black goes first,
 * selecting a coordinate on the board, then there are verifications to validate that the move is allowed.
 * If not, the player will have to re-enter the coordinates until a valid entry is made. The valid
 * move is processed, and the board is changed to rearrange the disks. The score is tallied, then it is
 * the white turn. This same sequence is for white, then black again until the game is over and a result is found.
 *
 * The process is slightly different for computer moves. Rather than having someone enter a valid coordinate,
 * then verify it with the list of valid locations, the computer randomly selects a valid coordinate from the list
 * of valid choices, then the game processes that coordinate. This process happenss when the user selects enter.
 * This sequence also runs until a result is achieved.
 *
 * The game tallies the score and displays a message on the console informing the users on the result.
 **/

//Game class
class Game {

  //set class variables
  private static int gameMode;
  private static GameBoard board;
  //define class constants
  private static final String ENTER = "";
  private static final String BLACK_MOVES_FIRST = "Black Moves first";
  private static final String BLACK_PASS_TURN = "Black needs to passTurn... Passing to white";
  private static final String SEPARATOR = "----------------------------------------------------------";
  private static final String SUCCESS_FOR_BLACK = "Success: Black move at ";
  private static final String SUCCESS_FOR_WHITE = "Success: White move at ";
  private static final String SCORE_BLACK = "Score: Black: ";
  private static final String COMMA_WHITE = ", White: ";
  private static final String NO_ENTER_TRY_AGAIN = "You did not press enter. Try again. ";


  /**
   * the default contructor for the Game class
   *
   * @param mode - game mode selected
   * @param board - game board object
   */
  Game(int mode, GameBoard board) {
    //set input from user and created board as the class variables
    this.board = board;
    this.gameMode = mode;
  }

  /**
   * Switch/Case to determine which game mode method to execute based on inputted gameMode
   */
  static void start() {
    //switch based on integer inputted for game mode
    switch (gameMode) {
      //run the player vs player method
      case 1:
        startPP();
        break;
      //run the computer vs player method
      case 2:
        startCP();
        break;
      //run the player vs computer method
      case 3:
        startPC();
        break;
      //run the computer vs computer method
      case 4:
        startCC();
        break;
      //do nothing
      default:
    }
  }

  /**
   * runs the COMPUTER vs COMPUTER game
   */
  private static void startCC() {
    //declare variables
    Scanner input = new Scanner(System.in);
    Coordinate coordinate = new Coordinate(-1, -1);
    boolean flag = true;
    int result;
    Boolean passTurn;

    //display that black moves first, per the rules of the game
    System.out.println(BLACK_MOVES_FIRST);

    //while loop to run the game until completed
    while (flag) {
      //---For Black Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      ArrayList<Coordinate> blackLocations = board.getLocations('B', 'W');
      ArrayList<Coordinate> whiteLocations = board.getLocations('W', 'B');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for black to make, set the pass turn flag to true
      if (blackLocations.isEmpty()) {
        System.out.println(BLACK_PASS_TURN);
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the computer move for 'black'
        blackComputerMove(coordinate, blackLocations, input);
      }

      //---For White Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      whiteLocations = board.getLocations('W', 'B');
      blackLocations = board.getLocations('B', 'W');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for white to make, set the pass turn flag to true
      if (whiteLocations.isEmpty()) {
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the computer move for 'white'
        whiteComputerMove(coordinate, whiteLocations, input);
      }
    }
  }

  /**
   * runs COMPUTER BLACK vs PLAYER WHITE game
   */
  private static void startCP() {
    //declare variables
    Scanner input = new Scanner(System.in);
    Coordinate coordinate = new Coordinate(-1, -1);
    boolean flag = true;
    int result;
    Boolean passTurn;

    //display that black moves first, per the rules of the game
    System.out.println(BLACK_MOVES_FIRST);

    //while loop to run the game until completed
    while (flag) {
      //---For Black Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      ArrayList<Coordinate> blackLocations = board.getLocations('B', 'W');
      ArrayList<Coordinate> whiteLocations = board.getLocations('W', 'B');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for black to make, set the pass turn flag to true
      if (blackLocations.isEmpty()) {
        System.out.println(BLACK_PASS_TURN);
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the computer move for 'black'
        blackComputerMove(coordinate, blackLocations, input);
      }

      //---For White Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      whiteLocations = board.getLocations('W', 'B');
      blackLocations = board.getLocations('B', 'W');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for white to make, set the pass turn flag to true
      if (whiteLocations.isEmpty()) {
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the player move for 'white'
        whitePlayerMove(coordinate, whiteLocations, input);
      }
    }
  }

  /**
   * runs the PLAYER BLACK VS COMPUTER WHITE game
   */
  private static void startPC() {
    //declare variables
    Scanner input = new Scanner(System.in);
    Coordinate coordinate = new Coordinate(-1, -1);
    boolean flag = true;
    int result;
    Boolean passTurn;

    //display that black moves first, per the rules of the game
    System.out.println(BLACK_MOVES_FIRST);

    //while loop to run the game until completed
    while (flag) {
      //---For Black Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      ArrayList<Coordinate> blackLocations = board.getLocations('B', 'W');
      ArrayList<Coordinate> whiteLocations = board.getLocations('W', 'B');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for black to make, set the pass turn flag to true
      if (blackLocations.isEmpty()) {
        System.out.println(BLACK_PASS_TURN);
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the player move for 'black'
        blackPlayerMove(coordinate, blackLocations, input);
      }

      //---For White Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      whiteLocations = board.getLocations('W', 'B');
      blackLocations = board.getLocations('B', 'W');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for white to make, set the pass turn flag to true
      if (whiteLocations.isEmpty()) {
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the computer move for 'white'
        whiteComputerMove(coordinate, whiteLocations, input);
      }
    }
  }


  /**
   * runs the PLAYER VS PLAYER game
   */
  private static void startPP() {
    //declare variables
    Scanner input = new Scanner(System.in);
    Coordinate coordinate = new Coordinate(-1, -1);
    boolean flag = true;
    int result;
    Boolean passTurn;

    System.out.println(BLACK_MOVES_FIRST);

    while (flag) {
      //---For Black Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      ArrayList<Coordinate> blackLocations = board.getLocations('B', 'W');
      ArrayList<Coordinate> whiteLocations = board.getLocations('W', 'B');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for black to make, set the pass turn flag to true
      if (blackLocations.isEmpty()) {
        System.out.println(BLACK_PASS_TURN);
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the player move for 'black'
        blackPlayerMove(coordinate, blackLocations, input);
      }

      //---For White Turn----
      //set pass turn to false
      passTurn = false;
      //create 2 ArrayLists to hold the possible coordinates to be made for Black and White players
      whiteLocations = board.getLocations('W', 'B');
      blackLocations = board.getLocations('B', 'W');
      //display the board
      board.displayBoard(board);
      //set the result of game to check the board for valid moves
      result = board.finalResult(whiteLocations, blackLocations);

      //if the result is either 1, 0, or -1, end the game, set loop flag to false, to jump out of the loop
      if (result == 1 || result == 0 || result == -1) {
        //validate the result and display the winner/loser, tie game prompt
        validateResult(result);
        //change loop flag to jump out of the loop
        flag = false;
      }

      //if there are no moves for white to make, set the pass turn flag to true
      if (whiteLocations.isEmpty()) {
        passTurn = true;
      }

      //if the pass turn flag is not true
      if (!passTurn) {
        //run the method to get the player move for 'white'
        whitePlayerMove(coordinate, whiteLocations, input);
      }
    }
  }

  /**
   * validates the final result
   *
   * @param result - the int result
   */
  private static void validateResult(int result) {
    //if the result is equal to 0, then the game was a tie
    if (result == 0) {
      //print the tie message
      System.out.println("Tie Game!");
      //else if the result is equal to 1, then white won the game
    } else if (result == 1) {
      //print the game over message
      System.out.println("Game over. No moves found for Black");
      //else if the result is equal to 1, then black won the game
    } else if (result == -1) {
      //print the game over message
      System.out.println("Game over. No moves found for White");
    }
  }

  /**
   * get the coordinate coordinates for the computer on the black team to make, and make the move on the board
   *
   * @param coordinate - the coordinate object to hold the coordinates
   * @param blackLocations - List of coordinates that are possible moves for black
   * @param input - scanner object to get user inputted coordinates
   */
  private static void blackComputerMove(Coordinate coordinate, ArrayList<Coordinate> blackLocations, Scanner input) {
    //random object
    Random random = new Random();
    //declare enter key to verify if enter was pressed
    String enterKey;
    //do while loop to ask player to press enter to continue move. Do while used to validate any wrong input or choice. Exits loop once there is valid action
    do {
      //Prompt user it is black's turn, and to press enter, get user action
      System.out.println("Black turn. Press Enter.");
      enterKey = input.nextLine();
      //if the input action is enter
      if (enterKey.equals(ENTER)) {
        //get random coordinate from valid locations list
        coordinate = blackLocations.get(random.nextInt(blackLocations.size()));
        //set the value of the random coordinate into the x and y of the Coordinate object
        coordinate.y = (Integer.parseInt(Character.toString(coordinate.toString().charAt(1)))) - 1;
        coordinate.x = (Integer.parseInt(Character.toString(coordinate.toString().charAt(4)))) - 1;

        //take the valid move coordinate, and move to that place
        board.movePlace(coordinate, 'B', 'W');
        //update the score of the game
        board.updateScore();
        //display success of the move, and display the updated score, followed by a separator to divide each turn
        System.out.println("\n" + SUCCESS_FOR_BLACK + coordinate.toString());
        System.out.println(SCORE_BLACK + board.blackScore + COMMA_WHITE + board.whiteScore);
        System.out.println(SEPARATOR);
      } else {
        //else, print message that action was not valid, and try again, loops through once more
        System.out.print(NO_ENTER_TRY_AGAIN);
      }
    } while (!enterKey.equals(ENTER)); //execute the loop while enterKey is not pressed
  }

  /**
   * get the coordinate coordinates from the player on the black team to make, check if the move is valid, and make the move on the board
   *
   * @param coordinate - the coordinate object to hold the coordinates
   * @param blackLocations - List of coordinates that are possible moves for black
   * @param input - scanner object to get user inputted coordinates
   */
  private static void blackPlayerMove(Coordinate coordinate, ArrayList<Coordinate> blackLocations, Scanner input) {
    //declare string to hold user coordinates
    String coordinates;
    //prompt to show it is white teams turn, and to get inputted the coordinates
    System.out.println("Black move at: ");
    coordinates = input.next();

    //set the coordinate values from the parsed user input
    coordinate.y = (Integer.parseInt(Character.toString(coordinates.charAt(1)))) - 1;
    coordinate.x = (Integer.parseInt(Character.toString(coordinates.charAt(3)))) - 1;

    //while the inputted coordinate value is not a permissible move
    while (!blackLocations.contains(coordinate)) {
      //alert user of the invalid move, and ask again for another move
      System.out.println("Invalid move!\n\nBlack move at: ");
      coordinates = input.next();
      //set the new inputted value, and loop check again
      coordinate.y = (Integer.parseInt(Character.toString(coordinates.charAt(1)))) - 1;
      coordinate.x = (Integer.parseInt(Character.toString(coordinates.charAt(3)))) - 1;
    }

    //take the valid move coordinate, and move to that place
    board.movePlace(coordinate, 'B', 'W');
    //update the score of the game
    board.updateScore();
    //display success of the move, and display the updated score, followed by a separator to divide each turn
    System.out.println("\n" + SUCCESS_FOR_BLACK + coordinate.toString());
    System.out.println(SCORE_BLACK + board.blackScore + COMMA_WHITE + board.whiteScore);
    System.out.println(SEPARATOR);
  }

  /**
   * get the coordinate coordinates for the computer on the white team to make, and make the move on the board
   *
   * @param coordinate - the coordinate object to hold the coordinates
   * @param whiteLocations - List of coordinates that are possible moves for white
   * @param input - scanner object to get user enter action
   */
  private static void whiteComputerMove(Coordinate coordinate, ArrayList<Coordinate> whiteLocations, Scanner input) {
    //random object
    Random random = new Random();
    //declare enter key to verify if enter was pressed
    String enterKey;
    //do while loop to ask player to press enter to continue move. Do while used to validate any wrong input or choice. Exits loop once there is valid action
    do {
      //Prompt user it is white's turn, and to press enter, get user action
      System.out.println("White turn. Press Enter.");
      enterKey = input.nextLine();
      //if the input action is enter
      if (enterKey.equals(ENTER)) {
        //get random coordinate from valid locations list
        coordinate = whiteLocations.get(random.nextInt(whiteLocations.size()));
        //set the value of the random coordinate into the x and y of the Coordinate object
        coordinate.y = (Integer.parseInt(Character.toString(coordinate.toString().charAt(1)))) - 1;
        coordinate.x = (Integer.parseInt(Character.toString(coordinate.toString().charAt(4)))) - 1;

        //take the valid move coordinate, and move to that place
        board.movePlace(coordinate, 'W', 'B');
        //update the score of the game
        board.updateScore();
        //display success of the move, and display the updated score, followed by a separator to divide each turn
        System.out.println("\n" + SUCCESS_FOR_WHITE + coordinate.toString());
        System.out.println(SCORE_BLACK + board.blackScore + COMMA_WHITE + board.whiteScore);
        System.out.println(SEPARATOR);
      } else {
        //else, print message that action was not valid, and try again, loops through once more
        System.out.print(NO_ENTER_TRY_AGAIN);
      }
    } while (!enterKey.equals(ENTER)); //execute the loop while enterKey is not pressed
  }

  /**
   * get the coordinate coordinates from the player on the white team to make, check if the move is valid, and make the move on the board
   *
   * @param coordinate - the coordinate object to hold the coordinates
   * @param whiteLocations - List of coordinates that are possible moves for white
   * @param input - scanner object to get user inputted coordinates
   */
  private static void whitePlayerMove(Coordinate coordinate, ArrayList<Coordinate> whiteLocations, Scanner input) {
    //declare string to hold user coordinates
    String coordinates;
    //prompt to show it is white teams turn, and to get inputted the coordinates
    System.out.println("White move at: ");
    coordinates = input.next();

    //set the coordinate values from the parsed user input
    coordinate.y = (Integer.parseInt(Character.toString(coordinates.charAt(1)))) - 1;
    coordinate.x = (Integer.parseInt(Character.toString(coordinates.charAt(3)))) - 1;

    //while the inputted coordinate value is not a permissible move
    while (!whiteLocations.contains(coordinate)) {
      //alert user of the invalid move, and ask again for another move
      System.out.println("Invalid move!\n\nWhite move at: ");
      coordinates = input.next();
      //set the new inputted value, and loop check again
      coordinate.y = (Integer.parseInt(Character.toString(coordinates.charAt(1)))) - 1;
      coordinate.x = (Integer.parseInt(Character.toString(coordinates.charAt(3)))) - 1;
    }

    //take the valid move coordinate, and move to that place
    board.movePlace(coordinate, 'W', 'B');
    //update the score of the game
    board.updateScore();
    //display success of the move, and display the updated score, followed by a separator to divide each turn
    System.out.println("\n" + SUCCESS_FOR_WHITE + coordinate.toString());
    System.out.println(SCORE_BLACK + board.blackScore + COMMA_WHITE + board.whiteScore);
    System.out.println(SEPARATOR);
  }
}