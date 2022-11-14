/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Man {
    // member variables
    private boolean black;

    // constructor
    public Man() {
         black = true;
    }
    public Man(boolean black){
        setBlack(black);
    }

    public boolean getBlack(){
        return black;
    }
    public void setBlack(boolean black){
        this.black = black;
    }

}
