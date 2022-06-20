package tic

import slinky.core.annotations.react
import slinky.core.Component
import slinky.core.facade.ReactElement
import slinky.web.html._

@react class MyButton extends Component {
    type Props = Unit
    case class State(buttonPresses: Int)

    def initialState: State = State(0)

    def render(): ReactElement = div(
            button("Click me", onClick := (e => {
                setState(state.copy(buttonPresses = state.buttonPresses + 1))
            })),
            h1(s"Clicked button ${state.buttonPresses} times!")
    )
        
    
}
