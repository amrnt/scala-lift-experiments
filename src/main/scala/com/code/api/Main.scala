package com.code.api
import models._

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json._

object auth_token extends RequestVar[String](S.param("auth_token").map(_.toString) openOr "")

object Main extends RestHelper {


  serve( "2" / "me" prefix {
    case Nil JsonGet _ => {
      Xml.toJson(
        User.findAll() map(_.toXml)
      ).map {
        case JField("id", JString(s)) => JField("id", JInt(s.toLong))
        case x => x
      } \\ "User" // This to remove the root elem
    }
  })

  serve( "2" / "users" prefix {
    case "count" :: Nil JsonGet _ => {
      JInt(User.count())
    }

    case Nil JsonGet _ => {
      Xml.toJson(
        User.findAll() map(_.toXml)
      ).map {
        case JField("id", JString(s)) => JField("id", JInt(s.toLong))
        case x => x
      } \\ "User"
    }

    case id :: _ JsonGet _ => {
      Xml.toJson(
        User.find(id) map(_.toXml) head
      )
    }
  })

}
