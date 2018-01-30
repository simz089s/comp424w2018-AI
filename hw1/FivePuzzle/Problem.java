import java.util.ArrayList;

public abstract class Problem {
    private State startState;
    abstract boolean isGoal(State crtState);
    abstract boolean isLegal(State s, Operator op);
    abstract ArrayList getLegalOps(State s);
    abstract State nextState(State crtState, Operator op);
    abstract float cost(State s, Operator op);

    public State getStartState() { return startState; }
}
