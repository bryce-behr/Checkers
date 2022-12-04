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
        /**
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

        valid = false;
        while (valid == false) {
            System.out.println(checkers.currentBoard().toString());
            /*
            System.out.println("Player " + (checkers.getTurn() + 1) + ": please enter the column number of the piece you would like to move");
            input = myScan.next();
            while (!((Integer.valueOf(input) >= 0) && (Integer.valueOf(input) <= 7))) {
                System.out.println("Player " + (checkers.getTurn() + 1) + ": the previous value you entered was invalid, please enter a number between " +
                        "0 and 7 for the column number of the piece you would like to move");
                input = myScan.next();
            }
            iC = Integer.valueOf(input);

            System.out.println("Player " + (checkers.getTurn() + 1) + " please enter the row number of the piece you would like to move");
            input = myScan.next();
            while (!((Integer.valueOf(input) >= 0) && (Integer.valueOf(input) <= 7))) {
                System.out.println("Player " + (checkers.getTurn() + 1) + ": the previous value you entered was invalid, please enter a number between " +
                        "0 and 7 for the row number of the piece you would like to move");
                input = myScan.next();
            }
            iR = Integer.valueOf(input); */

            System.out.print("Player" + (checkers.getTurn() +1)  + " which piece would you like to move: ");
            input = myScan.next();
            while(!checkValid(input)) {
                System.out.print("Invalid Input: Enter piece coordinates in proper notation i.e. \"b4\": ");
                input = myScan.next();
            }
            iC = input.charAt(0) - 97;
            iR = input.charAt(1) - 48;

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
        while (valid == false) {
            System.out.println(checkers.currentBoard().toString());
            System.out.println("Player " + (checkers.getTurn() + 1) + " please enter the column number of the square you would like to move the piece to");
            if (myScan.hasNextLine()) {
                input = myScan.next();
            }
            while (!((Integer.valueOf(input) >= 0) && (Integer.valueOf(input) <= 7))) {
                System.out.println("Player " + (checkers.getTurn() + 1) + ": the previous value you entered was invalid, please enter a number between " +
                        "0 and 7 for the column number of the square you would like to move the piece to");
                input = myScan.next();
            }
            fC = Integer.valueOf(input);

            System.out.println("Player " + (checkers.getTurn() + 1) + " please enter the row number of the square you would like to move the piece to");
            input = myScan.next();
            while (!((Integer.valueOf(input) >= 0) && (Integer.valueOf(input) <= 7))) {
                System.out.println("Player " + (checkers.getTurn() + 1) + ": the previous value you entered was invalid, please enter a number between " +
                        "0 and 7 for the row number of the square you would like to move the piece to");
                input = myScan.next();
            }
            fR = Integer.valueOf(input);

            if (checkers.currentBoard().checkValid((checkers.currentBoard().getSquare(iR, iC)), (checkers.currentBoard().getSquare(fR, fC))) == true){
                valid = true;
            }
            else{
                System.out.println("You are moving to an invalid square");
            }
        }

        return (new int[]{iR, iC, fR, fC});
    }

    /**
     * Method
     * asks whether the player whose turn it is would like to go back and change their move
     * @return AN int (1 if they want to go back and 2 if they don't want to go back)
     */
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

        System.out.println("Would you like to be X's or O's ?");
        System.out.println("Pleas enter '0' to be X's and '1' to be O's");

        input = myScan.next();
        while (!((Integer.valueOf(input) >= 0) && (Integer.valueOf(input) <= 1))) {
            System.out.println("Please enter eiter '0' or '1'");
            input = myScan.next();
        }
        team = Integer.valueOf(input);
        if (team == 0){
            comp = 1;
        }
        else{
            comp = -1;
        }

        while (checkers.hasWon() == false){
            if (checkers.getTurn() != team){
                checkers.makeRandomMove(comp);
                //checkers.incTurn();
                System.out.println(checkers.currentBoard().toString());
                System.out.println("Your move");
            }
            else{
                int[] coordinates = askPlayers(checkers.getTurn());
                int iR = coordinates[0];
                int iC = coordinates[1];
                int fR = coordinates[2];
                int fC = coordinates[3];

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

        System.out.println("Player " + (checkers.getTurn() + 1) + " starts the game");

        while (checkers.hasWon() == false){
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
        while (flag == true) {
            int decision = askWhatGame();
            switch (decision) {
                case 1:
                    onePlayerGame();
                    break;
                case 2:
                    twoPlayerGame();
                    break;
            }
            System.out.println("Game over...");
            System.out.println("Starting new game");
        }
        System.out.println("PROGRAM CLOSED");
    }

    private static boolean checkValid(String str) {
        /*
        Scanner sc = new Scanner(str);
        sc.useDelimiter(",");
        String tempStr;
        char tempChar;

        if(sc.hasNext()){
            tempStr = sc.next();
            if(tempStr.length() == 1) {
                tempChar = tempStr.charAt(0);
                if((int)tempChar < 97 || (int)tempChar > 104) return false;
            }
            else return false;
        }
        else return false;

        if(sc.hasNext()){
            tempStr = sc.next();
            if(tempStr.length() == 1) {
                tempChar = tempStr.charAt(0);
                if((int)tempChar < 48 || (int)tempChar > 55) return false;
            }
            else return false;
        }
        else
            return false;

        return !sc.hasNext(); */

        char tempChar;
        int tempInt;

        if(str.length() == 2) {
            tempChar = str.charAt(0);
            tempInt = (int)str.charAt(1);
            return (int) tempChar >= 97 && (int) tempChar <= 104 && tempInt >= 48 && tempInt <= 55;
        }
        else return false;
    }
}
