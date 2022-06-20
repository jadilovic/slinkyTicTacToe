package tic

import slinky.core.annotations.react
import slinky.web.html._
import slinky.core.FunctionalComponent
import slinky.core.facade.Hooks._

@react object NameComponent {
    case class Props(name: String)

       val component = FunctionalComponent[Props] {
        props => h1(s"Hello  ${props.name}")
    }
}