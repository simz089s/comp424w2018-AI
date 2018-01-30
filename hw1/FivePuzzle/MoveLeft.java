import java.util.ArrayList;

public class MoveLeft extends Operator {
    public FivePuzzleState moveLeft(FivePuzzleState s, int i) {
        s.tilePosition[i-1] = s.tilePosition[i];
        s.tilePosition[i] = 0;
        return s;
    }
}
