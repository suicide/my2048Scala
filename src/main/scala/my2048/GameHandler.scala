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

package my2048

import my2048.game.Direction._
import my2048.output.BoardRenderer
import my2048.game.BoardHandler
import java.util.Scanner

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
class GameHandler {

  def play(): Unit = {

    val renderer = new BoardRenderer
    val boardHandler = new BoardHandler
    val scanner = new Scanner(System.in)

    var score = 0

    renderer.render(boardHandler.board, score)

    while (true) {
      println("What direction now?")
      val key = scanner.next()

      var d = mapDirection(key)

      var (board, moveScore, changed) = boardHandler.move(d)

      if (changed) {
        score += moveScore

        renderer.render(board, score)
      }
    }

  }

  private def mapDirection(key: String): Direction = {

    key match {
      case "w" => Up
      case "a" => Left
      case "s" => Down
      case "d" => Right
      case _ => throw new InvalidInputException(key + " does not match a direction")
    }

  }

  private class InvalidInputException(msg: String) extends RuntimeException(msg)

}
