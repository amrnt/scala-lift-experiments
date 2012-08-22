package com.code.api.models

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import net.liftweb.squerylrecord.RecordTypeMode._
import org.squeryl.annotations.Column
import org.squeryl.Query
import org.squeryl.annotations.Column
import DBSchema._

class Authentication private () extends Record[Authentication] with KeyedRecord[Long] {
  def meta = Authentication

  @Column(name="id")
  val idField = new LongField(this)

  val provider = new StringField(this, "")
  val uid = new LongField(this)
  val fetched_friends_count = new IntField(this)
  val friends_count = new IntField(this)
  val user_id = new LongField(this)

  def connected_providers = Map {
    provider.get.toString -> uid.get.toString
  }

  def worker_progress = Map() ++ (
    if(friends_count.get != 0)
      Seq(provider.get.toString -> (fetched_friends_count.get * 100 / friends_count.get).toLong)
    else
      Seq(provider.get.toString -> 0)
  )
}

object Authentication extends Authentication with MetaRecord[Authentication]
