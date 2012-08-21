package bootstrap.liftweb

import net.liftweb.common.Loggable
import net.liftweb.util.Props
import net.liftweb.http.LiftRules
import net.liftweb.squerylrecord.SquerylRecord
import org.squeryl.Session
import org.squeryl.adapters.PostgreSqlAdapter

import java.sql.DriverManager

class Boot extends Loggable {
  def boot {
    LiftRules.addToPackages("code")

    Class.forName(Props.get("db.driver") openOr "")
    SquerylRecord.initWithSquerylSession { 
      val session = Session.create(
        DriverManager.getConnection(
          Props.get("db.url")  openOr "",
          Props.get("db.user")  openOr "",
          Props.get("db.password")  openOr ""
        ),
        new PostgreSqlAdapter
      )
      session.setLogger(statement => println(statement))
      session
    }
    
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.statelessDispatch.append(com.code.api.Main)
  }

}
