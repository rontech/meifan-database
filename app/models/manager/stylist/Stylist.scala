package models.manager.stylist

import com.mongodb.casbah.Imports._
import se.radley.plugin.salat.Binders._
import mongoContext._
import play.api.PlayException
import scala.concurrent.{ ExecutionContext, Future }
import ExecutionContext.Implicits.global
import com.meifannet.framework.db._
import play.api.data.Forms._
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.commons.Imports._
import models.manager.stylist.MeifanStylistSearch
import com.mongodb.casbah.commons.Imports.{ DBObject => commonsDBObject }
/**
 * Created by CCC on 14/06/18.
 */
class Stylist (
//需要字段：技师ID 技师账号 行业 昵称 联系电话 邮箱 是否签约店铺 状态 操作
id: ObjectId = new ObjectId,
stylistId: ObjectId,
userId: String,
nickName: String,
email: String,
tel: Option[String],
industry :Option[String],
isVerified: Boolean,//技师申请状态
isValid: Boolean//技师是否有效
                )
object Stylist extends MeifanNetModelCompanion[Stylist] {
  val dao = new MeifanNetDAO[Stylist](collection = loadCollection()) {}
}

case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}
case class MeifanStylistSearch(userId :Option[String],
                                nickName :Option[String],
                                industry :Option[String],
                                isVerified: Option[Boolean]
                                 )

case class StylistApply (stylist : models.portal.stylist.Stylist)
object StylistApply  {
/*                                            */
  def findAllAPStylists() : List[models.portal.stylist.Stylist] = {

    var stylistApplies :List[models.portal.stylist.Stylist] = Nil
    stylistApplies = models.portal.stylist.Stylist.findAll.toList
    stylistApplies
  }

  /*                                            */
  def findStylistByCondition(stylistSearch : MeifanStylistSearch ) :List[models.portal.stylist.Stylist] = {
    println( ""+stylistSearch.industry.nonEmpty)
    var srchConds: List[commonsDBObject] = Nil
    if(stylistSearch.userId.nonEmpty){
      println("stylistId")
        srchConds :::= List(commonsDBObject("stylistId" -> stylistSearch.userId.get ))
      }
    if(stylistSearch.industry.nonEmpty){
      if(stylistSearch.industry.get.equals("Hairdressing" )){
        println("industry")
        //srchConds :::= List("industry" $in List(stylistSearch.industry.get))
      }
      srchConds :::= List("industry" $in List(stylistSearch.industry.get))
    }
    if(stylistSearch.isVerified.nonEmpty){
      if(stylistSearch.isVerified.get.equals("true")){
        println("true")
      srchConds :::= List(commonsDBObject("isValid"->true))}
      else{
        println("false")
        srchConds :::= List(commonsDBObject("isValid"->false))}

    }


    var stylist :List[models.portal.stylist.Stylist] = models.portal.stylist.Stylist.find($and(srchConds)).toList
    stylist


  }

  def activeStylist(stylist:models.portal.stylist.Stylist) = {
    models.portal.stylist.Stylist.save(stylist.copy(stylistId = stylist.stylistId, isValid = false))
  }

  def frozenStylist(stylist:models.portal.stylist.Stylist) = {
    models.portal.stylist.Stylist.save(stylist.copy(stylistId = stylist.stylistId, isValid = true))
  }
  }


//





