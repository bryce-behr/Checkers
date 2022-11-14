/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Square {
    // member variables
    private boolean black;

    // constructor
    public Square() {
         black = true;
    }
    public Square(boolean black){
        setBlack(black);
    }

    public boolean getBlack(){
        return black;
    }
    public void setBlack(boolean black){
        this.black = black;
    }

}
