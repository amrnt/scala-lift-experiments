package com.code.api.models

import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._

object Tables extends Schema {
  val users = table[User]("users")
  val authentications = table[Authentication]("authentications")

  val userToAuthentications = oneToManyRelation(users, authentications).via((u,a) => u.id === a.user_id)

  // if we delete a user, we want all authentications to be deleted
  userToAuthentications.foreignKeyDeclaration.constrainReference(onDelete cascade)
}
