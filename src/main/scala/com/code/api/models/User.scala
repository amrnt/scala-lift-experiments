package com.code.api
package models

import net.liftweb.mapper._

class User extends LongKeyedMapper[User] {
  def getSingleton = User
  
  def primaryKeyField = id
  object id extends MappedLongIndex(this)
  object full_name extends MappedString(this, 255)
  object email extends MappedString(this, 255)
}

object User extends User with LongKeyedMetaMapper[User] {
  override def dbTableName = "users"
}
