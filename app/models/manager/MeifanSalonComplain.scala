package models.manager

import com.meifannet.framework.db.{MeifanNetDAO, MeifanNetModelCompanion}
import scala.concurrent.{ ExecutionContext, Future }
import ExecutionContext.Implicits.global
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import mongoContext._
import com.mongodb.casbah.WriteConcern
import se.radley.plugin.salat.Binders._
import mongoContext._
import play.api.Play.current
import play.api.PlayException
import play.Configuration
import com.meifannet.framework.db._
import ExecutionContext.Implicits.global
import models.portal.salon._
import java.util.Date
import scala.util.matching.Regex
import mongoContext._
import com.mongodb.casbah.query._
import com.mongodb.casbah.commons.Imports.{ DBObject => commonsDBObject }
import models.portal.review._
import models.portal.reservation._

/**
 * 用于检索salon申诉的case class
 *
 * @param accountId salon的accoutId
 * @param salonName salon的name
 * @param complainStartDate 申诉的起始时间
 * @param complainEndDate 申诉的终止时间
 * @param flag 申诉的状态
 */
case class MeifanSalonComplainSearch(accountId :Option[String],
                                salonName :Option[String],
                                complainStartDate: Option[Date],
                                complainEndDate: Option[Date],
                                flag:Option[Int]
                                 )

object  MeifanSalonComplain {

  /**
   * Find salon complain by search condition object
   * @param salonComplainSearch
   */
  def findSalonComplainByCondition(salonComplainSearch :MeifanSalonComplainSearch) :List[ComplainOfSalon] = {
    var srchConds: List[commonsDBObject] = Nil

    if(salonComplainSearch.complainStartDate.nonEmpty){
      srchConds :::= List("createTime" $gte salonComplainSearch.complainStartDate.get)
    }

    if(salonComplainSearch.complainEndDate.nonEmpty){
      srchConds :::= List("createTime" $lte salonComplainSearch.complainEndDate.get)
    }

    srchConds :::= List(commonsDBObject("commentObjType" -> CommentType.Complain.id))

    var complain :List[Comment] = Comment.find($and(srchConds)).toList

    if(salonComplainSearch.flag.nonEmpty) {
      if(salonComplainSearch.flag.get == 1){
        complain = complain.filter(comp => {Comment.findOneById(comp.commentObjId).get.isValid == false})
      }
      if(salonComplainSearch.flag.get == 0){
        complain = complain.filter(comp => {comp.isValid == true && Comment.findOneById(comp.commentObjId).get.isValid == true})
      }

      if(salonComplainSearch.flag.get == 2){
        complain = complain.filter(_.isValid == false)
      }
    }

    var complainListByAccountId: List[Comment] = Nil
    if(salonComplainSearch.accountId.nonEmpty){
      Salon.findOneByAccountId(salonComplainSearch.accountId.get) match {
        case Some(salon) =>{
          Reservation.findAllResvBySalon(salon.id).map{resv =>
            complainListByAccountId :::= complain.filter(comp => {Comment.findOneById(comp.commentObjId).get.commentObjId == resv.id})
          }
          complain = complainListByAccountId
        }
        case None => complain = Nil
      }
    }

    var complainListByName: List[Comment] = Nil
    if(salonComplainSearch.salonName.nonEmpty){
      // TODO regx
      Salon.findOneBySalonName(salonComplainSearch.salonName.get) match {
        case Some(salon) =>{
          Reservation.findAllResvBySalon(salon.id).map{resv =>
//            srchConds :::= List("commentObjId" $in resv.id)
            complainListByName :::= complain.filter(comp => {Comment.findOneById(comp.commentObjId).get.commentObjId == resv.id})
          }
          complain = complainListByName
        }
        case None => complain = Nil
      }
    }

//    complain
    Comment.findComplainOfSalon(complain)
  }
}
