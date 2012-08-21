package com.code.api.models

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl._
import com.code.api.models.Tables._

class User(val id: Long,
           val full_name: String,
           val time_zone: String,
           val last_sign_in_at: String,
           val current_sign_in_at: String,
           val image_square: String,
           val image_small: String,
           val image_normal: String,
           val image_large: String) extends KeyedEntity[Long] {

  lazy val authentications: OneToMany[Authentication] = userToAuthentications.left(this)

  def as_json_map = Map (
    "id" -> id.toLong,
    "full_name" -> full_name,
    "time_zone" -> time_zone,
    "last_sign_in" -> Map (
      "at" -> last_sign_in_at.toString,
      "is_first" -> (current_sign_in_at == last_sign_in_at)),
    "images" -> Map (
      "square" -> image_square,
      "small" -> image_small,
      "normal" -> image_normal,
      "large" -> image_large
    ),
    "status" -> authentications.map(_.worker_progress).head,
    "connected_providers" -> authentications.map(_.connected_providers).head
  )
}
