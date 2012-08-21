package com.code.api

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json._

import org.squeryl.PrimitiveTypeMode._
import com.code.api.models.DBSchema._

object auth_token extends RequestVar[String](S.param("auth_token").map(_ toString) openOr "")

object Main extends RestHelper {

  serve( "2" / "me" prefix {
    case Nil JsonGet _ => {
      inTransaction {
        Extraction.decompose(
          from(users)(
            select(_)
          ).map(m => m.as_json_map)
        )
      }
    }
  })

  serve( "2" / "users" prefix {
    case "count" :: Nil JsonGet _ => {
      inTransaction {
        Extraction.decompose(
          Map("count" ->
            from(users)((u) =>
              compute(count())
            ).map(m => m.measures).head
          )
        )
      }
    }

    case Nil JsonGet _ => {
      inTransaction {
        Extraction.decompose(
          from(users)(
            select(_)
          ).map(m => m.as_json_map)
        )
      }
    }

    case id :: _ JsonGet _ => {
      inTransaction {
        Extraction.decompose(
          from(users)(u =>
            where(u.id === id.toLong)
            select (u)
          ).map(m => m.as_json_map)
        )
      }
    }
  })

}
