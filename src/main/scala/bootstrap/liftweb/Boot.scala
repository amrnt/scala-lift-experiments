package bootstrap.liftweb

import net.liftweb.http.LiftRules
import java.sql.DriverManager
import ru.circumflex.orm._

class Boot {
  def boot {
    LiftRules.addToPackages("code")
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.statelessDispatch.append(com.code.api.Main)
  }
}

