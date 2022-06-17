package tic

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import slinky.core.facade.ReactElement

@react class Board extends StatelessComponent {

  case class Props(squares: Seq[Option[Char]], onClick: Int => Unit)

  def render(): ReactElement = div(
    for (r <- 0 to 2)
      yield div(key := s"row_$r", className := "board-row")(
        for (c <- 0 to 2)
          yield renderSquare(r * 3 + c)
      )
  )

  private def renderSquare(squareIndex: Int): ReactElement =
    Square(props.squares(squareIndex), () => props.onClick(squareIndex))
      .withKey(s"square_$squareIndex")

}