package models.manager.keyword

import com.mongodb.casbah.Imports._
import mongoContext._
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.Imports._

import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import se.radley.plugin.salat._

case class WordManage(
    id: ObjectId = new ObjectId,
    keyword: String
)

object WordManage extends MeifanNetModelCompanion[WordManage] {
  val dao = new MeifanNetDAO[WordManage](collection = loadCollection()) {}
}
