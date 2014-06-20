package models.portal.manager

import com.meifannet.framework.db.{MeifanNetDAO, MeifanNetModelCompanion}
import scala.concurrent.{ ExecutionContext, Future }
import ExecutionContext.Implicits.global
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import mongoContext._
import com.mongodb.casbah.WriteConcern
import se.radley.plugin.salat._
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
//import com.mongodb.casbah.query.Imports._
import com.mongodb.casbah.query._
import com.mongodb.casbah.commons.Imports.{ DBObject => commonsDBObject }
import models.portal.salon.SalonStatus
import models.portal.manager.MeifanSalonApySearch

/**
 * Created by Ping-dou on 14/06/06.
 */


case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

case class MeifanSalonApySearch(id :Option[String],
                                 salonName :Option[String],
                                 industry :Option[String],
                                 registerStarDate: Option[Date],
                                 registerEndDate: Option[Date],
                                 flag: Option[Int]
                                )

case class SalonApply (salon: models.portal.salon.Salon,
                       finishedItem: List[String])

object SalonApply {

  //val dao = new MeifanNetDAO[SalonApply](collection = loadCollection()) {}


  /**
   * check the salon information is finish
   * @param salon object salon for check
   * @param f function for verify salon
   * @return false or true
   */
  def infoIsFinish(salon: models.portal.salon.Salon, f: Salon => Boolean): Boolean = f(salon)

  def date(d: Date) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(d)
  /**
   * Find all applied salons then return a list that contains SalonApply object
   * @param salons all salon  which is exits in database
   * @return
   */
  def findAllAPSalons(salons: List[models.portal.salon.Salon], meifanSalonFlag :Int) :List[SalonApply] = {
    var salonApplies :List[SalonApply] = Nil
    salons.map{ salon =>
      var flag :Boolean = false
      var finishedItem :List[String] = Nil
      if(infoIsFinish(salon, salon => Salon.checkBasicInfoIsFill(salon))) {
        finishedItem :::= List("BasicInfo")
      }else { flag = true }

      if(infoIsFinish(salon, salon => Salon.checkDetailIsFill(salon) )) {
        finishedItem :::= List("DetailInfo")

      }

      if(infoIsFinish(salon, salon => Salon.checkImgIsExist(salon))) {
        finishedItem :::= List("ImageInfo")
      }else { flag = true }

      if(flag || salon.salonStatus.applyMeifanFlag == meifanSalonFlag) {salonApplies :::= List(new SalonApply(salon,finishedItem))} else salonApplies

    }
    salonApplies

  }

  /**
   * Agree the apply item from salon, set the verification valid is true
   * @param salon The salon item which is apply
   * @return unit
   */
  def agreeSalonApy(salon: models.portal.salon.Salon) = {
    models.portal.salon.Salon.save(salon.copy(id = salon.id, salonStatus =new SalonStatus(1,true)))
  }

  /**
   * Reject the apply item from salon, set the verification valid is false
   * @param salon The salon item which is apply
   * @return
   */
  def rejectSalonApy(salon: models.portal.salon.Salon) = {
    models.portal.salon.Salon.save(salon.copy(id = salon.id, salonStatus =new SalonStatus (2,false)))
  }

  /**
   * Find salon by search condition object
   * @param salonApySearch
   */
  def findSalonApyByCondition(salonApySearch :MeifanSalonApySearch) :List[SalonApply] = {
    println("method in ..........")
    var srchConds: List[commonsDBObject] = Nil
    if(salonApySearch.id.nonEmpty){
      val id = salonApySearch.id.get
      val obj="""[0-9a-z]{24}"""
      //check the id is objectId or accountId
      if(id.matches(obj)){
        srchConds :::= List(commonsDBObject("_id" -> salonApySearch.id))
      }else{
        srchConds :::= List(commonsDBObject("salonAccount.accountId" -> salonApySearch.id))
      }
    }

    //industry condition
    if(salonApySearch.industry.nonEmpty) {
      srchConds :::= List("salonIndustry" $in List(salonApySearch.industry.get))
    }

    if(salonApySearch.registerStarDate.nonEmpty){
      srchConds :::= List("registerDate" $gte salonApySearch.registerStarDate.get)
    }

    if(salonApySearch.registerStarDate.nonEmpty){
      srchConds :::= List("registerDate" $lte salonApySearch.registerEndDate.get)
    }

    if(salonApySearch.flag.nonEmpty) {
      srchConds :::= List(commonsDBObject("salonStatus.applyMeifanFlag" -> salonApySearch.flag.get))
    }

    val salons :List[Salon] = Salon.find($and(srchConds)).toList

    findAllAPSalons(salons, salonApySearch.flag.get)
  }

}

object SalonManager {

  /**
   * Delete salon by change it's valid is false
   * @param salon
   * @return
   */
  def deleteSalon(salon :Salon) = {
    val flag = salon.salonStatus.applyMeifanFlag
    Salon.save(salon.copy(id = salon.id, salonStatus =  new SalonStatus(flag, false)))
  }

  /**
   * Active salon account by change it's valid is true
   * @param salon
   * @return
   */
  def activeSalon(salon :Salon) = {
    val flag = salon.salonStatus.applyMeifanFlag
    Salon.save(salon.copy(id = salon.id, salonStatus =  new SalonStatus(flag, true)))
  }
}