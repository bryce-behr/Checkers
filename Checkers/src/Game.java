import java.util.Stack;

public class Game {
    //Member Variables
    private Stack<Board> boards;

    //Default Constructor
    public Game(){
        boards = new Stack<>();
    }

    public Stack<Board> getBoards() {
        return boards;
    }
    public void setBoards(Stack<Board> boards) {
        this.boards = boards;
    }

    public Stack<Board> back(){
        return null;
    }

    public Board currentBoard(){
        return null;
    }
}
