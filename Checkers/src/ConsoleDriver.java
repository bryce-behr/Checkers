import java.util.Scanner;

public class ConsoleDriver {

    /**
     * Member variables
     */
    private static Game checkers;

    /**
     * Method
     * asks the player whose turn it is the coordinates of
     * the piece they want to move and where they want to move it to.
     * @return An array of integers in the following order: the row of the piece they want to move,
     *          the column of the piece they want to move, the row they want to move the piece to,
     *          the row they want to move the piece to.
     */
    public static int[] askPlayers(int cteam){
        /*
         * team == 0 --> black
         * team == 1 --> red
         */
        int team = 2;
        int iR = 0;
        int iC = 0;
        int fR = 0;
        int fC = 0;
        boolean valid = false;

        Scanner myScan = new Scanner(System.in);
        Scanner validScanner;
        String input = "";

        while (!valid) {
            System.out.print("Player " + (checkers.getTurn() +1)  + " which piece would you like to move: ");
            input = myScan.next();
            while(checkInvalid(input)) {
                System.out.print("Invalid Input: Enter piece coordinates in proper notation i.e. \"b4\": ");
                input = myScan.next();
            }
            iC = Math.abs(input.charAt(0) - 97);
            iR = Math.abs(input.charAt(1) - 48 - 7 - 1);

            if (checkers.currentBoard().getSquare(iR, iC).getTeam() > 0){
                team = 1;
            }
            else if (checkers.currentBoard().getSquare(iR, iC).getTeam() < 0){
                team = 0;
            }
            else{
                team = 2;
            }

            if (team == cteam){
                valid = true;
            }
            else{
                System.out.println("Please select a valid square");
            }
        }

        valid = false;
        while (!valid) {
            System.out.print("Move to -> ");
            input = myScan.next();
            while(checkInvalid(input)) {
                System.out.print("Invalid Input: Enter piece coordinates in proper notation i.e. \"b4\": ");
                input = myScan.next();
            }
            fC = Math.abs(input.charAt(0) - 97);
            fR = Math.abs(input.charAt(1) - 48 - 7 - 1);

            if (checkers.currentBoard().checkValid((checkers.currentBoard().getSquare(iR, iC)), (checkers.currentBoard().getSquare(fR, fC)))){
                valid = true;
            }
            else{
                System.out.println("You are moving to an invalid square");
            }
        }

        System.out.println();

        return (new int[]{iR, iC, fR, fC});
    }

    /**
     * Method
     * asks whether the player whose turn it is would like to go back and change their move
     * @return AN int (1 if they want to go back and 2 if they don't want to go back)
     */
    public static int askBack(){
        Scanner myScan = new Scanner(System.in);
        System.out.print("Do you want to go back? Enter 1 if you would like to go back and 2 if not: ");
        String input = myScan.next();
        while (input.length() != 1 || (int)input.charAt(0) < 49 || (int)input.charAt(0) > 50) {
            System.out.print("Player " + (checkers.getTurn() +1) + " the previous value you entered was invalid, please enter either a 1 or 2: ");
            input = myScan.next();
        }

        return Integer.valueOf(input);
    }

    /**
     * Method
     * asks the user if they want to play a two player game or a game against the computer
     * @return An int (1 if they want to play a two player game and 2 of they want to play against the computer
     */
    public static int askWhatGame(){
        Scanner myScan = new Scanner(System.in);
        System.out.println("Would you like to play a one player game or a 2 player game of checkers?");
        System.out.println("enter a '1' to play a one player game and enter '2' to play a 2 player game");
        String input = myScan.next();
        while (!((Integer.valueOf(input) >= 1)&&(Integer.valueOf(input) <=2))){
            System.out.println("The previous value you entered was invalid, You entered: " + input +" please enter either a '1' or '2'");
            input = myScan.next();
        }
        return Integer.valueOf(input);
    }

    /**
     * Method
     * runs a loop for a checkers game against the computer
     */
    public static void onePlayerGame(){
        int team = 0;
        int comp = 0;
        checkers = new Game();
        System.out.println(checkers.currentBoard().toString());

        Scanner myScan = new Scanner(System.in);
        String input = "";

        System.out.println("Would you like to be X's or O's ? ");
        System.out.print("Pleas enter '0' to be X's and '1' to be O's ");

        input = myScan.next();
        while (!((Integer.valueOf(input) >= 0) && (Integer.valueOf(input) <= 1))) {
            System.out.print("Please enter eiter '0' or '1': ");
            input = myScan.next();
            System.out.println();
        }
        System.out.println();
        team = Integer.valueOf(input);
        if (team == 0){
            comp = 1;
        }
        else{
            comp = -1;
        }

        while (!checkers.hasWon()){
            if (checkers.getTurn() != team){
                System.out.println("Opponent Move: ");
                checkers.makeRandomMove(comp);
                //checkers.incTurn();
            }
            else{
                int[] coordinates = askPlayers(checkers.getTurn());
                int iR = coordinates[0];
                int iC = coordinates[1];
                int fR = coordinates[2];
                int fC = coordinates[3];

                System.out.println("Your Move: ");
                checkers.makeMove(checkers.currentBoard().getSquare(iR, iC), checkers.currentBoard().getSquare(fR, fC));
            }
        }
        if (checkers.getTurn() == team){
            System.out.println("You have won the game");
        }
        else{
            System.out.println("You have lost the game");
        }

    }

    /**
     * Method
     * runs a loop for a checkers game against another player
     */
    public static void twoPlayerGame(){
        checkers = new Game();

        System.out.println(checkers.currentBoard().toString());
        System.out.println("Player " + (checkers.getTurn() + 1) + " starts the game");

        while (!checkers.hasWon()){
            int[] coordinates = askPlayers(checkers.getTurn());
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

            checkers.makeMove(checkers.currentBoard().getSquare(iR, iC), checkers.currentBoard().getSquare(fR, fC));

            int back = askBack();

            if (back == 1){
                checkers.back();
                System.out.println(checkers.currentBoard().toString());
            }
        }
        System.out.println("Player" + (checkers.getTurn() + 1) + "has won the ");
    }

    /**
     * Main method
     * @param args
     */
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
        boolean flag = true;
        while (flag) {
            int decision = askWhatGame();
            switch (decision) {
                case 1 -> onePlayerGame();
                case 2 -> twoPlayerGame();
            }
            System.out.println("Game over...");
            System.out.println("Starting new game");
        }
        System.out.println("PROGRAM CLOSED");
    }

    /**
     * this method checks for an invalid user input
     * @param str the user input
     * @return true if invalid input, false if valid input
     */
    private static boolean checkInvalid(String str) {
        char tempChar;
        int tempInt;

        if(str.length() == 2) {
            tempChar = str.charAt(0);
            tempInt = (int)str.charAt(1);
            return (int) tempChar < 97 || (int) tempChar > 104 || tempInt < 49 || tempInt > 56;
        }
        else return true;
    }
}
