import java.util.Scanner;

public class ConsoleDriver {

    private static Game checkers = new Game();

    public static int[] askPlayers(){
        Scanner myScan = new Scanner(System.in);
        String input;
        System.out.println("Player " + (checkers.getTurn() +1) + ": please enter the column number of the piece you would like to move");
        input = myScan.next();
        while (!((Integer.valueOf(input) >= 0)&&(Integer.valueOf(input) <=7))){
            System.out.println("Player " + (checkers.getTurn() +1) + ": the previous value you entered was invalid, please enter a number between " +
                                "0 and 7 for the column number of the piece you would like to move");
            input = myScan.next();
        }
        int iC = Integer.valueOf(input);

        System.out.println("Player " + (checkers.getTurn() +1) + " please enter the row number of the piece you would like to move");
        input = myScan.next();
        while (!((Integer.valueOf(input) >= 0)&&(Integer.valueOf(input) <=7))){
            System.out.println("Player " + (checkers.getTurn() +1) + ": the previous value you entered was invalid, please enter a number between " +
                    "0 and 7 for the row number of the piece you would like to move");
            input = myScan.next();
        }
        int iR= Integer.valueOf(input);

        System.out.println("Player " + (checkers.getTurn() +1) + " please enter the column number of the square you would like to move the piece to");
        if (myScan.hasNextLine()) {
            input = myScan.next();
        }
        while (!((Integer.valueOf(input) >= 0)&&(Integer.valueOf(input) <=7))){
            System.out.println("Player " + (checkers.getTurn() +1) + ": the previous value you entered was invalid, please enter a number between " +
                    "0 and 7 for the column number of the square you would like to move the piece to");
            input = myScan.next();
        }
        int fC = Integer.valueOf(input);

        System.out.println("Player " + (checkers.getTurn() +1) + " please enter the row number of the square you would like to move the piece to");
        input = myScan.next();
        while (!((Integer.valueOf(input) >= 0)&&(Integer.valueOf(input) <=7))){
            System.out.println("Player " + (checkers.getTurn() +1) + ": the previous value you entered was invalid, please enter a number between " +
                    "0 and 7 for the row number of the square you would like to move the piece to");
            input = myScan.next();
        }
        int fR = Integer.valueOf(input);

        return (new int[]{iR, iC, fC, fR});
    }

    public static int askBack(){
        Scanner myScan = new Scanner(System.in);
        System.out.println("Do you want to go back? Enter 1 if you would like to go back and 2 if not");
        String input = myScan.next();
        while (!((Integer.valueOf(input) >= 1)&&(Integer.valueOf(input) <=2))){
            System.out.println("Player " + (checkers.getTurn() +1) + ": the previous value you entered was invalid, please enter either a 1 or 2");
            input = myScan.next();
        }
        return Integer.valueOf(input);
    }

    public static int askWhatGame(){
        Scanner myScan = new Scanner(System.in);
        System.out.println("Would you like to play a 2 player game or a 1 player game against the computer?");
        System.out.println("enter a 1 to play a 2 player game or a 2 to play against the computer");
        String input = myScan.next();
        while (!((Integer.valueOf(input) >= 1)&&(Integer.valueOf(input) <=2))){
            System.out.println("The previous value you entered was invalid, please enter either a 1 or 2");
            input = myScan.next();
        }
        return Integer.valueOf(input);
    }

    public static void onePlayerGame(){

    }

    public static void twoPlayerGame(){

        System.out.println(checkers.currentBoard().toString());
        while (checkers.hasWon() == false){
            int[] coordinates = askPlayers();
            int iR = coordinates[0];
            int iC = coordinates[1];
            int fR = coordinates[2];
            int fC = coordinates[3];


            int team;
            if (checkers.getTurn() == 0){
                team = 1;
            }
            else{
                team = -1;
            }
            checkers.makeMove(checkers.currentBoard().getSquare(iR, iC), new Square(team, fR, fC));
            System.out.println(checkers.currentBoard().toString());

            int back = askBack();

            if (back == 1){
                checkers.back();
                System.out.println(checkers.currentBoard().toString());
            }
        }
        System.out.println("Player" + (checkers.getTurn() + 1) + "has won the ");
    }

    public static void main(String[] args) {
        /**
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

        int decision = askWhatGame();

        switch (decision){
            case 1:
                twoPlayerGame();
                break;
            case 2:
                onePlayerGame();
                break;
        }
    }
}
