import java.util.ArrayList;

public class MoveUp extends Operator {
    public FivePuzzleState moveUp(FivePuzzleState s, int i) {
        s.tilePosition[i-3] = s.tilePosition[i];
        s.tilePosition[i] = 0;
        return s;
    }
}
