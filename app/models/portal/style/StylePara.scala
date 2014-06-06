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
package models.portal.style

import play.api.Play.current
import play.api.PlayException
import com.mongodb.casbah.commons.Imports._
import mongoContext._
import com.meifannet.framework.db._

/**
 * The table of styleLength
 * @param id ObjectId ObjectId
 * @param styleLength 适合长度
 * @param description 描述
 */
case class StyleLength(
  id: ObjectId = new ObjectId,
  styleLength: String,
  description: String)

object StyleLength extends MeifanNetModelCompanion[StyleLength] {
  val dao = new MeifanNetDAO[StyleLength](collection = loadCollection()) {}
}

/**
 * The table of styleColor
 * @param id ObjectId ObjectId
 * @param industryName 行业类别
 * @param styleColor 适合颜色
 * @param description 描述
 */
case class StyleColor(
  id: ObjectId = new ObjectId,
  industryName: String,
  styleColor: String,
  description: String)

object StyleColor extends MeifanNetModelCompanion[StyleColor] {
  val dao = new MeifanNetDAO[StyleColor](collection = loadCollection()) {}

  /**
   * 根据行业名获取该行业所有颜色
   * @param industryName 行业名
   * @return
   */
  def findAllStyleColor(industryName: String) = dao.find(MongoDBObject("industryName" -> industryName)).toList.map {
    styleColor => styleColor.styleColor
  }

  /**
   * 根据行业名获得所有颜色
   * @param salonIndustrys 行业名集合
   *@return
   */
  def findAllStyleColor(salonIndustrys: List[String]) = {
    var styleColors: List[StyleColor] = Nil
    for (salonIndustry <- salonIndustrys) {
      val styleColor: List[StyleColor] = dao.find(MongoDBObject("industryName" -> salonIndustry)).toList
      styleColors = styleColors ::: styleColor
    }
    styleColors
  }
}

/**
 * The table of StyleImpression
 * @param id ObjectId ObjectId
 * @param industryName 行业类别
 * @param styleImpression 适合风格
 * @param description 描述
 */
case class StyleImpression(
  id: ObjectId = new ObjectId,
  industryName: String,
  styleImpression: String,
  description: String)

object StyleImpression extends MeifanNetModelCompanion[StyleImpression] {
  val dao = new MeifanNetDAO[StyleImpression](collection = loadCollection()) {}

  /**
   * 根据行业名获取该行业所有相关风格
   * @param industryName 行业名
   * @return
   */
  def findAllStyleImpression(industryName: String) = dao.find(MongoDBObject("industryName" -> industryName)).toList.map {
    styleImpression => styleImpression.styleImpression
  }

  /**
   * 根据行业名获得所有相关风格
   * @param salonIndustrys 行业名集合
   *@return
   */
  def findAllStyleImpression(salonIndustrys: List[String]) = {
    var styleImpressions: List[StyleImpression] = Nil
    for (salonIndustry <- salonIndustrys) {
      val styleImpression: List[StyleImpression] = dao.find(MongoDBObject("industryName" -> salonIndustry)).toList
      styleImpressions = styleImpressions ::: styleImpression
    }
    styleImpressions
  }

}

/**
 * The table of styleAmount
 * @param id ObjectId ObjectId
 * @param styleAmount 适合发量
 * @param description 描述
 */
case class StyleAmount(
  id: ObjectId = new ObjectId,
  styleAmount: String,
  description: String)

object StyleAmount extends MeifanNetModelCompanion[StyleAmount] {
  val dao = new MeifanNetDAO[StyleAmount](collection = loadCollection()) {}
}

/**
 * The table of styleQuality
 * @param id ObjectId
 * @param styleQuality 适合发质
 * @param description 描述
 */
case class StyleQuality(
  id: ObjectId = new ObjectId,
  styleQuality: String,
  description: String)

object StyleQuality extends MeifanNetModelCompanion[StyleQuality] {
  val dao = new MeifanNetDAO[StyleQuality](collection = loadCollection()) {}
}

/**
 * The table of styleDiameter
 * @param id ObjectId
 * @param styleDiameter 适合直径
 * @param description 描述
 */
case class StyleDiameter(
  id: ObjectId = new ObjectId,
  styleDiameter: String,
  description: String)

object StyleDiameter extends MeifanNetModelCompanion[StyleDiameter] {
  val dao = new MeifanNetDAO[StyleDiameter](collection = loadCollection()) {}
}

/**
 * The table of faceShape
 * @param id ObjectId
 * @param faceShape 适合脸型
 * @param description 描述
 */
case class FaceShape(
  id: ObjectId = new ObjectId,
  faceShape: String,
  description: String)

object FaceShape extends MeifanNetModelCompanion[FaceShape] {
  val dao = new MeifanNetDAO[FaceShape](collection = loadCollection()) {}
}

/**
 * The table of socialScene
 * @param id ObjectId
 * @param industryName 行业类别
 * @param socialScene 适合场合
 * @param description 描述
 */
case class SocialScene(
  id: ObjectId = new ObjectId,
  industryName: String,
  socialScene: String,
  description: String)

object SocialScene extends MeifanNetModelCompanion[SocialScene] {
  val dao = new MeifanNetDAO[SocialScene](collection = loadCollection()) {}

  /**
   * 根据行业名获取该行业所有相关风格
   * @param industryName 行业名
   * @return
   */
  def findAllSocialScene(industryName: String) = dao.find(MongoDBObject("industryName" -> industryName)).toList.map {
    socialScene => socialScene.socialScene
  }

  /**
   * 根据行业名获得所有相关风格
   * @param salonIndustrys 行业名集合
   *@return
   */
  def findAllSocialScene(salonIndustrys: List[String]) = {
    var socialScenes: List[SocialScene] = Nil
    for (salonIndustry <- salonIndustrys) {
      val socialScene: List[SocialScene] = dao.find(MongoDBObject("industryName" -> salonIndustry)).toList
      socialScenes = socialScenes ::: socialScene
    }
    socialScenes
  }

}

/**
 * The table of ageGroup
 * @param id ObjectId
 * @param ageGroup 适合年龄段
 * @param description 描述
 */
case class AgeGroup(
  id: ObjectId = new ObjectId,
  ageGroup: String,
  description: String)

object AgeGroup extends MeifanNetModelCompanion[AgeGroup] {
  val dao = new MeifanNetDAO[AgeGroup](collection = loadCollection()) {}
}

/**
 * The table of sex
 * @param id ObjectId
 * @param sex 适合性别
 */
case class Sex(
  id: ObjectId = new ObjectId,
  sex: String)

object Sex extends MeifanNetModelCompanion[Sex] {
  val dao = new MeifanNetDAO[Sex](collection = loadCollection()) {}
}

/**
 * The table of stylePicDescription
 * @param id ObjectId
 * @param stylePicDescription 图片描述
 * @param description 描述
 */
case class StylePicDescription(
  id: ObjectId = new ObjectId,
  stylePicDescription: String,
  description: String)

object StylePicDescription extends MeifanNetModelCompanion[StylePicDescription] {
  val dao = new MeifanNetDAO[StylePicDescription](collection = loadCollection()) {}
}

/**
 * The table of searchByLengthForF
 * 用于存放发型导航女性长度检索一栏
 * @param id ObjectId
 * @param sex 适合性别
 * @param styleLength 发长
 * @param stylePic 发型图片
 * @param description 描述
 */
case class SearchByLengthForF(
  id: ObjectId = new ObjectId,
  sex: String,
  styleLength: String,
  stylePic: ObjectId,
  description: String)

object SearchByLengthForF extends MeifanNetModelCompanion[SearchByLengthForF] {
  val dao = new MeifanNetDAO[SearchByLengthForF](collection = loadCollection()) {}
  //保存图片
  def saveSearchByLengthForFImage(searchByLengthForF: SearchByLengthForF, imgId: ObjectId) = {
    dao.update(MongoDBObject("_id" -> searchByLengthForF.id),
      MongoDBObject("$set" -> (MongoDBObject("stylePic" -> imgId))), false, true)
  }
}

/**
 * The table of searchByLengthForM
 * 用于存放发型导航男性长度检索一栏
 * @param id ObjectId
 * @param sex 适合性别
 * @param styleLength 发长
 * @param stylePic 发型图片
 * @param description 描述
 */
case class SearchByLengthForM(
  id: ObjectId = new ObjectId,
  sex: String,
  styleLength: String,
  stylePic: ObjectId,
  description: String)

object SearchByLengthForM extends MeifanNetModelCompanion[SearchByLengthForM] {
  val dao = new MeifanNetDAO[SearchByLengthForM](collection = loadCollection()) {}
  //保存图片
  def saveSearchByLengthForMImage(searchByLengthForM: SearchByLengthForM, imgId: ObjectId) = {
    dao.update(MongoDBObject("_id" -> searchByLengthForM.id),
      MongoDBObject("$set" -> (MongoDBObject("stylePic" -> imgId))), false, true)
  }
}

/**
 * The table of searchByImpression
 * 用于存放发型导航风格检索一栏
 * @param id ObjectId
 * @param sex 适合性别
 * @param styleImpression 发型适合风格
 * @param stylePic 发型图片
 * @param description 描述
 */
case class SearchByImpression(
  id: ObjectId = new ObjectId,
  sex: String,
  styleImpression: String,
  stylePic: ObjectId,
  description: String)

object SearchByImpression extends MeifanNetModelCompanion[SearchByImpression] {
  val dao = new MeifanNetDAO[SearchByImpression](collection = loadCollection()) {}
  //保存图片
  def saveSearchByImpressionImage(searchByImpression: SearchByImpression, imgId: ObjectId) = {
    dao.update(MongoDBObject("_id" -> searchByImpression.id),
      MongoDBObject("$set" -> (MongoDBObject("stylePic" -> imgId))), false, true)
  }
}

/**
 * The table of styleMaterial
 * @param id ObjectId
 * @param industryName 行业类别
 * @param styleMaterial 材质名
 * @param description 描述
 */
case class StyleMaterial(
  id: ObjectId = new ObjectId,
  industryName: String,
  styleMaterial: String,
  description: String)

object StyleMaterial extends MeifanNetModelCompanion[StyleMaterial] {
  val dao = new MeifanNetDAO[StyleMaterial](collection = loadCollection()) {}

  /**
   * 根据行业名获取该行业所有相关材质
   * @param industryName 行业名
   * @return
   */
  def findAllStyleMaterial(industryName: String) = dao.find(MongoDBObject("industryName" -> industryName)).toList.map {
    styleMaterial => styleMaterial.styleMaterial
  }

  /**
   * 根据行业名获得所有相关材质
   * @param salonIndustrys 行业名集合
   *@return
   */
  def findAllStyleMaterial(salonIndustrys: List[String]) = {
    var styleMaterials: List[StyleMaterial] = Nil
    for (salonIndustry <- salonIndustrys) {
      val styleMaterial: List[StyleMaterial] = dao.find(MongoDBObject("industryName" -> salonIndustry)).toList
      styleMaterials = styleMaterials ::: styleMaterial
    }
    styleMaterials
  }

}

/**
 * The table of styleBase
 * @param id ObjectId
 * @param industryName 行业类别
 * @param styleBase 该服务在人体那些部位（手、足。。。）
 * @param description 描述
 */
case class StyleBase(
  id: ObjectId = new ObjectId,
  industryName: String,
  styleBase: String,
  description: String)

object StyleBase extends MeifanNetModelCompanion[StyleBase] {
  val dao = new MeifanNetDAO[StyleBase](collection = loadCollection()) {}

  /**
   * 根据行业名获取该行业所有相关身体部位集合
   * @param industryName 行业名
   * @return
   */
  def findAllStyleBase(industryName: String) = dao.find(MongoDBObject("industryName" -> industryName)).toList.map {
    styleBase => styleBase.styleBase
  }

  /**
   * 根据行业名获得所有相关身体部位集合
   * @param salonIndustrys 行业名集合
   *@return
   */
  def findAllStyleBase(salonIndustrys: List[String]) = {
    var styleBases: List[StyleBase] = Nil
    for (salonIndustry <- salonIndustrys) {
      val styleBase: List[StyleBase] = dao.find(MongoDBObject("industryName" -> salonIndustry)).toList
      styleBases = styleBases ::: styleBase
    }
    styleBases
  }

}

/*/**
 * The table of styleDesign
 * @param id ObjectId
 * @param industryName 行业类别
 * @param styleDesign 款式名
 * @param description 描述
 */
case class StyleDesign(
  id: ObjectId = new ObjectId,
  industryName: String,
  styleDesign: String,
  description: String)

object StyleDesign extends MeifanNetModelCompanion[StyleDesign] {
  val dao = new MeifanNetDAO[StyleDesign](collection = loadCollection()) {}

  /**
   * 根据行业名获取该行业所有相关款式集合
   * @param industryName 行业名
   * @return
   */
  def findAllStyleDesign(industryName: String) = dao.find(MongoDBObject("industryName" -> industryName)).toList.map {
    styleDesign => styleDesign.styleDesign
  }

  /**
   * 根据行业名获得所有相关款式集合
   * @param salonIndustrys 行业名集合
   *@return
   */
  def findAllStyleDesign(salonIndustrys: List[String]) = {
    var styleDesigns: List[StyleDesign] = Nil
    for (salonIndustry <- salonIndustrys) {
      val styleDesign: List[StyleDesign] = dao.find(MongoDBObject("industryName" -> salonIndustry)).toList
      styleDesigns = styleDesigns ::: styleDesign
    }
    styleDesigns
  }

}*/