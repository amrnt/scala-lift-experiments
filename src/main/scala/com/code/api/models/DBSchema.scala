package com.code.api.models

import org.squeryl.Schema
import net.liftweb.squerylrecord.RecordTypeMode._

object DBSchema extends Schema {
  val users = table[User]("users")
  on(users)(u => declare(
    u.id is(primaryKey,autoIncremented)
  ))
  
  val authentications = table[Authentication]("authentications")
  on(authentications)(a => declare(
    a.id is(primaryKey,autoIncremented)
  ))

  val userToAuthentications =
      oneToManyRelation(users, authentications).via((u, a) => u.id === a.user_id)
}