package tic

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import slinky.core.facade.ReactElement

@react class Game extends Component {
  type Props = Unit

  case class State(history: Seq[HistoryEntry],
                   stepNumber: Int,
                   xIsNext: Boolean)

  def initialState: State = State(Seq(HistoryEntry()), 0, xIsNext = true)

  private def handleClick(squareIndex: Int) {
    val history = state.history.take(state.stepNumber + 1)
    val current = history.last
    val existingValue = calculateWinner(current) orElse current.squares(squareIndex)
    if (existingValue.isEmpty) {
      val newSquares = HistoryEntry(current.squares.updated(
        squareIndex,
        existingValue orElse nextPlayer
      ))
      setState(State(history :+ newSquares, history.length, !state.xIsNext))
    }
  }

  def render(): ReactElement = {
    val history = state.history
    val current = history(state.stepNumber)
    val moves = history.indices.map(move =>
      li(key := move.toString,
        button(onClick := { () => jumpTo(move) })(
          if (move > 0)
            s"Go to move # $move"
          else
            "Go to game start"
        )
      )
    )

    val status = calculateWinner(current)
      .map("Winner: " + _)
      .getOrElse(s"Next player ${nextPlayer.mkString}")

    div(className := "game")(
      h2("Slinky Scala React TicTacToe Tutorial"),
      div(className := "game-board")(
        Board(current.squares, i => handleClick(i))
      ),
      br(),
      div(className := "game-info")(
        div(className := "status")(status),
        ol(moves)
      )
    )
  }

  private def jumpTo(step: Int): Unit =
    setState(State(state.history, step, step % 2 == 0))

  private def calculateWinner(entry: HistoryEntry): Option[Char] = {
    val lines = List(
      (0, 1, 2),
      (3, 4, 5),
      (6, 7, 8),
      (0, 3, 6),
      (1, 4, 7),
      (2, 5, 8),
      (0, 4, 8),
      (2, 4, 6)
    )
    val sq = entry.squares
    lines.collectFirst {
      case (a, b, c)
        if sq(a).nonEmpty && sq(a) == sq(b) && sq(a) == sq(c) =>
        sq(a).get
    }
  }

  private def nextPlayer = {
    Some(if (state.xIsNext) 'X' else '0')
  }

}

case class HistoryEntry(squares: Seq[Option[Char]] = List.fill(9)(None))
