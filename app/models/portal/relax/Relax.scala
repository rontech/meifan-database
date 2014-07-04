/**
 * RONTECH CONFIDENTIAL
 * __________________
 *
 *  [2014] - SuZhou Rontech Co.,Ltd.(http://www.sz-rontech.com)
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of SuZhou Rontech Co.,Ltd. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to SuZhou Rontech Co.,Ltd.
 * and its suppliers and may be covered by China and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from SuZhou Rontech Co.,Ltd..
 */
package models.portal.relax

import models.portal.common.OnUsePicture
import java.util.Date
import com.meifannet.framework.db.{MeifanNetDAO, MeifanNetModelCompanion}
import mongoContext._
import com.mongodb.casbah.Imports._


case class Relax(
                 id: ObjectId = new ObjectId,
                 salonId: ObjectId,
                 styleName: String,
                 serviceType: List[String],
                 stylePic: List[OnUsePicture],
                 description: String,
                 createDate: Date,
                 isValid: Boolean)

object Relax extends MeifanNetModelCompanion[Relax] {

  val dao = new MeifanNetDAO[Relax](collection = loadCollection()) {}

  def findAllRelaxsBySalon(salonId: ObjectId) : List[Relax] = {
    dao.find(DBObject("salonId" -> salonId)).toList
  }

  def delete(id: ObjectId) ={
    val relax = findOneById(id).get
    dao.update(MongoDBObject("_id" -> relax.id), MongoDBObject("$set" -> MongoDBObject("isValid" -> false)))
  }

  def checkRelaxIsExist(relaxNm: String, salonId: ObjectId): Boolean =
    dao.find(MongoDBObject("styleName" -> relaxNm, "salonId" -> salonId)).hasNext

}
