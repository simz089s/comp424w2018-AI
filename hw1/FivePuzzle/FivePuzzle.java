

public class FivePuzzle {
    public static void main(String[] args) {
        int[] tilePositionInit = { 1, 4, 2,
                                   5, 3, 0 };
        int[] tilePositionGoal = { 0, 1, 2,
                                   5, 4, 3 };
        FivePuzzleState init = new FivePuzzleState(tilePositionInit);
        FivePuzzleState goal = new FivePuzzleState(tilePositionGoal);
        FivePuzzleProblem fpp = new FivePuzzleProblem(init, goal);
    }
}
