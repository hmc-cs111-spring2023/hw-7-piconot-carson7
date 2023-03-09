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

    def MoveNorth (start: Int, end: Int) = new Rule(State(start.toString), Surroundings(Open, Anything, Anything, Anything), North, State(end.toString))
    def MoveEast (start: Int, end: Int) = new Rule(State(start.toString), Surroundings(Anything, Open, Anything, Anything), East, State(end.toString))
    def MoveSouth (start: Int, end: Int) = new Rule(State(start.toString), Surroundings(Anything, Anything, Anything, Open), South, State(end.toString))
    def MoveWest (start: Int, end: Int) = new Rule(State(start.toString), Surroundings(Anything, Anything, Open, Anything), West, State(end.toString))

    def fly (direction: MoveDirection) = 
        direction match
            case North => rules += MoveNorth(currentState, currentState)
                rules += Rule(State(currentState.toString), Surroundings(Blocked, Anything, Anything, Anything), StayHere, State((currentState+1).toString))
            case East => rules += MoveEast(currentState, currentState)
                rules += Rule(State(currentState.toString), Surroundings(Anything, Blocked, Anything, Anything), StayHere, State((currentState+1).toString))
            case South => rules += MoveSouth(currentState, currentState)
                rules += Rule(State(currentState.toString), Surroundings(Anything, Anything, Anything, Blocked), StayHere, State((currentState+1).toString))
            case West => rules += MoveWest(currentState, currentState)
                rules += Rule(State(currentState.toString), Surroundings(Anything, Anything, Blocked, Anything), StayHere, State((currentState+1).toString))
            currentState += 1
    
    def search (direction: MoveDirection) =
        direction match
            case North => fly(West)
                rules += MoveNorth(currentState, currentState+1)
                currentState += 1
                fly(East)
                rules += MoveNorth(currentState, currentState-3)
            case East => fly(North)
                rules += MoveEast(currentState, currentState+1)
                currentState += 1
                fly(South)
                rules += MoveEast(currentState, currentState-3)
            case South => fly(East)
                rules += MoveSouth(currentState, currentState+1)
                currentState += 1
                fly(West)
                rules += MoveSouth(currentState, currentState-3)
            case West => fly(South)
                rules += MoveWest(currentState, currentState+1)
                currentState += 1
                fly(North)
                rules += MoveWest(currentState, currentState-3)
        
    
        
    

}