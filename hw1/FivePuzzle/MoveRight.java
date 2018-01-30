import java.util.ArrayList;

public class MoveRight extends Operator {
    public FivePuzzleState moveRight(FivePuzzleState s, int i) {
        s.tilePosition[i+1] = s.tilePosition[i];
        s.tilePosition[i] = 0;
        return s;
    }
}
