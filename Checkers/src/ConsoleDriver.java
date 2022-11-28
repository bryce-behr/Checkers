import java.util.Scanner;

public class ConsoleDriver {
    public static void main(String[] args) {
        /*
        * bulk of logic elsewhere
        * this should only have I/O logic
        * meaning talk to user, get user input, show changes to user
        *
        * e.g.
        * create an object of type Game, Adventure...
        * Game g = new Game();
        * maybe while(!g.hasWon())
        * ask user for input
        * call g.displayStatus() --- or displayBoard()
        *
        * can add a gui with minimal if any changes to game logic
        */
        Game checkers = new Game();

        while (checkers.hasWon() == false){
            Scanner myScan = new Scanner(System.in);
            System.out.println("Player " + (checkers.getTurn() +1) + " please enter the x coordinate of the piece you would like to move");
            int iX = Integer.valueOf(myScan.nextLine());
            System.out.println("Player " + (checkers.getTurn() +1) + " please enter the y coordinate of the piece you would like to move");
            int iY = Integer.valueOf(myScan.nextLine());

            System.out.println("Player " + (checkers.getTurn() +1) + " please enter the x coordinate of where you would like to move the piece to");
            int fX = Integer.valueOf(myScan.nextLine());
            System.out.println("Player " + (checkers.getTurn() +1) + " please enter the y coordinate of where you would like to move the piece to");
            int fY = Integer.valueOf(myScan.nextLine());
        }

        System.out.println("Player" + (checkers.getTurn() + 1) + "has won the ");
    }
}
