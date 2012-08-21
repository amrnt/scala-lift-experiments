package com.code.api.models

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl._
import com.code.api.models.Tables._

class Authentication(val id: Long,
           val user_id: Long,
           val full_name: String,
           val provider: String,
           val uid: String,
           val fetched_friends_count: Int,
           val friends_count: Int) extends KeyedEntity[Long]{

  lazy val user: ManyToOne[User] = userToAuthentications.right(this)

  def connected_providers = Map {
    provider -> uid
  }

  def worker_progress = Map() ++ (
    if(friends_count != 0)
      Seq(provider -> (fetched_friends_count * 100 / friends_count).toLong)
    else
      Seq(provider -> 0)
  )
}
