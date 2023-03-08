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

    def fly (direction: MoveDirection) = 
        direction match
            case North => rules += MoveNorth(State(currentState.toString), State(currentState.toString))
        
    

}