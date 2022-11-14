/**
 * This is the class for our checkers pieces ("Men")
 **/

public class Man {
    // member variables
    private int team;

    // constructor
    public Man() {
         team = 0;
    }
    public Man(int team){
        setTeam(team);
    }

    public int getTeam(){
        return team;
    }
    public void setTeam(int team){
        this.team = team;
    }

}
