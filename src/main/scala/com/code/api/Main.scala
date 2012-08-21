package com.code.api
import models._

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json._

object auth_token extends RequestVar[String](S.param("auth_token").map(_ toString) openOr "")

object Main extends RestHelper {

  // serve( "2" / "me" prefix {
  //   case Nil JsonGet _ => {
  //     Extraction.decompose(
  //       (User findAll) map(_ as_json_map)
  //     )
  //   }
  // })

  // serve( "2" / "users" prefix {
  //   case "count" :: Nil JsonGet _ => {
  //     Extraction.decompose(Map("count" -> (User count)))
  //   }

  //   case Nil JsonGet _ => {
  //     Extraction.decompose(
  //       (User findAll) map(_ as_json_map)
  //     )
  //   }

  //   case id :: _ JsonGet _ => {
  //     Extraction.decompose(
  //       (User find(id)) map(_ as_json_map) head
  //     )
  //   }
  // })

}
