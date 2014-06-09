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
/**
 * Created by Ping-dou on 14/06/06.
 */


case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

case class MeifanSalonApySearch(id :String,
                                 salonName :String,
                                 industry :String,
                                 registerStarDate: Date,
                                 registerEndDate: Date,
                                 flag: Int
                                )

case class SalonApply (salon: models.portal.salon.Salon,
                       finishedItem: List[String])

object SalonApply extends MeifanNetModelCompanion[SalonApply] {

  val dao = new MeifanNetDAO[SalonApply](collection = loadCollection()) {}

  def isApply(salon: models.portal.salon.Salon, f: Salon => Boolean): Boolean = {
    var finishedItem: List[String] = Nil
    if(f(salon)) false else true
  }

  /**
   * Find all applied salons then return a list that contains SalonApply object
   * @param salons all salon  which is exits in database
   * @return
   */
  def findAllAPSalons(salons: List[models.portal.salon.Salon]) :List[SalonApply] = {
    var salonApplies :List[SalonApply] = Nil
    var finishedItem :List[String] = Nil
    var flag :Boolean = false
    salons.map{ salon =>
      if(isApply(salon, salon => Salon.checkBasicInfoIsFill(salon))) {
        finishedItem :::= List("basicInfo")
        flag = true
      }

      if(isApply(salon, salon => Salon.checkDetailIsFill(salon) )) {
        finishedItem :::= List("detailInfo")
        flag = true
      }

      if(isApply(salon, salon => Salon.checkImgIsExist(salon))) {
        finishedItem :::= List("imageInfo")
        flag = true
      }

      if(flag && salon.isValid == false) {salonApplies :::= List(new SalonApply(salon,finishedItem))} else salonApplies
    }
    salonApplies
  }

  /**
   * Agree the apply item from salon, set the verification valid is true
   * @param salon The salon item which is apply
   * @return unit
   */
  def agreeSalonApy(salon: models.portal.salon.Salon) = {
    models.portal.salon.Salon.save(salon.copy(id = salon.id, isValid = true))
  }

  /**
   * Agree the apply item from salon, set the verification valid is false
   * @param salon The salon item which is apply
   * @return unit
   */
  def rejectSalonApy(salon: models.portal.salon.Salon) = {
    models.portal.salon.Salon.save(salon.copy(id = salon.id, isValid = false))
  }

  def findSalonApyByCondition(salonApySearch :MeifanSalonApySearch) = {
    if(salonApySearch.id.nonEmpty){
      val obj="""ObjectId//(/"[0-9a-z]{24}/"//)"""
      if(salonApySearch.id.matches(obj)){
        val salon = Salon.findOneById(new ObjectId(salonApySearch.id))
      }esle{
        val salon = Salon.findOneByAccountId(salonApySearch.id)
      }
    }
    if(salonApySearch.industry.nonEmpty) {
      Salon.find(MongoDBObject("salonIndustry" $in salonApySearch.industry ))
    }
  }

}