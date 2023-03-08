package piconot.internal

import picolib.maze.Maze
import picolib.semantics._
import picolib.display.TextDisplay

import scala.collection.mutable.ListBuffer

class Picodrone (val mazeFilename: String) extends App {

    def run = {
        val maze = Maze(mazeFilename)
        object bot extends Picobot(maze, rules.toList) with TextDisplay
        bot.run()
    }

    private val rules = ListBuffer.empty[Rule]

    private var currentState = 0

    def MoveNorth (start: State, end: State) = new Rule(start, Surroundings(Open, Anything, Anything, Anything), North, end)
    def MoveEast (start: State, end: State) = new Rule(start, Surroundings(Anything, Open, Anything, Anything), East, end)
    def MoveSouth (start: State, end: State) = new Rule(start, Surroundings(Anything, Anything, Anything, Open), South, end)
    def MoveWest (start: State, end: State) = new Rule(start, Surroundings(Anything, Anything, Open, Anything), West, end)

    def fly (direction: MoveDirection) = 
        direction match
            case North => rules += MoveNorth(State(currentState.toString), State(currentState.toString))
                rules += Rule(State(currentState.toString), Surroundings(Blocked, Anything, Anything, Anything), StayHere, State(currentState+1.toString))
            case East => rules += MoveEast(State(currentState.toString), State(currentState.toString))
            case South => rules += MoveSouth(State(currentState.toString), State(currentState.toString))
            case West => rules += MoveWest(State(currentState.toString), State(currentState.toString))
        currentState += 1
        
    

}