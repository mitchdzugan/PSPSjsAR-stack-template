package client.modules

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router2.RouterCtl
import japgolly.scalajs.react.vdom.prefix_<^._
import client.Main.Loc
import client.components.Bootstrap._

object Home {
  private val MainMenu = ReactComponentB[RouterCtl[Loc]]("MainMenu")
    .render( c => {
      Panel(Panel.Props("HOME PAGE"),
        <.div(
          <.span("This is the default home page for a single page app built using Scala, Play, Slick, Postgresql, Scalajs, Autowire and React")
        ))
    }).build

  def apply(c : RouterCtl[Loc]) = MainMenu(c)
}
