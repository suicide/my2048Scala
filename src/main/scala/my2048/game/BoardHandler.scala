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
import scala.util.Random

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class BoardHandler {

  val r = new Random()

  var board = spawnRandom(spawnRandom(new Board))

  val rowHandler = new RowHandler

  private def spawnRandom(board: Board): Board = {

    val emptyFields = for {
      x <- 0 until Board.size
      y <- 0 until Board.size
      if board.get(x,y).value == 0
    } yield (x, y)

    val index = r.nextInt(emptyFields.size)
    val value = (r.nextInt(2) + 1) * 2

    val (x, y) = emptyFields(index)

    board.set(x, y, CellFactory.getCell(value))

    board
  }

  def move(direction: Direction): (Board, Int, Boolean) = {

    val (newBoard, score, changed) = doMove(board, direction)

    if (changed) {
      board = spawnRandom(newBoard)
      return (board, score, changed)
    } else {
      (newBoard, score, changed)
    }

  }

  private def doMove(board: Board, direction: Direction): (Board, Int, Boolean) = {
    val newBoard = new Board

    val coordinates = CoordinatesGenerator.coordinates(direction)

    var score = 0
    var changed = false

    for (i <- 0 until Board.size) {

      val row = (0 until Board.size).map(j => {
        val (x, y) = coordinates(i * Board.size + j)
        board.get(x, y)
      })

      val (newRow, rowScore, rowChanged) = rowHandler.foldRow(row)
      score += rowScore
      changed |= rowChanged

      for (j <- 0 until Board.size) {
        val (x, y) = coordinates(i * Board.size + j)
        newBoard.set(x, y, newRow(j))
      }
    }

    (newBoard, score, changed)
  }


}
