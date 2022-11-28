/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Integer {
    /**
     * Member Variables
     */
    private int team;
    private int x;
    private int y;

    /**
     * Default Constructor
     */
    public Integer() {
         team = 0;
         x = -1;
         y = -1;
    }

    /**
     * Constructor
     * @param team the team that is on that square (can be black[-2, -1], red[1, 2] or "null"[0]
     *             A 2 denotes that it is a king and a 1 denotes that it is a normal man, the sign
     *             indicates the team; either black or red
     * @param x the x co-ordinate on the board
     * @param y the y co-ordinate on the board
     */
    public Integer(int team, int x, int y){
        setTeam(team);
        setX(x);
        setY(y);
    }

    /**
     * Getter
     * @return the y co-ordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Setter
     * Sets the member variable y
     * @param y the y co-ordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter
     * @return the x co-ordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Setter
     * Sets the member variable x
     * @param x the x co-ordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter
     * @return What team is on that square
     */
    public int getTeam(){
        return team;
    }

    /**
     * Setter
     * Sets the member variable team
     * @param team What team is on that specific square
     */
    public void setTeam(int team){
        this.team = team;
    }
}
