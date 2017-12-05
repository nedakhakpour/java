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
 * In Project.java, it runs the main method to execute the game. It decalres a scanner object
 * to read in the users input, and 3 variables: size, mode, and decision. "Size" is used to
 * store the dimension of the board from the user that is greater than 2, mode is used to
 * store the the answer choice selected on which game mode to run, and decsion is used when
 * the user is prompted with a decision.
 *
 * A welcome prompt is printed on the screen, asking the user to display the rules or continue.
 * If "Y", the rules will be displayed, followed by the questions. If "N", the questions will display.
 * The first question is the game mode: 1 for player vs player, 2 for computer vs player, 3 for player
 * vs computer, and 4 for computer vs computer (Black and White respectively). The program checks if the
 * users input is valid, if not, it will continue to ask until the valid input is entered. The second
 * question is the size of the board. It must be an integer greater than 2. If the input is not valid,
 * the program will continue to ask until the valid input is entered.
 *
 * When the variables have been set, the program creates a "GameBoard" object, and "Game" object
 * to initialize the game with the inputted parameters. The "start" method in the game class is then
 * called to run the program.
 *
 * This whole process is wrapped in a while loop. The player is asked after completion of the game
 * if they would like to play again. The decision is stored the validated in an if statement. If no,
 * the loop is broken and the program ends. If yes, the loop iterates again until complete.
 **/

public class Project {
  public static void main(String[] args) {
    //declare variables
    Scanner input = new Scanner(System.in);
    int size;
    int mode;
    String decision;

    //welcome prompt to introduce players
    System.out.println("Welcome to Strategy Board Game! \n ");
    //ask player if they would like to read the rules, store input answer into decision variable
    System.out.println("Would you like to view the rules before playing? Enter 'Y' to view and 'N' to continue: ");
    decision = input.next();

    //if input is 'y', display the rules given for the strategy game
    if (decision.equalsIgnoreCase("y")) {
      System.out.println("\nHere are the rules before you play: \n" +
                           "This is a strategy board game played by two sides, Black and\n" +
                           "White. It is played on an N by N board. The winner is the player who has more\n" +
                           "discs of his color than his opponent at the end of the game. This will happen when\n" +
                           "neither of the two players has a legal move or there are no spaces left on the board.\n\nIn the beginning of the game, two black discs and two white discs are placed in the\n" +
                           "middle of the board. Black always begins, and the two players subsequently take turns moving." +
                           "\nDuring a turn, a player must place a disc of his color on one of the empty squares of\n" +
                           "the board, adjacent to an opponent's disc. No two matching colors are connected\n" +
                           "vertically or horizontally so a miniature-chequered pattern is made.\n\n" +
                           "Both sides take turns to make their move which consists of placing one piece\n" +
                           "down in a legally acceptable position and then turning any of the opposing player’s\n" +
                           "pieces where applicable. A legal move is one that consists of, for example,\na black piece being placed on the" +
                           "board that creates a straight line (vertical, " +
                           "\nhorizontal or diagonal) made up of a black piece at either end and only white\n" +
                           "pieces in between. When a side achieves this, they must complete the move by\n" +
                           "turning any white pieces in between the two black so that they line becomes entirely\n" +
                           "black. This turning action must be completed for every legal turning line that is\n" +
                           "created with the placing of the new piece. Players will then continue to move \nalternately until they get to the end of the game" +
                           "and a winner is decided. \nThis decision is reached by identifying which of the two\n" +
                           "opponents has the highest score (most pieces on the board).");
    }
    //begin game menu
    System.out.println("\n\nGAME SETTINGS:");
    //loop the whole program with while loop
    while (true) {
      //do while loop to ask player what game mode to play in given the 4 choices. Do while used to validate any wrong input or choice. Exits loop once there is valid input and stores into "mode" variable
      do {
        //print the game mode prompt
        System.out.println("Please select a game mode with menu options 1, 2, 3, or 4:\n1) Player (Black) vs Player (White)\n2) Computer (Black) vs Player (White)\n"
                             + "3) Player (Black) vs Computer (White)\n4) Computer (Black) vs Computer (White)");
        //if the input is not an integer
        while (!input.hasNextInt()) {
          //try again prompt
          System.out.println("That is not a valid input. Please select a game mode by typing 1, 2, 3, or 4:\n1) Player (Black) vs Player (White)\n2) Computer (Black) vs Player (White)\n"
                               + "3) Player (Black) vs Computer (White)\n4) Computer (Black) vs Computer (White)");
          //get new input
          input.next();
        }
        //store input
        mode = input.nextInt();
        //if mode input is not an integer between 1 and 4...
        if (mode < 1 || mode > 4) {
          //...display not valid option prompt
          System.out.print("That is not a valid option. ");
        }
      } while (mode < 1 || mode > 4); //execute the loop while mode is not between 1 and 4

      //another do while loop to ask player what size the dimensions the board should be. Do while used to validate any wrong input or choice. Exits loop once there is valid input and stores into "mode" variable
      do {
        //print the board size prompt
        System.out.println("Please enter a number greater than 2 for the dimension size of the board: ");
        //if the input is not an integer
        while (!input.hasNextInt()) {
          //try again prompt
          System.out.println("That is not a valid input. Please enter a number for the dimension size of the board that is greater than 2: ");
          //get new input
          input.next();
        }
        //store input
        size = input.nextInt();
        //if mode input is not an integer greater than 2...
        if (size <= 2) {
          //...display not valid option prompt
          System.out.print("That is not a valid option. ");
        }
      } while (size <= 2); //execute the loop while mode is less than or equal to 2

      //create GameBoard object, inputting size of the game board
      GameBoard board = new GameBoard(size);
      //create Game object, inputting the game board and game mode
      Game game = new Game(mode, board);
      //run the game using the start method in the Game class
      game.start();

      //After game has run, ask player to play again, store the input into the decision variable
      System.out.println("Would you like to play again? Enter 'Y' for yes and 'N' for no.");
      decision = input.next();

      //if input is 'n', display good bye message and end the loop (terminating the program)...If 'y', the program loops again
      if (decision.equalsIgnoreCase("n")) {
        //display "Thanks for playing", break out of the while loop
        System.out.println("Thanks for playing!");
        break;
      }
    }
  }
}