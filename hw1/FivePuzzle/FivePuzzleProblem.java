import java.util.ArrayList;

public class FivePuzzleProblem extends Problem {

    private FivePuzzleState init;
    private FivePuzzleState goal;
    private static final int size = 6;
    private static final Operator[] ops = { new MoveUp(), new MoveRight(),
                                            new MoveDown(), new MoveLeft() };

    public FivePuzzleProblem(FivePuzzleState init, FivePuzzleState goal) {
        this.init = init;
        this.goal = goal;
    }

    boolean isGoal(State crtState) {
        int[] crt = ((FivePuzzleState)crtState).tilePosition;
        int[] gl = goal.tilePosition;
        for (int i = 0; i < size; i++) {
            if (crt[i] != gl[i]) {
                return false;
            }
        }
        return true;
    }

    boolean isLegal(State s, Operator op) {
        int[] board = s.tilePosition;
        int i = 0;
        for (i; board[i] != 0; i++);
        if (op.isValid(i)) return true;
        return false;
    }

    ArrayList<Operator> getLegalOps(State s) {
        ArrayList<Operator> legals = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            if isLegal(s, ops[i]) {
                legals.add(ops[i]);
            }
        }
        return legals;
    }

    State nextState(State crtState, Operator op) {
        return null;
    }

    float cost(State s, Operator op) {
        return 1.0f;
    }

}
