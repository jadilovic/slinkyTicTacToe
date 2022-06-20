package tic

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.core.facade.Hooks._
import slinky.web.html._

@react object HooksComponent {
    case class Props(name: String)
    val component = FunctionalComponent[Props] { props => 
          val (state, updateState) = useState(0)
          div(
            h2(s"Number of clicks ${state}"),
            button("Click", onClick := (e => updateState(state + 1)) )
          )
        }
}