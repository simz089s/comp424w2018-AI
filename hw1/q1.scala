import scala.collection.immutable.Vector

class FivePuzzleOperator extends Operator {
    def moveUp(s: State, i: Int) : State = {}
}

class FivePuzzleState extends State {
    val tilePosition: Array[Int] = { 1, 4, 2,
                                     5, 3, 0 }
    def print() : Unit = {
        println("_____________\n| %d | %d | %d |\n| %d | %d | %d |\n¯¯¯¯¯¯¯¯¯¯¯¯¯")
    }
}

class FivePuzzleProblem extends Problem {
    def isLegal(s: FivePuzzleState, op: FivePuzzleOperator) : Boolean = {}
}
