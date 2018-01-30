import java.util.ArrayList;

public class FivePuzzleState extends State {
    int[] tilePosition;

    public FivePuzzleState(int[] tilePosition) {
        this.tilePosition = tilePosition;
    }

    public void print() {
        System.out.println("_____________\n| %d | %d | %d |\n| %d | %d | %d |\n¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    // public int[] getTilePosition() { return tilePosition; }
}
