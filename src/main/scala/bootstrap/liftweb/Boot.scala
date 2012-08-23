package bootstrap.liftweb

import net.liftweb.common.Loggable
import net.liftweb.util.{Props, LoanWrapper}
import net.liftweb.http.{LiftRules, S}
import net.liftweb.squerylrecord.SquerylRecord
import net.liftweb.squerylrecord.RecordTypeMode._
import org.squeryl.Session
import org.squeryl.adapters.PostgreSqlAdapter
import java.sql.DriverManager
import net.liftweb.http._

class Boot extends Loggable {
  def boot {
    LiftRules.addToPackages("amrtamimi")

    LiftRules.statelessRewrite.prepend( {
      case RewriteRequest(ParsePath(path, _, _, true), _, _) if path.last == "index" => RewriteResponse(path.init)
    })

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

    S.addAround(new LoanWrapper{
        override def apply[T](f: => T): T = {
          inTransaction{
              f
          }
        }
    })

    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.statelessDispatch.append(com.amrtamimi.api.Main)
  }

}
