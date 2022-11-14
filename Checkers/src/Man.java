/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Man {
    // member variables
    private int team;
    private int x;
    private int y;

    //Default Constructor
    public Man() {
         team = 0;
         x = 0;
         y = 0;
    }

    /**
     * Constructor
     * @param team the team that is on that square (can be black[1], red[2] or "null"[0]
     * @param x the x co-ordinate on the board
     * @param y the y co-ordinate on the board
     */
    public Man(int team, int x, int y){
        setTeam(team);
        setX(x);
        setY(y);
    }

    /**
     *
     * @return the y co-ordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the member variable y
     * @param y the y co-ordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return the x co-ordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the member variable x
     * @param x the x co-ordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return What team is on that square
     */
    public int getTeam(){
        return team;
    }

    /**
     * Sets the member variable team
     * @param team What team is on that specific square
     */
    public void setTeam(int team){
        this.team = team;
    }
}
