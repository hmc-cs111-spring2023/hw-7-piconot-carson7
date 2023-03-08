package piconot.internal
import picolib.maze.Maze
import picolib.semantics._
import picolib.display.TextDisplay

import scala.collection.mutable.ListBuffer

object EmptyBot extends Picodrone("resources/empty.txt") {
    fly (North)
}
