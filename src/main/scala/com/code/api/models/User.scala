package com.code.api
package models

import net.liftweb.mapper._

class User extends LongKeyedMapper[User] with OneToMany[Long, User] {
  def getSingleton = User
  def primaryKeyField = id

  object id extends MappedLongIndex(this)
  object full_name extends MappedString(this, 255)
  object time_zone extends MappedString(this, 255)
  object last_sign_in_at extends MappedDateTime(this)
  object current_sign_in_at extends MappedDateTime(this)
  object image_square extends MappedString(this, 255)
  object image_small extends MappedString(this, 255)
  object image_normal extends MappedString(this, 255)
  object image_large extends MappedString(this, 255)
  
  object authentications extends MappedOneToMany(Authentication, Authentication.user_id, OrderBy(Authentication.id, Ascending))

  def as_json_map = Map (
    "id" -> id.get.toLong,
    "full_name" -> full_name.get,
    "time_zone" -> time_zone.get,
    "last_sign_in" -> Map (
      "at" -> last_sign_in_at.get.toString,
      "is_first" -> (current_sign_in_at.get == last_sign_in_at.get)),
    "images" -> Map (
      "square" -> image_square.get,
      "small" -> image_small.get,
      "normal" -> image_normal.get,
      "large" -> image_large.get
    ),
    "status" -> authentications.map(_.worker_progress).head,
    "connected_providers" -> authentications.map(_.connected_providers).head
  )

}

object User extends User with LongKeyedMetaMapper[User] {
  override def dbTableName = "users"
}
