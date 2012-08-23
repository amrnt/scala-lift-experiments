package com.amrtamimi.api

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.common.Full
import net.liftweb.json._
import scala.xml._
// import org.squeryl.PrimitiveTypeMode._
import net.liftweb.squerylrecord.RecordTypeMode._
import com.amrtamimi.api.models.DBSchema._

object auth_token extends RequestVar[String](S.param("auth_token").map(_ toString) openOr "")

object Main extends RestHelper {

  serve {

    case req@Req(List("api", "2", "users"), _, GetRequest) => () =>
      Full(toResponse(req.headers("accept"), req.path.suffix,
        (from(users)(
          select(_)
        )).map(m => m.mapIt)
      ))

    case req@Req(List("api", "2", "users", userId), _, GetRequest) => () =>
      Full(toResponse(req.headers("accept"), req.path.suffix,
        (from(users)(u =>
          where(u.id === userId.toLong)
          select (u)
        )).map(m => m.mapIt)
      ))

    case req@Req(List("api", "2", "me"), _, GetRequest) => () =>
      Full(toResponse(req.headers("accept"), req.path.suffix,
        (from(users)(u =>
          where(u.authentication_token === auth_token.toString)
          select (u)
        )).map(m => m.mapIt)
      ))

  }

  override implicit val formats = net.liftweb.json.DefaultFormats
  private def toResponse(accept: List[String], suffix: String, item: Any) = {
    lazy val jsonObject = Extraction.decompose(item)
    lazy val xmlObject = <users>{Xml.toXml(Extraction.decompose(Map("user" -> item)))}</users>

    suffix.toLowerCase match {
      case "xml"  => XmlResponse(xmlObject)
      case "json" => JsonResponse(jsonObject)
      case ""     => {
        if(accept.find(_.toLowerCase.contains("application/json")).isDefined ||
          accept.find(_.toLowerCase.contains("application/javascript")).isDefined ||
          accept.find(_.toLowerCase.contains("*/*")).isDefined)
        {
          JsonResponse(jsonObject)
        }else if (
          accept.find(_.toLowerCase.contains("text/xml")).isDefined ||
          accept.find(_.toLowerCase.contains("application/xml")).isDefined)
        {
          XmlResponse(xmlObject)
        } else{
          JsonResponse(jsonObject)
        }
      }
      case _      => NotFoundResponse("Bad URI")
    }
  }
}
