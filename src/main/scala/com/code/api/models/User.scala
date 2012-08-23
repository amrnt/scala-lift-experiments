package com.code.api
package models

import ru.circumflex.orm._

class User extends Record[Long, User] {
  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val full_name = "full_name".VARCHAR(255)
  val time_zone = "time_zone".VARCHAR(255)
  val last_sign_in_at = "last_sign_in_at".TIMESTAMP
  val current_sign_in_at = "current_sign_in_at".TIMESTAMP
  val image_square = "image_square".VARCHAR(255)
  val image_small = "image_small".VARCHAR(255)
  val image_normal = "image_normal".VARCHAR(255)
  val image_large = "image_large".VARCHAR(255)

  def PRIMARY_KEY = id
  def relation = User
}

object User extends User with Table[Long, User] {
  override def qualifiedName = "users"
}
