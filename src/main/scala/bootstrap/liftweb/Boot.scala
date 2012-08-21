package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.util.Props
import net.liftweb.common.{Box, Empty, Full, Logger}
import net.liftweb.util.Props
import java.sql.{Connection, DriverManager}

class Boot {
  def boot {
    LiftRules.addToPackages("code")
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.statelessDispatch.append(com.code.api.Main)
  }
}

