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

import Direction._
import scala.collection.mutable.ListBuffer

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class BoardHandler {

  var board = spawnRandom(spawnRandom(new Board))

  val rowHandler = new RowHandler

  private def spawnRandom(board: Board): Board = {
    board
  }

  def move(direction: Direction): Change = {

    val change = doMove(board, direction)

    if (change.changed) {
      return new Change(spawnRandom(board), change.score, change.changed)
    } else {
      change
    }

  }

  private def doMove(board: Board, direction: Direction): Change = {
    val newBoard = new Board

    val coordinates = CoordinatesGenerator.coordinates(direction)

    var score = 0
    var changed = false

    for (i <- 0 until Board.size) {
      val rowBuffer = new ListBuffer[Cell]

      for (j <- 0 until Board.size) {
        val coordinate = coordinates(i * Board.size + j)
        rowBuffer += board.get(coordinate._1, coordinate._2)
      }

      val rowChange = rowHandler.foldRow(rowBuffer.toList)
      val newRow = rowChange.row
      score += rowChange.score
      changed |= rowChange.changed

      for (j <- 0 until Board.size) {
        val coordinate = coordinates(i * Board.size + j)
        newBoard.set(coordinate._1, coordinate._2, newRow(j))
      }
    }

    new Change(newBoard, score, changed)
  }



}
