package tic

import slinky.core.Component
import slinky.core.facade.ReactElement
import slinky.web.html._
import slinky.core.annotations.react
import slinky.core.StatelessComponent

@react class Square extends StatelessComponent {
case class Props(value: Option[Char], onClick: () => Unit)

  def render(): ReactElement =
    button(
      className := "square",
      onClick := (_ => props.onClick())
    )(this.props.value.mkString)
}