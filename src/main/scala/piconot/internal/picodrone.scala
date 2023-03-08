package piconot.internal

import picolib.maze.Maze
import picolib.semantics._
import picolib.display.TextDisplay

import scala.collection.mutable.ListBuffer

class Picodrone (val mazeFilename: String) extends App {

    // the list of rules, which is built up as the Picobor program executes
    private val rules = ListBuffer.empty[Rule]

    def fly (direction: MoveDirection) = println("flying")
    

}