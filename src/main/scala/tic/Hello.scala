package tic

import slinky.core.annotations.react
import slinky.core.StatelessComponent
import slinky.core.facade.ReactElement
import slinky.web.html.h1

@react class Hello extends StatelessComponent {
    case class Props(name: String)
    def render(): ReactElement = {
        h1(s"Hello ${props.name}")
    }
}