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

import org.specs2.mutable.Specification

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class RowHandlerTest extends Specification {

  implicit def asCell(value: Int): Cell = {
    CellFactory.getCell(value)
  }

  val rowHandler = new RowHandler

  "The RowHandler object" should {
    "do nothing on empty lines" in {
      val row: Seq[Cell] = List(0,0,0,0)
      val targetRow: Seq[Cell] = List(0,0,0,0)
      val result = new RowChange(targetRow, 0, false)
      rowHandler.foldRow(row) must be equalTo result
    }
    "do nothing on 2,0,0,0" in {
      val row: Seq[Cell] = List(2,0,0,0)
      val targetRow: Seq[Cell] = List(2,0,0,0)
      val result = new RowChange(targetRow, 0, false)
      rowHandler.foldRow(row) must be equalTo result
    }
    "just move on 0,2,0,0" in {
      val row: Seq[Cell] = List(0,2,0,0)
      val targetRow: Seq[Cell] = List(2,0,0,0)
      val result = new RowChange(targetRow, 0, true)
      rowHandler.foldRow(row) must be equalTo result
    }
    "just move on 0,2,0,4" in {
      val row: Seq[Cell] = List(0,2,0,4)
      val targetRow: Seq[Cell] = List(2,4,0,0)
      val result = new RowChange(targetRow, 0, true)
      rowHandler.foldRow(row) must be equalTo result
    }
    "merge on 2,2,0,0" in {
      val row: Seq[Cell] = List(2,2,0,0)
      val targetRow: Seq[Cell] = List(4,0,0,0)
      val result = new RowChange(targetRow, 4, true)
      rowHandler.foldRow(row) must be equalTo result
    }
    "move and merge on 0,0,2,2" in {
      val row: Seq[Cell] = List(0,0,2,2)
      val targetRow: Seq[Cell] = List(4,0,0,0)
      val result = new RowChange(targetRow, 4, true)
      rowHandler.foldRow(row) must be equalTo result
    }
    "move and merge on 0,2,0,2" in {
      val row: Seq[Cell] = List(0,2,0,2)
      val targetRow: Seq[Cell] = List(4,0,0,0)
      val result = new RowChange(targetRow, 4, true)
      rowHandler.foldRow(row) must be equalTo result
    }
    "move and merge on 0,2,2,2" in {
      val row: Seq[Cell] = List(0,2,2,2)
      val targetRow: Seq[Cell] = List(4,2,0,0)
      val result = new RowChange(targetRow, 4, true)
      rowHandler.foldRow(row) must be equalTo result
    }
    "move and merge on 2,2,2,2" in {
      val row: Seq[Cell] = List(2,2,2,2)
      val targetRow: Seq[Cell] = List(4,4,0,0)
      val result = new RowChange(targetRow, 8, true)
      rowHandler.foldRow(row) must be equalTo result
    }
  }
}
