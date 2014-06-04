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

import scala.collection.mutable.ListBuffer

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class RowHandler {

  def foldRow(row: Seq[Cell]): (Seq[Cell], Int, Boolean) = {

    var newRow: Seq[Cell] = Vector()

    var previousCell: Cell = null
    var score = 0

    for (cell <- row if cell.value != 0) {

      if (cell.equals(previousCell)) {
        // merge
        val newCell = CellFactory.getCell(cell.value * 2)
        newRow = newRow :+ newCell
        score += newCell.value

        previousCell = null
      } else {
        if (previousCell != null) {
          newRow = newRow :+ previousCell
        }
        previousCell = cell
      }

    }

    if (previousCell != null) {
      newRow = newRow :+ previousCell
    }

    // fill up with 0's
    for (i <- 0 until Board.size - newRow.size) {
      newRow = newRow :+ CellFactory.getCell(0)
    }


    (newRow, score, !row.equals(newRow))
  }

}
