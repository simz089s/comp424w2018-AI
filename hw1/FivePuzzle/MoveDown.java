import java.util.ArrayList;

public class MoveDown extends Operator {
    public FivePuzzleState move(FivePuzzleState s, int i) {
        s.tilePosition[i+3] = s.tilePosition[i];
        s.tilePosition[i] = 0;
        return s;
    }

    public boolean isValid(int i) {
        if (i < 0 || i > 5) return false;
        return true;
    }
}
