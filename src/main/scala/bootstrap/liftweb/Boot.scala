package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.util.Props

import org.squeryl._
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.PrimitiveTypeMode._
import java.sql.DriverManager

import com.code.api.models.Tables._

class Boot {
  def boot {
    Class.forName(Props.get("db.driver") openOr "")
    SessionFactory.concreteFactory = Some(() =>
      Session.create(
        DriverManager.getConnection(Props.get("db.url")  openOr "", Props.get("db.user")  openOr "", Props.get("db.password")  openOr ""),
        new PostgreSqlAdapter
      )
    )

    LiftRules.addToPackages("code")
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.statelessDispatch.append(com.code.api.Main)
  }
}
