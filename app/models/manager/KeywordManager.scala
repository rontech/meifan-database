package models.manager.keyword

import mongoContext._
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.Imports._


case class WordManage(
    id: ObjectId = new ObjectId,
    keyword: String
)

object WordManage extends MeifanNetModelCompanion[WordManage] {
  val dao = new MeifanNetDAO[WordManage](collection = loadCollection()) {}

  /** 重複データ検索メソッド */
  def findWord(checkWord: String): Option[WordManage] = {
    dao.findOne(MongoDBObject("keyword" -> checkWord))
  }
}
