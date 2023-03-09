package piconot.internal
import picolib.maze.Maze
import picolib.semantics._
import picolib.display.TextDisplay

import scala.collection.mutable.ListBuffer

object MazeBot extends Picodrone("resources/maze.txt") {
    search(South)
    run
}