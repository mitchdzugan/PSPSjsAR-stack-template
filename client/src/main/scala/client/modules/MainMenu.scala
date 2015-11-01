package client.modules

import japgolly.scalajs.react.extra.router2.RouterCtl
import client.Main._

import scalacss.ScalaCssReact._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.OnUnmount
import japgolly.scalajs.react.vdom.prefix_<^._
import rx._
import rx.ops._
import client.components.Bootstrap.CommonStyle
import client.components.Icon._
import client.components._
import spatutorial.client.services._

object MainMenu {
  // shorthand for styles
  @inline private def bss = GlobalStyles.bootstrapStyles

  case class Props(ctl: RouterCtl[Loc], currentLoc: Loc)

  case class MenuItem(idx: Int, label: (Props) => ReactNode, icon: Icon, location: Loc)

  class Backend(t: BackendScope[Props, _]) extends OnUnmount {
    def mounted(): Unit = {
    }
  }

  private val menuItems = Seq.empty[MenuItem]

  private val MainMenu = ReactComponentB[Props]("MainMenu")
    .stateless
    .backend(new Backend(_))
    .render((P, _, B) => {
    <.ul(bss.navbar)(
      // build a list of menu items
      for (item <- menuItems) yield {
        <.li(^.key := item.idx, (P.currentLoc == item.location) ?= (^.className := "active"),
          P.ctl.link(item.location)(item.icon, " ", item.label(P))
        )
      }
    )
  })
    .componentDidMount(_.backend.mounted())
    .build

  def apply(props: Props) = MainMenu(props)
}
