package models.manager.stylist

import com.mongodb.casbah.Imports._
import mongoContext._
import scala.concurrent.{ExecutionContext, Future}
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.Imports._
import com.mongodb.casbah.commons.Imports.{DBObject => commonsDBObject}
import models.portal.user.User
import models.portal.stylist.Stylist

/**
 * Created by CCC on 14/06/18.
 */
class StylistManager(
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

case class MeifanStylistSearch(userId: Option[String],
                               nickName: Option[String],
                               industry: Option[String],
                               isValid: Option[Boolean]
                                )

object StylistManager {
  /*    find all stylist                                        */
  def findAllAPStylists(): List[Stylist] = {

    var stylistApplies: List[Stylist] = Nil
    stylistApplies = Stylist.findAll.toList
    stylistApplies
  }

  /*    find stylist according condition                                        */
  def findStylistByCondition(stylistSearch: MeifanStylistSearch): List[Stylist] = {
    var srchConds: List[commonsDBObject] = Nil
    if (stylistSearch.userId.nonEmpty) {
      User.findOneByUserId(stylistSearch.userId.get) match {
        case Some(user) =>{
          srchConds :::= List(commonsDBObject("stylistId" -> user.id))
        }
        case None =>
          srchConds :::= List(commonsDBObject("stylistId" -> None))
      }
    }
    if (stylistSearch.nickName.nonEmpty) {
      User.findOneByNickNm(stylistSearch.nickName.get) match {
        case Some(user) => {
          srchConds :::= List(commonsDBObject("stylistId" -> user.id))
        }
        case None =>
          srchConds :::= List(commonsDBObject("stylistId" -> None))
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

    val stylist :List[Stylist] = if(srchConds.nonEmpty){ Stylist.find($and(srchConds)).toList } else Stylist.findAll.toList

    if (stylistSearch.industry.nonEmpty) {
      return stylist.filter(_.position.head.industryName == stylistSearch.industry.get)
    }

    stylist
  }

  def activeStylist(stylist: Stylist) = {
    Stylist.save(stylist.copy(stylistId = stylist.stylistId, isValid = true))
  }

  def frozenStylist(stylist: Stylist) = {
    Stylist.save(stylist.copy(stylistId = stylist.stylistId, isValid = false))
  }
}