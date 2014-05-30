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
object CoordinatesGenerator {

  def coordinates(direction: Direction): Seq[Tuple2[Int, Int]] = {

    val coordinates = new ListBuffer[Tuple2[Int, Int]]

    val addCoordinate = (x: Int, y: Int) => coordinates += new Tuple2[Int, Int](x, y)

    direction match {
      case Left =>
        for (x <- 0 until Board.size; y <- 0 until Board.size) {
          addCoordinate(x, y)
        }
      case Right => {
        for (x <- 0 until Board.size; y <- Board.size - 1 to 0 by -1) {
          addCoordinate(x, y)
        }
      }
      case Up => {
        for (y <- 0 until Board.size; x <- 0 until Board.size) {
          addCoordinate(x, y)
        }
      }
      case Down => {
        for (y <- 0 until Board.size; x <- Board.size - 1 to 0 by -1) {
          addCoordinate(x, y)
        }
      }
    }

    coordinates.toList
  }

}
