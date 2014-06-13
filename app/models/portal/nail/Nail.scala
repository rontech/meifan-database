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
package models.portal.nail

import java.util.Date
import models.portal.style._
import models.portal.service.ServiceType
import models.portal.search.PriceRange
import com.mongodb.casbah.query.Imports._
import com.mongodb.casbah.commons.Imports.{ DBObject => commonsDBObject }
import mongoContext._
import models._
import com.meifannet.framework.db._

import models.portal.common.OnUsePicture
import models.portal.relation.SalonAndStylist
import models.portal.stylist.Stylist
import models.portal.salon.Salon


/**
 * 美甲数据及其他一些表中字段整合类
 * @param nail 美甲数据
 * @param salonId 店铺ID
 * @param salonName 店铺名
 * @param salonNameAbbr 店铺昵称
 * @param stylistId 技师ID
 * @param stylistNickname 技师昵称
 */
case class NailWithAllInfo(
  nail: Nail,
  salonId: ObjectId,
  salonName: String,
  salonNameAbbr: String,
  stylistId: ObjectId,
  stylistNickname: String)

/**
 * 美甲相关辅助主表数据整合类
 * @param serviceType 美甲技术类别主表数据
 * @param styleColor 美甲颜色主表数据
 * @param styleMaterial 美甲材质主表数据
 * @param styleBase 美甲作用部位主表数据
 * @param styleImpression 美甲风格主表数据
 * @param socialScene 美甲适用场合主表数据
 * @param priceRange 美甲价格区间主表数据
 */
case class StylePara (
  serviceType: List[String],
  styleColor: List[String],
  styleMaterial: List[String],
  styleBase: List[String],
  styleImpression: List[String],
  socialScene: List[String],
  priceRange:List[Tuple2[BigDecimal,BigDecimal]])

/**
 * 美甲检索字段整合类
 * @param keyWord 关键字
 * @param city 城市
 * @param region 地区
 * @param stylistId 技师Id
 * @param serviceType 技术类别
 * @param styleColor 美甲颜色
 * @param styleMaterial 美甲材质
 * @param styleBase 美甲作用部位
 * @param styleImpression 美甲风格
 * @param socialScene 美甲适用场合
 * @param priceRange 美甲价格
 */
case class SearchPara(
   keyWord: Option[String],
   city: String,
   region: String,
   stylistId:String,
   serviceType: List[String],
   styleColor: List[String],
   styleMaterial: List[String],
   styleBase: List[String],
   styleImpression: List[String],
   socialScene: List[String],
   priceRange: PriceRange)

/**
 * 美甲主类
 * @param id 美甲ID
 * @param styleName 美甲名称
 * @param stylistId 技师Id
 * @param stylePrice 美甲的价格
 * @param serviceType 技术类别
 * @param styleColor 颜色
 * @param styleMaterial 材质
 * @param styleBase 身体部位（手、足）
 * @param styleImpression 美甲风格
 * @param socialScene 美甲适合场合
 * @param stylePic 美甲图片
 * @param description 美甲描述
 * @param createDate 创建时间
 * @param isValid 是否有效
 */
case class Nail(
  id: ObjectId = new ObjectId,
  styleName: String,
  stylistId: ObjectId,
  stylePrice: BigDecimal,
  serviceType: List[String],
  styleColor: List[String],
  styleMaterial: List[String],
  styleBase: List[String],
  styleImpression: String,
  socialScene: List[String],
  stylePic: List[OnUsePicture],
  description: String,
  createDate: Date,
  isValid: Boolean)

object Nail extends MeifanNetModelCompanion[Nail] {

  val dao = new MeifanNetDAO[Nail](collection = loadCollection()) {}

  /**
   * 获取美甲检索字段主表数据,并将它们放入整合类StylePara中
   * @return StylePara
   */
  def findParaAll = {
    //获得相应主表数据
    val paraServiceType = ServiceType.findAllServiceType("Manicures").toList
    var paraServiceTypes: List[String] = Nil
    paraServiceType.map { para =>
      paraServiceTypes :::= List(para)
    }
    val paraStyleColor = StyleColor.findAllStyleColor("Manicures").toList
    var paraStyleColors: List[String] = Nil
    paraStyleColor.map { para =>
      paraStyleColors :::= List(para)
    }
    val paraStyleMaterial = StyleMaterial.findAllStyleMaterial("Manicures").toList
    var paraStyleMaterials: List[String] = Nil
    paraStyleMaterial.map { para =>
      paraStyleMaterials :::= List(para)
    }
    val paraStyleBase = StyleBase.findAllStyleBase("Manicures").toList
    var paraStyleBases: List[String] = Nil
    paraStyleBase.map { para =>
      paraStyleBases :::= List(para)
    }
    val paraStyleImpression = StyleImpression.findAllStyleImpression("Manicures").toList
    var paraStyleImpressions: List[String] = Nil
    paraStyleImpression.map { para =>
      paraStyleImpressions :::= List(para)
    }
    val paraSocialScene = SocialScene.findAllSocialScene("Manicures").toList
    var paraSocialScenes: List[String] = Nil
    paraSocialScene.map { para =>
      paraSocialScenes :::= List(para)
    }
    val paraPriceRange = PriceRange.findAllPriceRange("Manicures").toList
    var paraPriceRanges: List[Tuple2[BigDecimal,BigDecimal]] = Nil
    paraPriceRange.map { para =>
      paraPriceRanges :::= List(para)
    }
    //将检索出来的主表数据放到发型主表字段整合类中
    val stylePara = new StylePara(paraServiceTypes, paraStyleColors, paraStyleMaterials, paraStyleBases, paraStyleImpressions, paraSocialScenes, paraPriceRanges)
    stylePara
  }

  /**
   * 保存初始化图片
   * @param nail 逐条美甲数据
   * @param imgId 图片id
   * @return
   */
  def updateNailImage(nail: Nail, imgId: ObjectId) = {
    dao.update(MongoDBObject("_id" -> nail.id, "stylePic.fileObjId" -> nail.stylePic(0).fileObjId),
      MongoDBObject("$set" -> (MongoDBObject("stylePic.$.fileObjId" -> imgId))), false, true)
  }

  /**
   * 前台主检索逻辑，获得包括店铺信息、技师信息的整合结果
   * @param searchPara 美甲检索条件
   * @param limitCnt 数量限制
   * @return
   */
  def findNailBySearchPara(searchPara: SearchPara)(limitCnt: Int = 0) = {
    //获取美甲检索的主要检索条件
    val srchConds = commonSrchConds(searchPara)
    val nailSrchRst = dao.find($and(srchConds)).toList
    val nailWithAllInfo: List[NailWithAllInfo] = getNailWithAllInfo(nailSrchRst)(searchPara)(limitCnt)
    nailWithAllInfo
  }

  /**
   * 对已检索出的数据，进行所在店铺是否符合地域检索条件的判断，同时将添加相关店铺信息到检索结果中
   * @param nails 用主要检索字段检索的结果
   * @param searchPara 检索参数
   * @param limitCnt 数量限制 -控制检索结果
   * @return
   */
  def getNailWithAllInfo(nails: List[Nail])(searchPara: SearchPara)(limitCnt: Int = 0): List[NailWithAllInfo] = {
    var nailWithAllInfo: List[NailWithAllInfo] = Nil
    var cnt: Int = 0
    for (nail <- nails) {
      if (limitCnt == 0 || cnt <= limitCnt) {
        //通过美甲的技师Id查看与有绑定关系的店铺
        val salon = Style.findSalonByStyle(nail.stylistId)
        //判断美甲对应的店铺是否在检索条件的地理区域中,进行检索条件中的城市、地区check
        salon.map(salon => {
          if (searchPara.region.equals("all")) {
            if (salon.salonAddress.get.city.get.startsWith(searchPara.city)) {
              val nailInfo = addNailWithInfo(nail, salon)
              nailWithAllInfo = nailWithAllInfo ::: List(nailInfo)
              cnt += 1
            }
          } else {
            if (salon.salonAddress.get.city.get.startsWith(searchPara.city) && salon.salonAddress.get.region.get.startsWith(searchPara.region)) {
              val nailInfo = addNailWithInfo(nail, salon)
              nailWithAllInfo = nailWithAllInfo ::: List(nailInfo)
              cnt += 1
            }
          }
        }).getOrElse(None)
      }
    }
    nailWithAllInfo
  }

  /**
   * 将美甲信息和店铺信息提取需要的字段，整合到一起
   * @param nail 美甲信息
   * @param salon 店铺信息
   * @return
   */
  def addNailWithInfo(nail: Nail, salon: Salon) = {
    val stylistName = Stylist.findUserName(nail.stylistId)
    val nailWithInfo = NailWithAllInfo(nail, salon.id, salon.salonName, salon.salonNameAbbr.getOrElse(salon.salonName), nail.stylistId, stylistName)
    nailWithInfo
  }

  /**
   * 存放美甲检索的主要检索条件
   * @param searchPara 画面检索条件
   * @return
   */
  def commonSrchConds(searchPara: SearchPara): List[commonsDBObject] = {
    var srchConds: List[commonsDBObject] = Nil

    if (searchPara.serviceType.nonEmpty) {
      srchConds :::= List("serviceType" $in searchPara.serviceType)
    }
    if (searchPara.styleColor.nonEmpty) {
      srchConds :::= List("styleColor" $in searchPara.styleColor)
    }
    if (searchPara.styleMaterial.nonEmpty) {
      srchConds :::= List("styleMaterial" $in searchPara.styleMaterial)
    }
    if (searchPara.styleBase.nonEmpty) {
      srchConds :::= List("styleBase" $in searchPara.styleBase)
    }
    if (searchPara.styleImpression.nonEmpty) {
      srchConds :::= List("styleImpression" $in searchPara.styleImpression)
    }
    if (searchPara.socialScene.nonEmpty) {
      srchConds :::= List("socialScene" $in searchPara.socialScene)
    }
    //价格区间
    srchConds :::= List("stylePrice" $lte searchPara.priceRange.maxPrice.toFloat $gte searchPara.priceRange.minPrice.toFloat)
    //有效性
    srchConds :::= List(commonsDBObject("isValid" -> true))
    srchConds
  }
}