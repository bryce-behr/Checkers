/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Square {
    /**
     * Member Variables
     */
    private int team;
    private int c;
    private int r;

    /**
     * Default Constructor
     */
    public Square() {
         team = 0;
         c = -1;
         r = -1;
    }

    /**
     * Constructor
     * @param team the team that is on that square (can be black[-2, -1], red[1, 2] or "null"[0]
     *             A 2 denotes that it is a king and a 1 denotes that it is a normal man, the sign
     *             indicates the team; either black or red
     * @param c the x co-ordinate on the board
     * @param r the y co-ordinate on the board
     */
    public Square(int team, int r, int c){
        setTeam(team);
        setC(c);
        setR(r);
    }

    /**
     * Getter
     * @return the y co-ordinate
     */
    public int getR() {
        return r;
    }

    /**
     * Setter
     * Sets the member variable y
     * @param r the y co-ordinate
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Getter
     * @return the x co-ordinate
     */
    public int getC() {
        return c;
    }

    /**
     * Setter
     * Sets the member variable x
     * @param c the x co-ordinate
     */
    public void setC(int c) {
        this.c = c;
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

    @Override
    public String toString(){
       return ("(Row[" + r + "] Column[" + c + "] Team[" + team + "])");
    }
}
