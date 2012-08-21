package com.code.api
package models

// import net.liftweb.mapper._

// class Authentication extends LongKeyedMapper[Authentication] {
//   def getSingleton = Authentication
//   def primaryKeyField = id

//   object id extends MappedLongIndex(this)
//   object provider extends MappedString(this, 255)
//   object uid extends MappedString(this, 255)
//   object fetched_friends_count extends MappedInt(this)
//   object friends_count extends MappedInt(this)

//   object user_id extends MappedLongForeignKey(this, User)

//   def connected_providers = Map {
//     provider.get.toString -> uid.get.toString
//   }

//   def worker_progress = Map() ++ (
//     if(friends_count.get != 0)
//       Seq(provider.get.toString -> (fetched_friends_count.get * 100 / friends_count.get).toLong)
//     else
//       Seq(provider.get.toString -> 0)
//   )

// }

// object Authentication extends Authentication with LongKeyedMetaMapper[Authentication] {
//   override def dbTableName = "authentications"

//   override lazy val fieldOrder = List(id)
// }
