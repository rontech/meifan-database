package models.manager

import models.portal.industry.Industry
import models.portal.industry.Position
import com.meifannet.framework.db.{MeifanNetDAO, MeifanNetModelCompanion}
import scala.concurrent.{ ExecutionContext, Future }
import com.mongodb.casbah.Imports._
import models.portal.service.ServiceType
import models.portal.style._

/**
 * Created by ping-dou on 14/06/19.
 */
case class MasterTableManager(category: String, id: ObjectId, name: String, industryName:Option[String], description:Option[String]) {

}

object MasterTableManager {

  /**
   * findAll master table
   * 每个主表查询的所有结果都封装成MasterTableManager对象，放入同一个集合中
   * @return
   */
  def getAllMaster = {
    //有些主表字段会有 industryName 、 description, 如果不存在设为none
    val industryRst = findOneCollectionByCategory("Industry")

    //职位
    val positionRst = findOneCollectionByCategory("Position")

    //服务类别
    val serviceTypeRst = findOneCollectionByCategory("ServiceType")


    //发型风格
    val styleImpressionRst = findOneCollectionByCategory("StyleImpression")

    //长度
    val styleLengthRst = findOneCollectionByCategory("StyleLength")

    //发量
    val styleAmountRst = findOneCollectionByCategory("StyleAmount")

    //发质
    val styleQualityRst = findOneCollectionByCategory("StyleQuality")

    //发质粗细
    val styleDiameterRst = findOneCollectionByCategory("StyleDiameter")

    //脸型
    val faceShapeRst = findOneCollectionByCategory("FaceShape")

    //发色
    val styleColorRst = findOneCollectionByCategory("StyleColor")

    //年龄分段
    val ageGroupRst = findOneCollectionByCategory("AgeGroup")

    //人群
    val socialSceneRst = findOneCollectionByCategory("SocialScene")

    //merge all collections
    val list = industryRst ::: positionRst ::: serviceTypeRst ::: styleImpressionRst ::: styleLengthRst ::: styleAmountRst ::: styleQualityRst ::: styleDiameterRst ::: faceShapeRst ::: styleColorRst ::: ageGroupRst ::: socialSceneRst
    //根据类别分类，结果为 ((String, List[models.manager.MasterTableManager]), Int))
    list.groupBy(x => x.category).map( y => (y,y._2.length)).toList
  }

  def findObjByCategory(category: String) = {
    //val obj = Class.forName("Industry").asInstanceOf[Industry]
    category match {
      case "Industry" => Industry

      case "Position" =>Position

      case "ServiceType" => ServiceType

      case "StyleImpression" => StyleImpression

      case "StyleLength" => StyleLength

      case "StyleAmount" => StyleAmount

      case "StyleQuality" => StyleQuality

      case "StyleDiameter" => StyleDiameter

      case "FaceShape" => FaceShape

      case "StyleColor" => StyleColor

      case "AgeGroup" => AgeGroup

      case "SocialScene" => SocialScene
    }
  }

  /**
   * get one specific collection by category, then
   * package to a list
   * @param category for match which collection will find
   * @return
   */
  def findOneCollectionByCategory(category: String): List[MasterTableManager] = {
    category match {
      case "Industry" => {
        val industries = Industry.findAll.toList
        val industryRst = industries.flatMap{ industry =>
          List(new MasterTableManager("Industry", industry.id, industry.industryName, None, None))
        }
        industryRst
      }

      case "Position" => {
        val positions = Position.findAll.toList
        //职位
        val positionRst = positions.flatMap{ position =>
          List(new MasterTableManager("Position", position.id, position.positionName, None, None))
        }
        positionRst
      }

      case "ServiceType" => {
        val serviceTypes = ServiceType.findAll.toList
        //服务类别
        val serviceTypeRst = serviceTypes.flatMap{serviceType =>
          List(new MasterTableManager("ServiceType", serviceType.id, serviceType.serviceTypeName, Option(serviceType.industryName), Option(serviceType.description)))
        }
        serviceTypeRst
      }

      case "StyleImpression" => {
        val styleImpressions = StyleImpression.findAll.toList
        val styleImpressionRst = styleImpressions.flatMap{ styleImpression =>
          List(new MasterTableManager("StyleImpression", styleImpression.id, styleImpression.styleImpression, Option(styleImpression.industryName), Option(styleImpression.description)))
        }
        styleImpressionRst
      }

      case "StyleLength" => {
        val styleLengths = StyleLength.findAll.toList
        val styleLengthRst = styleLengths.flatMap { styleLength =>
          List(new MasterTableManager("StyleLength", styleLength.id, styleLength.styleLength, None, Option(styleLength.description)))
        }
        styleLengthRst
      }

      case "StyleAmount" => {
        val styleAmounts = StyleAmount.findAll.toList
        //发量
        val styleAmountRst = styleAmounts.flatMap{ styleAmount =>
          List(new MasterTableManager("StyleAmount", styleAmount.id, styleAmount.styleAmount, None, Option(styleAmount.description)))
        }
        styleAmountRst
      }
      case "StyleQuality" => {
        val styleQualities = StyleQuality.findAll.toList
        //发质
        val styleQualityRst = styleQualities.flatMap{ styleQuality =>
          List(new MasterTableManager("StyleQuality", styleQuality.id, styleQuality.styleQuality, None, Option(styleQuality.description)))
        }
        styleQualityRst
      }

      case "StyleDiameter" => {
        val styleDiameters = StyleDiameter.findAll.toList
        //发质粗细
        val styleDiameterRst = styleDiameters.flatMap{ styleDiameter =>
          List(new MasterTableManager("StyleDiameter", styleDiameter.id, styleDiameter.styleDiameter, None, Option(styleDiameter.description)))
        }
        styleDiameterRst
      }

      case "FaceShape" => {
        val faceShapes = FaceShape.findAll.toList
        //脸型
        val faceShapeRst = faceShapes.flatMap { faceShape =>
          List(new MasterTableManager("FaceShape", faceShape.id, faceShape.faceShape, None, Option(faceShape.description)))
        }
        faceShapeRst
      }

      case "StyleColor" => {
        val styleColors = StyleColor.findAll.toList
        //发色
        val styleColorRst = styleColors.flatMap { styleColor =>
          List(new MasterTableManager("StyleColor", styleColor.id, styleColor.styleColor, Option(styleColor.industryName), Option(styleColor.description)))
        }
        styleColorRst
      }

      case "AgeGroup" => {
        val ageGroups = AgeGroup.findAll.toList
        //年龄分段
        val ageGroupRst = ageGroups.flatMap { ageGroup =>
          List(new MasterTableManager("AgeGroup", ageGroup.id, ageGroup.ageGroup, None, Option(ageGroup.description)))
        }
        ageGroupRst
      }

      case "SocialScene" => {
        val socialScenes = SocialScene.findAll.toList
        //人群
        val socialSceneRst = socialScenes.flatMap { socialScene =>
          List(new MasterTableManager("SocialScene", socialScene.id, socialScene.socialScene, Option(socialScene.industryName), Option(socialScene.description)))
        }
        socialSceneRst
      }
    }

  }

  /**
   * insert a item into a specific collection in mongo,
   * if industryName or description isn't exists use ""
   * instead and it will not access to corresponding
   * field in mongo.
   * @param category the type of mongo collection
   * @param name the item name of type collection
   * @param industryName industry name for current insert item
   * @param description
   * @return
   */
  def addTableItem(category: String, name: String)(industryName: Option[String] = None, description: Option[String] = None): Unit = {
    category match {
      case "Industry" => Industry.save(Industry.apply(new ObjectId, name))

      case "Position" => Position.save(Position.apply(new ObjectId, name))

      case "ServiceType" => ServiceType.save(ServiceType.apply(new ObjectId, industryName.map{n => n }getOrElse{""}, name, description.map{n => n }getOrElse{""}))

      case "StyleImpression" => StyleImpression.save(StyleImpression.apply(new ObjectId,industryName.map{n => n }getOrElse{""}, name, description.map{n => n }getOrElse{""}))

      case "StyleLength" => StyleLength.save(StyleLength.apply(new ObjectId, name, description.map{n => n }getOrElse{""}))

      case "StyleAmount" => StyleAmount.save(StyleAmount.apply(new ObjectId, name, description.map{n => n }getOrElse{""}))

      case "StyleQuality" => StyleQuality.save(StyleQuality.apply(new ObjectId, name, description.map{n => n }getOrElse{""}))

      case "StyleDiameter" => StyleDiameter.save(StyleDiameter.apply(new ObjectId, name, description.map{n => n }getOrElse{""}))

      case "FaceShape" => FaceShape.save(FaceShape.apply(new ObjectId, name, description.map{n => n }getOrElse{""}))

      case "StyleColor" => StyleColor.save(StyleColor.apply(new ObjectId, industryName.map{n => n }getOrElse{""}, name, description.map{n => n }getOrElse{""}))

      case "AgeGroup" => AgeGroup.save(AgeGroup.apply(new ObjectId, name, description.map{n => n }getOrElse{""}))

      case "SocialScene" => SocialScene.save(SocialScene.apply(new ObjectId, industryName.map{n => n }getOrElse{""}, name, description.map{n => n }getOrElse{""}))
    }
  }
}
