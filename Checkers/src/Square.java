/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Square {
    // member variables
    private int team;

    // constructor
    public Square() {
         black = true;
    }
    public Square(boolean black){
        setBlack(black);

    }

    public int getTeam(){
        return team;
    }
    public void setTeam(int team){
        this.team = team;
    }

}
