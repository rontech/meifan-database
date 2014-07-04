package models.manager.stylist

import com.mongodb.casbah.Imports._
import mongoContext._
import scala.concurrent.{ExecutionContext, Future}
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.Imports._
import com.mongodb.casbah.commons.Imports.{DBObject => commonsDBObject}

/**
 * Created by CCC on 14/06/18.
 */
class Stylist(
               //需要字段：技师ID 技师账号 行业 昵称 联系电话 邮箱 是否签约店铺 状态 操作
               id: ObjectId = new ObjectId,
               stylistId: ObjectId,
               userId: String,
               nickName: String,
               email: String,
               tel: Option[String],
               industry: Option[String],
               isVerified: Boolean, //技师申请状态
               isValid: Boolean //技师是否有效
               )

object Stylist extends MeifanNetModelCompanion[Stylist] {
  val dao = new MeifanNetDAO[Stylist](collection = loadCollection()) {}
}

case class MeifanStylistSearch(userId: Option[String],
                               nickName: Option[String],
                               industry: Option[String],
                               isValid: Option[Boolean]
                                )

case class StylistApply(stylist: models.portal.stylist.Stylist)

object StylistApply {
  /*    find all stylist                                        */
  def findAllAPStylists(): List[models.portal.stylist.Stylist] = {

    var stylistApplies: List[models.portal.stylist.Stylist] = Nil
    stylistApplies = models.portal.stylist.Stylist.findAll.toList
    stylistApplies
  }

  /*    find stylist according condition                                        */
  def findStylistByCondition(stylistSearch: MeifanStylistSearch): List[models.portal.stylist.Stylist] = {
    var srchConds: List[commonsDBObject] = Nil
    if (stylistSearch.userId.nonEmpty) {
      srchConds :::= List(commonsDBObject("stylistId" -> models.portal.user.User.findOneByUserId(stylistSearch.userId.get).get.id))
    }
    if (stylistSearch.nickName.nonEmpty) {
      srchConds :::= List(commonsDBObject("stylistId" -> models.portal.user.User.findOneByNickNm(stylistSearch.nickName.get).get.id))
    }
    if (stylistSearch.industry.nonEmpty) {
      if (stylistSearch.industry.get.equals("Hairdressing")) {

      } else {
        srchConds :::= List("industry" $in List(stylistSearch.industry.get))
      }
    }
    if (stylistSearch.isValid.nonEmpty) {
      if (stylistSearch.isValid.get) {
        srchConds :::= List(commonsDBObject("isValid" -> true))
      }
      else {
        srchConds :::= List(commonsDBObject("isValid" -> false))
      }

    }
    var stylist: List[models.portal.stylist.Stylist] = Nil
    if (!srchConds.length.equals(0)) {
      stylist = models.portal.stylist.Stylist.find($and(srchConds)).toList
    }

    stylist


  }

  def activeStylist(stylist: models.portal.stylist.Stylist) = {
    models.portal.stylist.Stylist.save(stylist.copy(stylistId = stylist.stylistId, isValid = true))
  }

  def frozenStylist(stylist: models.portal.stylist.Stylist) = {
    models.portal.stylist.Stylist.save(stylist.copy(stylistId = stylist.stylistId, isValid = false))
  }
}