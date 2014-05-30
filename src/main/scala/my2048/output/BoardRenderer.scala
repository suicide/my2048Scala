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

package my2048.output

import my2048.game.Board

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class BoardRenderer {

  private val separator = "-------------------------------------"

  def render(board: Board, score: Int): Unit = {

    println(separator)

    for (x <- 0 until Board.size) {
      val row = Array.fill[Integer](Board.size) {0}
      for (y <- 0 until Board.size) {
        row(y) = board.get(x, y).value
      }

      // varargs hint
      printf("| %6d | %6d | %6d | %6d |\n", row : _*)
      println(separator)
    }

    println("Score: " + score)

  }

}
