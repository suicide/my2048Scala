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
class RowChange(val row: Seq[Cell], val score: Int, val changed: Boolean) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[RowChange]

  override def equals(other: Any): Boolean = other match {
    case that: RowChange =>
      (that canEqual this) &&
        row == that.row &&
        score == that.score &&
        changed == that.changed
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(row, score, changed)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
