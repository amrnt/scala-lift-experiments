package com.amrtamimi.api.models

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field.{LongField, StringField}
import net.liftweb.squerylrecord.KeyedRecord
import net.liftweb.squerylrecord.RecordTypeMode._
import org.squeryl.Query
import org.squeryl.annotations.Column
import com.amrtamimi.api.models.DBSchema._

class User private () extends Record[User] with KeyedRecord[Long] {
  def meta = User

  @Column(name="id")
  val idField = new LongField(this)

  val full_name = new StringField(this, "")
  val time_zone = new StringField(this, "")
  val authentication_token = new StringField(this, "")
  val last_sign_in_at = new StringField(this, "")
  val current_sign_in_at = new StringField(this, "")
  val image_square = new StringField(this, "")
  val image_small = new StringField(this, "")
  val image_normal = new StringField(this, "")
  val image_large = new StringField(this, "")

  lazy val authentications = userToAuthentications.left(this)

  def mapIt = Map (
    "id" -> id.toLong,
    "full_name" -> full_name.toString,
    "time_zone" -> time_zone.toString,
    "last_sign_in" -> Map (
      "at" -> last_sign_in_at.toString,
      "is_first" -> (current_sign_in_at == last_sign_in_at)),
    "images" -> Map (
      "square" -> image_square.toString,
      "small" -> image_small.toString,
      "normal" -> image_normal.toString,
      "large" -> image_large.toString
    ),
    "status" -> authentications.map(_.worker_progress).head,
    "connected_providers" -> authentications.map(_.connected_providers).head
  )
}

object User extends User with MetaRecord[User]
