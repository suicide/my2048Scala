/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package my2048.game

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class Board {

  private val board = Array.fill(Board.size * Board.size){CellFactory.getCell(0)}

  def get(x: Int, y: Int): Cell = {
    board(x * Board.size + y)
  }

  def set(x: Int, y: Int, cell: Cell): Unit = {
    board(x * Board.size + y) = cell
  }

}

object Board {
  val size = 4
}
