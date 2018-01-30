import scala.collection.immutable.Vector

abstract class Operator() {}

abstract class State() {
    def print() : Unit
}

abstract class Problem() {
    var _startState:State
    def isGoal(crtState: State) : Boolean
    def isLegal(s: State, op: Operator) : Boolean
    def getLegalOps(s: State) : Vector[Operator]
    def nextState(crtState: State, op: Operator) : State
    def cost(s: State, op: Operator) : Float

    def startState = _startState
}


