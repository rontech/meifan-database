import models.manager.admin.Admin
import models.portal.common.Address
import models.portal.common.Address
import models.portal.common.Address
import models.portal.common.OptContactMethod
import models.portal.common.OptContactMethod
import models.portal.common.OptContactMethod
import models.portal.reservation.ResvItem
import models.portal.reservation.ResvItem
import models.portal.reservation.ResvItem
import models.portal.salon.BriefIntroduction
import models.portal.salon.BriefIntroduction
import models.portal.salon.BriefIntroduction
import models.portal.salon.Contact
import models.portal.salon.Contact
import models.portal.salon.Contact
import models.portal.salon.RestDay
import models.portal.salon.RestDay
import models.portal.salon.RestDay
import models.portal.salon.SalonAccount
import models.portal.salon.SalonAccount
import models.portal.salon.SalonAccount
import models.portal.salon.SalonStatus
import models.portal.salon.SalonStatus
import models.portal.salon.SalonStatus
import models.portal.salon.WorkTime
import models.portal.salon.WorkTime
import models.portal.salon.WorkTime
import play.api._
import org.bson.types.ObjectId
import java.util.Date
import java.io.File
import play.api.mvc.Results._
import play.api.mvc._
import scala.concurrent.Future

import models.portal.advert._
import models.portal.blog._ 
import models.portal.common._ 
import models.portal.coupon._ 
import models.portal.industry._ 
import models.portal.info._
import models.portal.mail._
import models.portal.menu._ 
import models.portal.question._ 
import models.portal.relation._ 
import models.portal.reservation._ 
import models.portal.review._ 
import models.portal.salon._ 
import models.portal.search._ 
import models.portal.service._ 
import models.portal.style._ 
import models.portal.stylist._ 
import models.portal.user._
import models.portal.nail._
import scala.Some
import scala.Some
import scala.Some


object Global extends GlobalSettings {

  /**
   *
   * @param app
   * @return
   */
  override def onStart(app: Application) {
    // Initial Master Data.
//    InitialData.insertMaster()
    // Initial Base Data.
//    InitialData.insertBaseData()
    // Initial Test Data.
//    InitialData.insertSampleData()
//    InitialData.insertAdmin()
    // 临时追加，发型导航数据
//    InitialData.insertGuide()
  }

}

/**
 * Initial set of data to be imported
 * in the sample application.
 *
 */
object InitialData {

  def date(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str)

  def dateTime(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str)

  /*---------------------------
 * Admin Data For Initialization.
 * 预先需要登录的Admin数据
 *--------------------------*/
  def insertAdmin() = {
    if (Admin.findAll.isEmpty){
      Seq(
        // initial password is "rontech.1" for temp.
        Admin(new ObjectId,"cheng-zhang@sz-rontech.com", "cheng-zhang", "$2a$10$pHIxh5nqCyunIef.wkwGaeni6HyVyXlZpj3rAvmJhJkMvD6mIet9."),
        Admin(new ObjectId,"jie-zhang@sz-rontech.com", "jie-zhang", "$2a$10$pHIxh5nqCyunIef.wkwGaeni6HyVyXlZpj3rAvmJhJkMvD6mIet9."),
        Admin(new ObjectId,"admin@sz-rontech.com", "Administrator", "$2a$10$pHIxh5nqCyunIef.wkwGaeni6HyVyXlZpj3rAvmJhJkMvD6mIet9.")
      ).foreach(Admin.save)
    }
  }

  // 临时追加，发型导航数据
  def insertGuide() = {
    insertInitSearchByLengthForF

    insertInitSearchByLengthForM

    insertInitSearchByImpression

    insertInitPicture
  }

  /*---------------------------
   * Master Data For Initialization. 
   * 预先需要登录的主表数据
   *--------------------------*/
  def insertMaster() = {

    // For nowtime, we change the Table Defination frequently....
    // If you have some errors about the DB, try to drop the database [fashion-mongo] which store the site data, and 
    //     the [Picture] db which store all of the pictures the site using.
    InsertInitIndustry

    insertInitPosition

    insertInitIndustryAndPosition

    insertInitServiceType

    insertInitStyleColor

    insertInitStyleLength

    insertInitStyleImpression

    insertInitStyleAmount

    insertInitStyleQuality

    insertInitStyleDiameter

    insertInitFaceShape

    insertInitSocialScene

    insertInitStyleMaterial

    insertInitStyleBase

    insertInitSex

    insertInitAgeGroup
    
    insertInitStylePicDescription
    
    insertInitBlogCategory

    insertInitContMethodType

    insertInitSearchByLengthForF

    insertInitSearchByLengthForM

    insertInitSearchByImpression

    insertInitDefaultLog

    insertInitPriceRange

    insertInitSeatNums

    insertInitBrand

    insertInitHotestkeyword

    insertInitPicture

  }

  /*---------------------------
   * Base Data.
   * 基础数据
   *--------------------------*/
  def insertBaseData() = {

    insertBaseMessage

    insertBaseQuestion

    insertBaseNotice

    insertBaseInfo

    insertBasePicture
  }

  /*---------------------------
   * Sample Data For Test.
   * 测试数据
   *--------------------------*/
  def insertSampleData() {


    insertSampleSalon

    insertSampleStylist

    insertSampleUser

    insertSampleSalonAndStylist

    insertSampleStyle

    insertSampleNail

    insertSampleCoupon

    insertSampleMenu

    insertSampleService

    insertSampleMyFollow

    insertSampleBlog

    insertSampleComment

    insertSampleReservation

    insertSamplePicture

  }

  /*---------------------------
     * 主表数据
     *--------------------------*/
  private def InsertInitIndustry {
    if (Industry.findAll.isEmpty) {
      Seq(
        Industry(new ObjectId("5317c0d1d4d57997ce3e6ec1"), "Hairdressing"), // 美发 Hair
        Industry(new ObjectId("5317c0d1d4d57997ce3e6ec2"), "Manicures"), // 美甲 Nail
        Industry(new ObjectId("5317c0d1d4d57997ce3e6ec3"), "Healthcare"), // 保健 Health Care
        Industry(new ObjectId("5317c0d1d4d57997ce3e6ec4"), "Cosmetic") // 整形 Face, plastic
      ).foreach(Industry.save)
    }
  }

  private def insertInitPosition {
    if (Position.findAll.isEmpty) {
      Seq(
        Position(new ObjectId("531964e0d4d57d0a43771416"), "Assistant"), // 助手
        Position(new ObjectId("531964e0d4d57d0a43771413"), "Stylist"), // 发型师
        Position(new ObjectId("531964e0d4d57d0a43771414"), "AdvancedStylist"), // 高级发型师
        Position(new ObjectId("531964e0d4d57d0a43771412"), "ChiefStylist"), // 首席发型师
        Position(new ObjectId("531964e0d4d57d0a43771411"), "Manager")).foreach(Position.save) // 店长
    }
  }

  private def insertInitIndustryAndPosition {
    if (IndustryAndPosition.findAll().isEmpty) {
      Seq(
        IndustryAndPosition(new ObjectId(), "Manager", "Hairdressing"), //美发店长
        IndustryAndPosition(new ObjectId(), "ChiefStylist", "Hairdressing"), //美发总监/主管
        IndustryAndPosition(new ObjectId(), "Consultant", "Hairdressing"), //美发顾问
        IndustryAndPosition(new ObjectId(), "AdvancedStylist", "Hairdressing"), //高级发型师
        IndustryAndPosition(new ObjectId(), "Stylist", "Hairdressing"), //发型师
        IndustryAndPosition(new ObjectId(), "Assistant", "Hairdressing"), //美发助理，？美发学员，洗头工暂时都放在助理中
        IndustryAndPosition(new ObjectId(), "Manager", "Manicures"), //美甲店长
        IndustryAndPosition(new ObjectId(), "ChiefManicurist", "Manicures"), //美甲总监/主管
        IndustryAndPosition(new ObjectId(), "Consultant", "Manicures"), //美甲顾问
        IndustryAndPosition(new ObjectId(), "AdvancedManicurist", "Manicures"), //高级美甲师
        IndustryAndPosition(new ObjectId(), "Manicurist", "Manicures"), //美甲师
        IndustryAndPosition(new ObjectId(), "Assistant", "Manicures"), //美甲助理
        IndustryAndPosition(new ObjectId(), "Manager", "Healthcare"), //休闲保健店长
        IndustryAndPosition(new ObjectId(), "Consultant", "Healthcare"), //保健顾问
        IndustryAndPosition(new ObjectId(), "AdvancedArtificer", "Healthcare"), //高级技师
        IndustryAndPosition(new ObjectId(), "Artificer", "Healthcare"), //技师
        IndustryAndPosition(new ObjectId(), "Assistant", "Healthcare"), //助理
        IndustryAndPosition(new ObjectId(), "", "Cosmetic")).foreach(IndustryAndPosition.save)
    }
  }

  private def insertInitServiceType {
    if (ServiceType.findAll.isEmpty) {
      Seq(
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d68"), "Hairdressing", "Other", "其他"),
        ServiceType(new ObjectId("53167b3cd4d5cb7e816db359"), "Hairdressing", "Supple", "柔顺"),
        ServiceType(new ObjectId("53167ae7d4d5cb7e816db355"), "Hairdressing", "Perm", "烫"),
        ServiceType(new ObjectId("53167ad9d4d5cb7e816db353"), "Hairdressing", "Care", "护理"),
        ServiceType(new ObjectId("53167aced4d5cb7e816db351"), "Hairdressing", "Dye", "染"),
        ServiceType(new ObjectId("53167abbd4d5cb7e816db34f"), "Hairdressing", "Blow", "吹"),
        ServiceType(new ObjectId("53167a91d4d5cb7e816db34d"), "Hairdressing", "Wash", "洗"),
        ServiceType(new ObjectId("5316798cd4d5cb7e816db34b"), "Hairdressing", "Cut", "剪"),



        /*ServiceType(new ObjectId("5316c443d4d57997ce3e6d69"), "Manicures", "Pen", "笔绘甲"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d71"), "Manicures", "Delineation", "勾绘甲"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d72"), "Manicures", "Airbrush", "喷绘甲"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d73"), "Manicures", "Patch", "贴片甲"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d74"), "Manicures", "Crystal", "水晶甲"),

        ServiceType(new ObjectId("5316c443d4d57997ce3e6d90"), "Healthcare", "Spa", "Spa"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d91"), "Healthcare", "Footbath", "足浴"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d92"), "Healthcare", "Massage", "按摩")).foreach(ServiceType.save)

        ServiceType(new ObjectId("5316c443d4d57997ce3e6d75"), "Manicures", "Gel", "光疗树脂甲")*/

        ServiceType(new ObjectId("5316c443d4d57997ce3e6d77"), "Manicures", "HandCare", "手足护理"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d78"), "Manicures", "Eyelashes", "美睫"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d66"), "Manicures", "Nail", "美甲"),

        ServiceType(new ObjectId("5316c443d4d57997ce3e6d90"), "Healthcare", "Spa", "Spa"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d91"), "Healthcare", "Footbath", "足浴"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d93"), "Healthcare", "Yoga", "瑜伽"),
        ServiceType(new ObjectId("5316c443d4d57997ce3e6d92"), "Healthcare", "Massage", "按摩")).foreach(ServiceType.save)
    }
  }

  private def insertInitStyleColor {
    if (StyleColor.findAll.isEmpty) {
      Seq(
        StyleColor(new ObjectId, "Hairdressing", "alternative", "另类"),
        StyleColor(new ObjectId, "Hairdressing", "red", "红色"),
        StyleColor(new ObjectId, "Hairdressing", "brown", "棕色"),
        StyleColor(new ObjectId, "Hairdressing", "chocolate", "巧克力色"),
        StyleColor(new ObjectId, "Hairdressing", "flax", "亚麻"),
        StyleColor(new ObjectId, "Hairdressing", "black", "黑色"),


        StyleColor(new ObjectId, "Manicures", "multi", "混合色"),
        StyleColor(new ObjectId, "Manicures", "clear", "透明"),
        StyleColor(new ObjectId, "Manicures", "black", "黑色"),
        StyleColor(new ObjectId, "Manicures", "red", "红色"),
        StyleColor(new ObjectId, "Manicures", "purple", "紫色"),
        StyleColor(new ObjectId, "Manicures", "blue", "蓝色"),
        StyleColor(new ObjectId, "Manicures", "green", "绿色"),
        StyleColor(new ObjectId, "Manicures", "brown", "棕色"),
        StyleColor(new ObjectId, "Manicures", "yellow", "黄色"),
        StyleColor(new ObjectId, "Manicures", "orange", "橙色"),
        StyleColor(new ObjectId, "Manicures", "gold", "金色"),
        StyleColor(new ObjectId, "Manicures", "beige", "浅褐色"),
        StyleColor(new ObjectId, "Manicures", "silver", "银色"),
        StyleColor(new ObjectId, "Manicures", "white", "白色"),
        StyleColor(new ObjectId, "Manicures", "pink", "粉红色")).foreach(StyleColor.save)
    }
  }

  private def insertInitStyleLength {
    if (StyleLength.findAll.isEmpty) {
      Seq(
        StyleLength(new ObjectId, "long", "长"),
          StyleLength(new ObjectId, "mid-length", "中长"),
          StyleLength(new ObjectId, "shoulder-length", "齐肩"),
          StyleLength(new ObjectId, "near-shoulder-length", "及肩"),
          StyleLength(new ObjectId, "short", "短"),
          StyleLength(new ObjectId, "super-short", "超短")).foreach(StyleLength.save)
    }
  }

  private def insertInitStyleImpression {
    if (StyleImpression.findAll.isEmpty) {
      Seq(
        StyleImpression(new ObjectId, "Hairdressing", "personality", "个性"),
        StyleImpression(new ObjectId, "Hairdressing", "gorgeous", "华丽"),
        StyleImpression(new ObjectId, "Hairdressing", "fresh", "清新"),
        StyleImpression(new ObjectId, "Hairdressing", "fashion", "时尚"),
        StyleImpression(new ObjectId, "Hairdressing", "sweet", "甜美"),
        StyleImpression(new ObjectId, "Hairdressing", "intellectual", "知性"),
        StyleImpression(new ObjectId, "Hairdressing", "natural", "自然"),

        StyleImpression(new ObjectId, "Manicures", "personality", "个性"),
        StyleImpression(new ObjectId, "Manicures", "gorgeous", "华丽"),
        StyleImpression(new ObjectId, "Manicures", "fresh", "清新"),
        StyleImpression(new ObjectId, "Manicures", "fashion", "时尚"),
        StyleImpression(new ObjectId, "Manicures", "sweet", "甜美"),
        StyleImpression(new ObjectId, "Manicures", "intellectual", "知性"),
        StyleImpression(new ObjectId, "Manicures", "retro", "复古"),
        StyleImpression(new ObjectId, "Manicures", "natural", "自然")).foreach(StyleImpression.save)
    }
  }

  private def insertInitStyleAmount {
    if (StyleAmount.findAll.isEmpty) {
      Seq(
        StyleAmount(new ObjectId, "few", "少"),
        StyleAmount(new ObjectId, "moderate", "适中"),
        StyleAmount(new ObjectId, "much", "多")).foreach(StyleAmount.save)
    }
  }

  private def insertInitStyleQuality {
    if (StyleQuality.findAll.isEmpty) {
      Seq(
        StyleQuality(new ObjectId, "general", "一般"),
        StyleQuality(new ObjectId, "dry", "干性"),
        StyleQuality(new ObjectId, "greasy", "油性"),
        StyleQuality(new ObjectId, "silky", "柔顺")).foreach(StyleQuality.save)
    }
  }

  private def insertInitStyleDiameter {
    if (StyleDiameter.findAll.isEmpty) {
      Seq(
        StyleDiameter(new ObjectId, "bold", "偏粗"),
        StyleDiameter(new ObjectId, "moderate", "适中"),
        StyleDiameter(new ObjectId, "thin", "偏细")).foreach(StyleDiameter.save)
    }
  }

  private def insertInitFaceShape {
    if (FaceShape.findAll.isEmpty) {
      Seq(
        FaceShape(new ObjectId, "diamond-face", "菱形"),
        FaceShape(new ObjectId, "pointed-face", "尖脸"),
        FaceShape(new ObjectId, "square-face", "方脸"),
        FaceShape(new ObjectId, "long-face", "长脸"),
        FaceShape(new ObjectId, "round-face", "圆脸"),
        FaceShape(new ObjectId, "oval-face", "椭圆脸")).foreach(FaceShape.save)
    }
  }

  private def insertInitSocialScene {
    if (SocialScene.findAll.isEmpty) {
      Seq(
        SocialScene(new ObjectId, "Hairdressing", "others", "其它"),
        SocialScene(new ObjectId, "Hairdressing", "T-stage", "T台"),
        SocialScene(new ObjectId, "Hairdressing", "street", "街拍"),
        SocialScene(new ObjectId, "Hairdressing", "star", "明星"),
        SocialScene(new ObjectId, "Hairdressing", "brief", "简约"),
        SocialScene(new ObjectId, "Hairdressing", "evening-wear", "晚装"),

        SocialScene(new ObjectId, "Manicures", "date", "约会"),
        SocialScene(new ObjectId, "Manicures", "sport", "运动"),
        SocialScene(new ObjectId, "Manicures", "party", "聚会"),
        SocialScene(new ObjectId, "Manicures", "office", "办公室"),
        SocialScene(new ObjectId, "Manicures", "bridal", "婚礼")).foreach(SocialScene.save)
    }
  }

  private def insertInitStyleMaterial {
    if (StyleMaterial.findAll.isEmpty) {
      Seq(
        StyleMaterial(new ObjectId, "Manicures", "fruitSlices", "水果片"),
        StyleMaterial(new ObjectId, "Manicures", "peucine", "树脂"),
        StyleMaterial(new ObjectId, "Manicures", "metal", "金属"),
        StyleMaterial(new ObjectId, "Manicures", "bridal", "玉石"),
        StyleMaterial(new ObjectId, "Manicures", "pearl", "珍珠"),
        StyleMaterial(new ObjectId, "Manicures", "rhinestone", "水钻")).foreach(StyleMaterial.save)
    }
  }

  private def insertInitStyleBase {
    if (StyleBase.findAll.isEmpty) {
      Seq(
        StyleBase(new ObjectId, "Manicures", "foot", "足"),
        StyleBase(new ObjectId, "Manicures", "hand", "手")).foreach(StyleBase.save)
    }
  }

  private def insertInitSex {
    if (Sex.findAll.isEmpty) {
      Seq(
        Sex(new ObjectId, "female"),
        Sex(new ObjectId, "male")).foreach(Sex.save)
    }
  }

  private def insertInitAgeGroup {
    if (AgeGroup.findAll.isEmpty) {
      Seq(
        AgeGroup(new ObjectId, "fifty-five--", "55以上"),
        AgeGroup(new ObjectId, "forty-five--fifty-five", "45—55"),
        AgeGroup(new ObjectId, "thirty-five--forty-five", "35—45"),
        AgeGroup(new ObjectId, "twenty-five--thirty-five", "25—35"),
        AgeGroup(new ObjectId, "one--twenty-five", "15—25"),
        AgeGroup(new ObjectId, "one--fifteen", "1—15")).foreach(AgeGroup.save)
    }
  }

  private def insertInitStylePicDescription {
    if (StylePicDescription.findAll.isEmpty) {
      Seq(
        StylePicDescription(new ObjectId, "FASHION", "FASHION"),
        StylePicDescription(new ObjectId, "SIDE", "侧面"),
        StylePicDescription(new ObjectId, "BACK", "背面"),
        StylePicDescription(new ObjectId, "FRONT", "正面")).foreach(StylePicDescription.save)
    }
  }

  private def insertInitBlogCategory {
    if (BlogCategory.findAll.isEmpty) {
      Seq(
        BlogCategory(new ObjectId("53195fb4a89e175858abce84"), "其他", true),
        BlogCategory(new ObjectId("53195fb4a89e175858abce87"), "喜欢", true),
        BlogCategory(new ObjectId("53195fb4a89e175858abce86"), "工作", true),
        BlogCategory(new ObjectId("53195fb4a89e175858abce85"), "个人", true),
        BlogCategory(new ObjectId("53195fb4a89e175858abce83"), "健康养生", true),
        BlogCategory(new ObjectId("53195fb4a89e175858abce82"), "美容资讯", true)).foreach(BlogCategory.save)
    }
  }

  private def insertInitContMethodType {
    if (ContMethodType.findAll.isEmpty) {
      Seq(
        ContMethodType(new ObjectId, "line", "line"),
        ContMethodType(new ObjectId, "YY", "YY语音"),
        ContMethodType(new ObjectId, "fetion", "Fetion"),
        ContMethodType(new ObjectId, "MSN", "MSN"),
        ContMethodType(new ObjectId, "skype", "Skype"),
        ContMethodType(new ObjectId, "we-chat", "微信"),
        ContMethodType(new ObjectId, "QQ", "QQ")).foreach(ContMethodType.save)
    }
  }

  private def insertInitSearchByLengthForF {
    if (SearchByLengthForF.findAll.isEmpty) {
      Seq(
        SearchByLengthForF(new ObjectId, "female", "super-short", new ObjectId, "女-超短"),
        SearchByLengthForF(new ObjectId, "female", "short", new ObjectId, "女-短"),
        SearchByLengthForF(new ObjectId, "female", "near-shoulder-length", new ObjectId, "女-及肩"),
        SearchByLengthForF(new ObjectId, "female", "shoulder-length", new ObjectId, "女-齐肩"),
        SearchByLengthForF(new ObjectId, "female", "mid-length", new ObjectId, "女-中长"),
        SearchByLengthForF(new ObjectId, "female", "long", new ObjectId, "女-长")).foreach(SearchByLengthForF.save)
    }
  }

  private def insertInitSearchByLengthForM {
    if (SearchByLengthForM.findAll.isEmpty) {
      Seq(
        SearchByLengthForM(new ObjectId, "male", "super-short", new ObjectId, "男-超短"),
        SearchByLengthForM(new ObjectId, "male", "short", new ObjectId, "男-短"),
        SearchByLengthForM(new ObjectId, "male", "near-shoulder-length", new ObjectId, "男-及肩"),
        SearchByLengthForM(new ObjectId, "male", "shoulder-length", new ObjectId, "男-齐肩"),
        SearchByLengthForM(new ObjectId, "male", "mid-length", new ObjectId, "男-中长"),
        SearchByLengthForM(new ObjectId, "male", "long", new ObjectId, "男-长")).foreach(SearchByLengthForM.save)
    }
  }

  private def insertInitSearchByImpression {
    if (SearchByImpression.findAll.isEmpty) {
      Seq(
        SearchByImpression(new ObjectId, "female", "natural", new ObjectId, "女-自然"),
        SearchByImpression(new ObjectId, "female", "intellectual", new ObjectId, "女-知性"),
        SearchByImpression(new ObjectId, "female", "fashion", new ObjectId, "女-时尚"),
        SearchByImpression(new ObjectId, "female", "fresh", new ObjectId, "女-清新"),
        SearchByImpression(new ObjectId, "female", "sweet", new ObjectId, "女-甜美"),
        SearchByImpression(new ObjectId, "female", "gorgeous", new ObjectId, "女-华丽"),
        SearchByImpression(new ObjectId, "female", "personality", new ObjectId, "女-个性")).foreach(SearchByImpression.save)
    }
  }

  private def insertInitDefaultLog {
    if (DefaultLog.findAll.isEmpty) {
      Seq(
        DefaultLog(new ObjectId, new ObjectId)).foreach(DefaultLog.save)
    }
  }

  private def insertInitPriceRange {
    if (PriceRange.findAll.isEmpty) {
      Seq(
        PriceRange(new ObjectId, 0, 20, "Hairdressing"),
        PriceRange(new ObjectId, 21, 50, "Hairdressing"),
        PriceRange(new ObjectId, 51, 100, "Hairdressing"),
        PriceRange(new ObjectId, 101, 1000, "Hairdressing"),
        PriceRange(new ObjectId, 1001, 100000, "Hairdressing"),

        PriceRange(new ObjectId, 0, 50, "Manicures"),
        PriceRange(new ObjectId, 51, 100, "Manicures"),
        PriceRange(new ObjectId, 101, 200, "Manicures"),
        PriceRange(new ObjectId, 201, 500, "Manicures"),
        PriceRange(new ObjectId, 501, 1000, "Manicures"),
        PriceRange(new ObjectId, 1001, 100000, "Manicures"),

        PriceRange(new ObjectId, 0, 50, "Healthcare"),
        PriceRange(new ObjectId, 51, 100, "Healthcare"),
        PriceRange(new ObjectId, 101, 200, "Healthcare"),
        PriceRange(new ObjectId, 201, 500, "Healthcare"),
        PriceRange(new ObjectId, 501, 1000, "Healthcare"),
        PriceRange(new ObjectId, 1001, 100000, "Healthcare")).foreach(PriceRange.save)
    }
  }

  private def insertInitSeatNums {
    if (SeatNums.findAll.isEmpty) {
      Seq(
        SeatNums(0, 5),
        SeatNums(6, 10),
        SeatNums(11, 20),
        SeatNums(21, 100)).foreach(SeatNums.save)
    }
  }

  private def insertInitBrand {
    if (Brand.findAll.isEmpty) {
      Seq(
        Brand(new ObjectId(), "Hairdressing", "悦美月容吧", 5, ""),
        Brand(new ObjectId(), "Hairdressing", "千美千寻吧", 4, ""),
        Brand(new ObjectId(), "Hairdressing", "忆荣吧", 3, ""),
        Brand(new ObjectId(), "Hairdressing", "虞美人", 4, ""),

        Brand(new ObjectId(), "Manicures", "浪漫满屋", 3, ""),
        Brand(new ObjectId(), "Manicures", "会美", 5, ""),
        Brand(new ObjectId(), "Manicures", "驻容坊", 4, ""),

        Brand(new ObjectId(), "Healthcare", "圣康Spa", 3, ""),
        Brand(new ObjectId(), "Healthcare", "汇贤足浴", 5, ""),
        Brand(new ObjectId(), "Healthcare", "固原中式按摩", 4, "")).foreach(Brand.save)
    }
  }

  private def insertInitHotestkeyword {
    if (HotestKeyword.findAll.isEmpty) {
      Seq(
        HotestKeyword(new ObjectId, "烫发", "HairSalon", 1, true),
        HotestKeyword(new ObjectId, "男士", "HairSalon", 2, true),
        HotestKeyword(new ObjectId, "千美", "HairSalon", 3, true),
        HotestKeyword(new ObjectId, "高新区", "HairSalon", 4, true),
        HotestKeyword(new ObjectId, "苏州", "HairSalon", 5, true),
        HotestKeyword(new ObjectId, "竹园路", "HairSalon", 6, true),
        HotestKeyword(new ObjectId, "剪", "HairSalon", 7, true),
        HotestKeyword(new ObjectId, "洗", "HairSalon", 8, true),
        HotestKeyword(new ObjectId, "美丽", "HairSalon", 9, true),
        HotestKeyword(new ObjectId, "悦美", "HairSalon", 9, true),
        HotestKeyword(new ObjectId, "悦美纤美", "hairSalon", 1, true),
        HotestKeyword(new ObjectId, "悦美月容", "hairSalon", 1, true),
        HotestKeyword(new ObjectId, "悦 美  彩", "hairSalon", 1, true),
        HotestKeyword(new ObjectId, "剪发", "hairSalon", 1, true),
        HotestKeyword(new ObjectId, "剪头发", "hairSalon", 1, true),
        HotestKeyword(new ObjectId, "洗剪吹", "hairSalon", 1, true),
        HotestKeyword(new ObjectId, "悦容美发", "hairSalon", 1, true)).foreach(HotestKeyword.save)
    }

  }

  private def insertBaseInfo {
    if (Info.findAll.isEmpty) {
      Seq(
        //         Info(new ObjectId(), "巴黎欧莱雅-新的市场新的挑战", "对于传统意义上的领导品牌,一个事实正摆在眼前,传统营销形式所积累的受众正在慢慢老去,最受数字媒体所影响的年轻一代正在成为主流消费群体,品牌营销该何去何从？", new ObjectId, new ObjectId, new Date, 1, true),
        Info(new ObjectId(), "巴黎欧莱雅-新的市场新的挑战", "对于传统意义上的领导品牌,一个事实正摆在眼前,传统营销形式所积累的受众正在慢慢老去,最受数字媒体所影响的年轻一代正在成为主流消费群体,品牌营销该何去何从？", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "雅诗兰黛'鲜亮焕采'", "'鲜亮焕采'系列的全新单品惊艳四方,给到场的媒体嘉宾带来独特的护肤体验。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "油状护发到底该怎么用", "随着女性对于头发护理的愈加重视,越来越多的新型护发产品出现在市面上,尤其是具有免洗功效的便携护发油。所谓护发油是给头发以充分保湿和营养的头发护理产品,并且能够有效防止头发白天收到外界紫外线和空气污染的侵害,但是你真的用对了吗？", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "复刻麻豆气质扎发", "不少女明星都有自己的一套扎发造型。今天小编就为大家挑选了三款发型扎发,一起复刻她们的扎发造型,展现出独有的俏丽风情。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "显瘦立体 清爽短发+侧编刘海", "忙碌工作了一周,到了周末当然要好好的放松一下。和闺蜜约个会、聚个餐想必是最能让自己身心愉悦的事。可即便是最休闲的周末,妆发上也将就不得！今天教给你小有讲究的清爽短发发型。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "简单SPA解决常见头皮问题", "头皮就像我们的皮肤一样需要精心的呵护,不管每天你都在忙碌的做任何事情,可以会忽略了对头发的护理,导致最后拥有一头看了就厌烦的头发,我们现在就介绍一种简单快捷,只需15分钟就能帮助你护发的方法,只要天天坚持给秀发SPA,秀发立即焕然一新哦！", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "不失庄重的随性编发", "职场中慢慢历练的你总想给人种成熟干练的感觉,除了个人工作能力外,妆发造型也是可以为你加分的一部分。小编为工作中的你介绍职场超match发型。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 1, true),
        Info(new ObjectId(), "非手术整容已成为整形美容新宠", "美丽永远是求美者永恒的追求,这种追求促进了整形美容行业的发展。如今,整形美容机构林立,整形观念层出不穷,整容的手段更是日新月异。面对那名目繁多的整形美容项目,许多求美者越来越聪明,选择非手术无创整形实现自己的美丽梦。非手术整容成为时下整形美容新宠。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        Info(new ObjectId(), "产后修复整形术成为未来几年流行趋势", "如果细心观察会发现,最近一两年孕妇特别多,而且绝大部分是80后的美丽孕妈咪。爱美的80后群体已经到了成家立业的阶段,生孩子也在计划之中。80后爱美女士们都希望自己成为“辣妈”,所以未来几年内,产后修复整形术会继续流行。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        Info(new ObjectId(), "化妆同时护肤的7个小技巧", "有很多人认为化妆品是毁容的产品,尤其是粉底液、BB霜一类的底妆产品,会致痘,堵塞毛孔,导致各种皮肤问题。但其实这样的说法并不是完全正确的,在了解自己肤质的情况下选对的产品使用,不但可以帮你修饰瑕疵,甚至对皮肤还会有保护的作用！", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        Info(new ObjectId(), "OL久坐不动 如何减掉背部肉肉", "为什么大腿的肉肉这么难减？用什么方法才能减小肚子？你也有这样的减肥烦恼吗？女人频道减肥沙龙特别为苦恼的姐妹们推出了“减肥帮帮团”栏目。来自减肥沙龙的减肥顾问,随时准备帮你回答关于减肥的所有问题哦。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        Info(new ObjectId(), "双眼皮修复手术 5种眼部问题的修复方法", "随着整形医疗科技的逐步成熟,以及人们对整形美容业心里认知度的逐步提高,如今“割双眼皮”在大家看来已是微不足道的外科小手术。虽然手术并不复杂,但由于患者个体差异以及主刀医生审美观念的不同,做出来的双眼皮效果并不能保证让每一个患者都感到完全称心实意。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        Info(new ObjectId(), "谷歌眼镜新应用 整容手术好帮手", "在纽约的一家美容医院工作的整容医生Dr. Ramtin Kassir表示在他的工作中,Google Glass在他的工作中能起到非常实用的帮助。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        Info(new ObjectId(), "医美四招去疤痕 再现无暇肌肤", "皮肤创伤、烧伤、溃疡等愈合后留下的痕迹被称为疤痕,有些疤痕深深地留在了皮肤上很难去除,这同时也给人们内心增加了一道伤痕。疤痕修复手术可以去除疤痕,常见的疤痕手术有植皮手术、磨削去疤痕、切除缝合术及皮肤扩张器四种手术,不同的疤痕情况要选择不同的手术方式。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 2, true),
        // type 6
        Info(new ObjectId(), "劳动节促销", "劳动节,给自己放松放松,别太辛苦了。", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 6, true),
        Info(new ObjectId(), "新店开业", "新店开业,大酬宾,欢迎新顾客光顾本店", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 6, true),
        Info(new ObjectId(), "半价优惠", "欢迎新老顾客光临本店,本店现在正在举行半价优惠活动,期待您的光临！", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 6, true),
        Info(new ObjectId(), "3月8日大促销", "妇女节,给自己'变身'吧,让心爱的他/她眼前一亮", new ObjectId, List(new OnUsePicture(new ObjectId, "logo", Some(1), None)), new Date, 6, true),

        Info(new ObjectId(), "本站服务条款的确认和接纳", "本站的各项电子服务的所有权和运作权归本站。本站提供的服务将完全按照其发布的服务条款和操作规则严格执行。用户同意所有服务条款并完成注册程序,才能成为本站的正式用户。", new ObjectId, Nil, new Date, 3, true),
        Info(new ObjectId(), "服务简介", "基于本站所提供的网络服务的重要性,用户应同意：<br>(1)提供详尽、准确的个人资料。<br>(2)不断更新注册资料,符合及时、详尽、准确的要求。<br>本站保证不公开用户的真实姓名、地址、电子邮箱和联系电话等用户信息, 除以下情况外：<br>(1)用户授权本站透露这些信息。<br>(2)相应的法律及程序要求本站提供用户的个人资料。", new ObjectId, Nil, new Date, 3, true),
        Info(new ObjectId(), "服务条款的修改", "本站将可能不定期的修改本用户协议的有关条款,一旦条款及服务内容产生变动,本站将会在重要页面上提示修改内容。", new ObjectId, Nil, new Date, 3, true),
        Info(new ObjectId(), "通告", "所有发给用户的通告都可通过重要页面的公告或电子邮件或常规的信件传送。用户协议条款的修改、服务变更、或其它重要事件的通告都会以此形式进行。", new ObjectId, Nil, new Date, 3, true),
        Info(new ObjectId(), "网络服务内容的所有权", "本站定义的网络服务内容包括：文字、软件、声音、图片、录象、图表、广告中的全部内容；电子邮件的全部内容；本站为用户提供的其它信息。所有这些内容受版权、商标、标签和其它财产所有权法律的保护。所以,用户只能在本站和广告商授权下才能使用这些内容,而不能擅自复制、再造这些内容、或创造与内容有关的派生产品。本站所有的文章版权归原文作者和本站共同所有,任何人需要转载本站的文章,必须征得原文作者和本站授权。", new ObjectId, Nil, new Date, 3, true),
        Info(new ObjectId(), "法律管辖和适用", "本协议的订立、执行和解释及争议的解决均应适用中国法律。", new ObjectId, Nil, new Date, 3, true),
        Info(new ObjectId(), "用户守则", "用户须提供完整、准确、真实的信息,并在发生变化时及时更新。若用户提供任何错误、不实、过时或不完整的资料,并为美范网所确知；或者美范网有合理理由怀疑前述资料为错误、不实、过时或不完整,美范网有权暂停或终止用户的帐号,并拒绝现在或将来使用本服务的全部或一部分。<br>用户同意遵守《中华人民共和国保守国家秘密法》、《中华人民共和国计算机信息系统安全保护条例》、《计算机软件保护条例》等有关计算机及互联网规定的法律和法规、实施办法。在任何情况下,美范网合理地认为用户的行为可能违反上述法律、法规,美范网可以在任何时候,不经事先通知终止向该用户提供服务。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "禁止用户使用下列名称进行注册：", "（1）国际、国家、地方政府机构及其他机构的名称；<br>（2）在社会上具有一定影响力和知名度的影星、歌星、体育明星等公众人物的真实姓名、艺名等名称；<br>（3）党和国家领导人的真实姓名、字、号、笔名等；<br>（4）与在先注册的用户名称相同或近似的名称；<br>（5）含有攻击性、歧视性、侮辱性、色情、猥亵、政治类内容的名称；<br>（6）上述各类名称的谐音及相似字形。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "禁止用户从事下列行为：", "（1）反对宪法所确定的基本原则的；<br>（2）危害国家安全,泄露国家秘密,颠覆国家政权,破坏国家统一的；<br>（3）损害国家荣誉和利益的；<br>（4）含有法律、行政法规禁止的其他内容的。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "责任说明", "基于技术和不可预见的原因而导致的服务中断,或者因会员的非法操作而造成的损失,美范网不负责任。会员应当自行承担一切因自身行为而直接或者间接导致的民事或刑事法律责任。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "版权说明", "任何会员接受本注册协议,即表明该用户主动将其在任何时间段在本站发表的任何形式的信息的著作财产权,包括并不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及应当由著作权人享有的其他可转让权利无偿独家转让给美范网运营商所 有,同时表明该会员许可美范网有权利就任何主体侵权而单独提起诉讼,并获得全部赔偿。本协议已经构成《著作权法》第二十五条所规定的书面协议,其效力及于用户在美范网发布的任何受著作权法保护的作品内容,无论该内容形成于本协议签订前还是本协议签订后。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "侵权者政策", "对于被视为侵犯他人知识产权的任何用户,美范网可自行决定限制其对本网站的访问或终止其帐户。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "通知", "美范网向用户发出的通知,采用电子邮件、手机短信、页面公告或常规信件的形式。服务条款的修改及其他事项的告知(包含但不限于注册结果通知、预订结果通知、手机验证通知等),美范网将会以上述形式进行通知。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "其他", "本协议条款在用户接受时对用户生效。", new ObjectId, Nil, new Date, 4, true),
        Info(new ObjectId(), "用户隐私制度", "尊重用户个人隐私是本站的一项基本政策。所以,本站一定不会在未经合法用户授权时公开、编辑或透露其注册资料及保存在本站中的非公开内容。", new ObjectId, Nil, new Date, 5, true),
        Info(new ObjectId(), "用户的帐号,密码和安全性", "用户一旦注册成功,成为本站的合法用户,将得到一个密码和用户名。您可随时根据指示改变您的密码。用户需谨慎合理的保存、使用用户名和密码。用户若发现任何非法使用用户帐号或存在安全漏洞的情况,请立即通知本站和向公安机关报案。", new ObjectId, Nil, new Date, 5, true),
        Info(new ObjectId(), "对用户信息的存储和限制", "如果用户违背了国家法律法规规定或本协议约定,本站有视具体情形中止或终止对其提供网络服务的权利。", new ObjectId, Nil, new Date, 5, true),
        Info(new ObjectId(), "保护会员隐私权", "本协议所称之会员隐私包括被法律确认为隐私内容,并符合下述范围的信息：<br>您注册美范网时,跟据网站要求提供的个人信息<br>在您使用美范网服务、参加网站活动、或访问网站网页时,网站自动接收并记录的您浏览器上的服务器数据,包括但不限于IP地址、网站Cookie中的资料及您要求取用的网页记录<br>美范网不会向任何人出售或出借您的个人信息,除非事先得到您的许可", new ObjectId, Nil, new Date, 5, true)).foreach(Info.save)
    }
  }

  private def insertBaseNotice {
    if (Notice.findAll.isEmpty) {
      Seq(
        Notice(new ObjectId(), "网站公告", "网站公告板块主要显示本网站的新闻、公告、通知等信息。", "admin01", new Date, true),
        Notice(new ObjectId(), "网站规划", "本站第一阶段于2014-04-20正式上线,本阶段网站建设的重点放在网站功能的完整性上,后期会再针对网站的预约功能等其他问题做进一步的完善。敬请期待~", "admin01", new Date, true),
        Notice(new ObjectId(), "网站目标", "一站式信息服务平台,最前沿美容美发咨询的汇集地！", "admin01", new Date, true)).foreach(Notice.save)
    }
  }


  private def insertBaseQuestion {



    if (Question.findAll.isEmpty) {
      Seq(
        Question(new ObjectId(), "发型师如何注册", """<p>
    （1）发型师首先注册为<strong>普通用户</strong>,获取登录账号；
</p>
<p>
    （2）利用以上获取的账号登录,点击页面右上角的<span style="text-decoration: underline;"><span id="_baidu_bookmark_start_35" style="display: none; line-height: 0px;">‍</span></span><span id="_baidu_bookmark_start_37" style="display: none; line-height: 0px;">‍</span><span style="color: rgb(217, 150, 148);">“我的美范”</span><span id="_baidu_bookmark_end_38" style="display: none; line-height: 0px;">‍</span><span style="text-decoration: underline;"><span id="_baidu_bookmark_end_36" style="display: none; line-height: 0px;">‍</span><span id="_baidu_bookmark_start_29" style="display: none; line-height: 0px;">‍</span></span><span id="_baidu_bookmark_start_31" style="display: none; line-height: 0px;">‍</span>至<span id="_baidu_bookmark_end_32" style="display: none; line-height: 0px;">‍</span><span style="text-decoration: underline;"><span id="_baidu_bookmark_end_30" style="display: none; line-height: 0px;">‍</span><span id="_baidu_bookmark_start_25" style="display: none; line-height: 0px;">‍</span></span><span id="_baidu_bookmark_start_27" style="display: none; line-height: 0px;">‍</span><strong>个人主页</strong>中；
</p>
<p>
    （3）在<strong>个人主页</strong>中点击左下角的<span style="color: rgb(217, 150, 148);">“申请为技师”</span>；
</p>
<p>
    （4）填写相应的信息,其中<strong>店铺账号</strong>及为您所在的店铺的账号,最后<strong>提交申请</strong>,所在店铺将收到您的申请信息,等待接收即可；
</p>
<p>
    （5）等待过程中<strong style="font-size: 12.5px;">个人主页</strong><span style="font-size: 12.5px;">中原先<span style="color: rgb(217, 150, 148); line-height: 22.5px;">“申请为技师”<span style="font-size: 12.5px; color: rgb(0, 0, 0); line-height: 22.5px;">将会变成“<span style="font-size: 12.5px; color: rgb(217, 150, 148); line-height: 22.5px;">取消当前申请”<span style="font-size: 12.5px; color: rgb(0, 0, 0); line-height: 22.5px;">,若发现先前提交的信息有误或者终止此次申请,可点击该按钮；</span></span></span></span></span>
</p>
<p>
    （6）等待时<strong>个人主页</strong>中若<span style="color: rgb(217, 150, 148); line-height: 22.5px;">取消当前申请”<span style="color: rgb(0, 0, 0); line-height: 22.5px;">变成</span><span style="color: rgb(217, 150, 148); line-height: 22.5px;">“申请为技师”,<span style="color: rgb(0, 0, 0); line-height: 22.5px;">说明您先前提交申请被欲申请的店铺拒绝；</span></span></span>
</p>
<p>
    <span style="color: rgb(217, 150, 148); line-height: 22.5px;"><span style="color: rgb(217, 150, 148); line-height: 22.5px;"><span style="color: rgb(0, 0, 0); line-height: 22.5px;">（7）若您提出的申请被店铺接收,您的个人主页左侧展示了您所在的店铺,您的发型等相关信息,并可编辑您的发型等作品。</span></span></span>
</p>
<p>
    （8）若想和当前所在店铺解除劳动关系,可点击<span style="color: rgb(217, 150, 148);">“我的店铺</span>”图标,点击右上角的<span style="color: rgb(217, 150, 148);">“离开店铺”</span>即可,此时在您的<strong>个人主页</strong>的左侧原先“<span style="color: rgb(217, 150, 148);">我的店铺”</span>区域将变为“<span style="color: rgb(217, 150, 148);">来自店铺的邀请”</span>,可重新<span style="text-decoration: underline;"><em>提出申请</em></span>或者<span style="text-decoration: underline;"><em>接受店铺的邀请</em></span>；<br/>
</p>""", new Date, 1, true),
        Question(new ObjectId(), "店铺用户如何注册", """<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);">(1)店铺账号注册：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; 店铺用户需先在网站上的“<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">店铺注册</span>”处注册,注册完成后成为准店铺状态。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp;&nbsp;<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">注册须知</span>：
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;a):店铺账号,店铺名称一经注册,不能修改。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; b):注册完成后,会自动跳转到登录页面,用户可以根据注册的账户ID和密码登录到该账户的信息管理页面。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);">(2)店铺信息完善：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; 进入店铺后台,点击店铺管理菜单中的“<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">店铺申请</span>”按钮,进入店铺申请页面。请按照要求填写店铺信息并完善。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp;&nbsp;<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">店铺信息完善手册</span>：
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; a):店铺logo：店铺logo可以在左上角部分点击“<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">更换头像</span>”按钮来更换logo,也可以在图片上传中更换logo。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; b):基本信息：用户可在基本信息页面填写店铺基本信息（此项目完善后可开通店铺浏览,店铺检索等功能,无预约功能）。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; c):详细信息：请根据您的店铺实际情况,填写店铺详细信息。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; d):图片上传：用户可在该页面上传图片,包括logo,展示图片,氛围图片。（c,d项目完成后,可开通预约功能）
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);">(3)店铺申请,审核：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; 店铺信息填写完成后,在该页面下方点击”<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">店铺申请</span>“,等待网站管理平台认证通过。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);">(4)店铺上线：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; 网站管理平台认证通过后,店铺即正式上线。
</p>
<p>
    <em style="color: rgb(255, 0, 0); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;"><strong><br/></strong></em>
</p>""", new Date, 1, true),
        Question(new ObjectId(), "如何根据详细条件检索", "请这样根据详细条件检索：......", new Date, 1, true),
        Question(new ObjectId(), "预约后想取消", "请按以下步骤取消：......", new Date, 1, true),
        Question(new ObjectId(), "如何获取店铺权限", """<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);">(1)店铺一级权限：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a):店铺后台管理：<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">店铺管理模块</span>,<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">店铺信息管理模块</span>,<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">发型管理模块</span>,<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">技师管理模块</span>。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b):店铺前台显示：根据后台信息,显示在店铺页面,并且可被访问。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c):检索功能：该店铺可以被其他用户检索并查看。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp; &nbsp; (该权限在填完基本信息后即可拥有。当您的信息管理,发型管理,技师管理都完善后,将自动拥有二级权限。)
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="color: rgb(0, 0, 0);">(2)店铺二级权限 ：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a):店铺一级权限的全部内容。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b):开放<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">服务管理模块</span>,优惠券管理模块,<span style="margin: 0px; padding: 0px; color: rgb(227, 108, 9);">菜单管理模块</span>&nbsp;&nbsp;
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(该权限在完善信息管理模块,发型管理模块,技师管理模块之后拥有,当您的服务管理,优惠券管理,菜单管理都完善后,将自动拥有三级权限) &nbsp; &nbsp;
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="color: rgb(0, 0, 0);">(3)店铺三级权限：</span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a):店铺一级权限全部功能 &nbsp;。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b):店铺二级权限全部功能 &nbsp;。
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <span style="margin: 0px; padding: 0px; font-size: 12.5px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c):开放</span><span style="margin: 0px; padding: 0px; font-size: 12.5px; color: rgb(227, 108, 9);">预约管理模块</span><span style="margin: 0px; padding: 0px; font-size: 12.5px;">,</span><span style="margin: 0px; padding: 0px; font-size: 12.5px; color: rgb(227, 108, 9);">评论管理模块</span><span style="margin: 0px; padding: 0px; font-size: 12.5px;">,</span><span style="margin: 0px; padding: 0px; font-size: 12.5px; color: rgb(227, 108, 9);">动态管理模块</span>
</p>
<p>
    <span style="margin: 0px; padding: 0px; font-size: 12.5px; color: rgb(227, 108, 9);"><br/></span>
</p>
<p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &#39;Microsoft YaHei&#39;, Arial, Helvetica, &#39;MS PGothic&#39;, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;">
    <br/>
</p>""", new Date, 1, true)).foreach(Question.save)

    }
  }

  private def insertBaseMessage {
    if (Message.findAll.isEmpty) {
      Seq(
        Message(new ObjectId("531964e0d4d57d0a43771810"), "欢迎加入美范网！", "欢迎你！！！！！！", new Date),
        Message(new ObjectId("531964e0d4d57d0a43771811"), "您有新粉丝关注你了！！", "您有新粉丝关注你了,快去查看吧！！！", new Date),
        Message(new ObjectId("531964e0d4d57d0a43771812"), "您有新的消息！", "新消息！", new Date)).foreach(Message.save)
    }
  }
  /*---------------------------
   * Master相关图片
   *--------------------------*/
  private def insertInitPicture {
    if (!SearchByImpression.findAll.isEmpty) {
      if (Image.fuzzyFindByName("impression-female").isEmpty) {
        // save picture of style
        val stylefile = new File(play.Play.application().path() + "/public/images/style/styleForImpression")
        val stylefiles = Image.listFilesInFolder(stylefile)
        for ((styf, index) <- stylefiles.zipWithIndex) {
          if (index < SearchByImpression.findAll.toList.length) {
            val styleImgId = Image.save(styf)
            val searchByImpression = SearchByImpression.findAll.toList(index)
            SearchByImpression.saveSearchByImpressionImage(searchByImpression, styleImgId)
          }
        }
      }
    }

    if (!SearchByLengthForF.findAll.isEmpty) {
      if (Image.fuzzyFindByName("length-female").isEmpty) {
        // save picture of style
        val stylefile = new File(play.Play.application().path() + "/public/images/style/styleForFemale")
        val stylefiles = Image.listFilesInFolder(stylefile)
        for ((styf, index) <- stylefiles.zipWithIndex) {
          if (index < SearchByLengthForF.findAll.toList.length) {
            val styleImgId = Image.save(styf)
            val searchByLengthForF = SearchByLengthForF.findAll.toList(index)
            SearchByLengthForF.saveSearchByLengthForFImage(searchByLengthForF, styleImgId)
          }
        }
      }
    }

    if (!SearchByLengthForM.findAll.isEmpty) {
      if (Image.fuzzyFindByName("length-male").isEmpty) {
        // save picture of style
        val stylefile = new File(play.Play.application().path() + "/public/images/style/styleForMale")
        val stylefiles = Image.listFilesInFolder(stylefile)
        for ((styf, index) <- stylefiles.zipWithIndex) {
          if (index < SearchByLengthForM.findAll.toList.length) {
            val styleImgId = Image.save(styf)
            val searchByLengthForM = SearchByLengthForM.findAll.toList(index)
            SearchByLengthForM.saveSearchByLengthForMImage(searchByLengthForM, styleImgId)
          }
        }
      }
    }


    if (!DefaultLog.findAll.isEmpty) {
      if (Image.fuzzyFindByName("defaultLog").isEmpty) {
        val defaultLogFile = new File(play.Play.application().path() + "/public/images/user/dafaultLog")
        val defaultLogFiles = Image.listFilesInFolder(defaultLogFile)
        for ((defaultLog, index) <- defaultLogFiles.zipWithIndex) {
          if (index < DefaultLog.findAll.toList.length) {
            val defaultLogImgId = Image.save(defaultLog)
            val defaultLogImg = DefaultLog.findAll.toList(index)
            DefaultLog.saveLogImg(defaultLogImg, defaultLogImgId)
          }
        }
      }
    }
  }

  /*---------------------------
   * 基础数据相关图片
   *--------------------------*/
  private def insertBasePicture {

    if (!Info.findAll.isEmpty) {
      if (Image.fuzzyFindByName("info").isEmpty) {
        // save picture of info
        val infofile = new File(play.Play.application().path() + "/public/images/info")
        val infofiles = Image.listFilesInFolder(infofile)
        for ((infof, index) <- infofiles.zipWithIndex) {
          if (index < Info.findAll.toList.length) {
            val infoImgId = Image.save(infof)
            val info = Info.findAll.toList(index)
            Info.updateInfoImage(info, infoImgId)
          }
        }
      }
    }

  }

  /*---------------------------
   * 测试数据相关图片
   *--------------------------*/
  private def insertSamplePicture {

    if (!Style.findAll.isEmpty) {
      if (Image.fuzzyFindByName("style").isEmpty) {
        //save picture of salon
        val stylefile = new File(play.Play.application().path() + "/public/images/style/style")
        val stylefiles = Image.listFilesInFolder(stylefile)
        var imgList: List[ObjectId] = Nil
        for ((styf, index) <- stylefiles.zipWithIndex) {
          if (index < Style.findAll.length * 3) {
            val styleImgId = Image.save(styf)
            imgList :::= List(styleImgId)
            //                    imgList = imgList.reverse
            if (index % 3 == 2) {
              val style = Style.findAll.toList(index / 3)
              Style.updateStyleImage(style, imgList)
              imgList = Nil
            }

          }
        }
      }
    }

    if (!Stylist.findAll.isEmpty) {
      if (Image.fuzzyFindByName("stylist").isEmpty) {
        // save picture of stylist
        val stylistfile = new File(play.Play.application().path() + "/public/images/stylist/stylist")
        val stylistfiles = Image.listFilesInFolder(stylistfile)
        for ((f, index) <- stylistfiles.zipWithIndex) {
          if (index < Stylist.findAll.length) {
            val stylistImgId = Image.save(f)
            val stylist = Stylist.findAll.toList(index)
            Stylist.updateImages(stylist, stylistImgId)
          }
        }
      }
    }

    if (!Salon.findAll.isEmpty) {
      if (Image.fuzzyFindByName("salon").isEmpty) {
        //save picture of salon
        val logofile = new File(play.Play.application().path() + "/public/images/store/logo")
        val logofiles = Image.listFilesInFolder(logofile)
        for ((l, index) <- logofiles.zipWithIndex) {
          if (index < Salon.findAll.length) {
            val logoImgId = Image.save(l)
            val logo = Salon.findAll.toList(index)
            Salon.updateSalonLogo(logo, logoImgId)
          }
        }
      }
    }

    if (!Salon.findAll.isEmpty) {
      if (Image.fuzzyFindByName("showPic").isEmpty) {
        //save picture of salon
        val showfile = new File(play.Play.application().path() + "/public/images/store/showPic")
        val showfiles = Image.listFilesInFolder(showfile)
        var imgList: List[ObjectId] = Nil
        for ((l, index) <- showfiles.zipWithIndex) {
          if (index < Salon.findAll.length * 3) {
            val showImgId = Image.save(l)
            imgList :::= List(showImgId)
            if (index % 3 == 2) {
              val salon = Salon.findAll.toList(index / 3)
              Salon.updateSalonShow(salon, imgList)
              imgList = Nil
            }

          }
        }
      }
    }

    if (!Salon.findAll.isEmpty) {
      if (Image.fuzzyFindByName("atomPic").isEmpty) {
        //save picture of salon
        val atomfile = new File(play.Play.application().path() + "/public/images/store/atomPic")
        val atomfiles = Image.listFilesInFolder(atomfile)
        var imgList: List[ObjectId] = Nil
        for ((n, index) <- atomfiles.zipWithIndex) {
          if (index < Salon.findAll.length * 3) {
            val atomImgId = Image.save(n)
            imgList :::= List(atomImgId)
            if (index % 3 == 2) {
              val salon = Salon.findAll.toList(index / 3)
              Salon.updateSalonAtom(salon, imgList)
              imgList = Nil
            }

          }
        }
      }
    }

    if (!Nail.findAll.isEmpty) {
      if (Image.fuzzyFindByName("nail").isEmpty) {
        // save picture of nail
        val stylefile = new File(play.Play.application().path() + "/public/images/nail/nail")
        val stylefiles = Image.listFilesInFolder(stylefile)
        for ((styf, index) <- stylefiles.zipWithIndex) {
          if (index < Nail.findAll.toList.length) {
            val styleImgId = Image.save(styf)
            val nail = Nail.findAll.toList(index)
            Nail.updateNailImage(nail, styleImgId)
          }
        }
      }
    }
  }
  /*---------------------------
   * 测试数据
   *--------------------------*/
  private def insertSampleReservation {
    if (Reservation.findAll.isEmpty) {
      Seq(
      //美发
        Reservation(new ObjectId("5317c0d1d4d57997ce3e6d7a"), "demo06", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 3, dateTime("2014-04-11 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId("5317c0d1d4d57997ce3e6d7b"), "demo07", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 3, dateTime("2014-04-11 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-04-11 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-04-11 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-04-11 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-04-12 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-04-12 10:00"), 90, None, List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), None, "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId("5317c0d1d4d57997ce3e6d7c"), "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 3, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a02ed")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a02ed")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a02ed")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo06", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a02ed")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0374")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo06", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457771c0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457771c0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457771c0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457771c0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a02ee")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a02ee")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a023d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a023d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134db9aa6b4dfc54a02ef")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457771c0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457772c0c")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd6")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457771c0d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd4")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a02ed")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a02ee")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a021d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a021e")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a023d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a023e")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd4")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134db9aa6b4dfc54a02ef")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0374")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134db9aa6b4dfc54a022f")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0324")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 0, dateTime("2014-05-25 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a022d")), "051268320328", "准时到", 100, 0, 100, date("2014-05-20"), date("2014-05-20")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a022e")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd4")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0375")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0376")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0377")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd6")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0378")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0379")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0373")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457761c0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd4")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457762c0c")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457761c0d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd6")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a12ed")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdd"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a12ee")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a121d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo06", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a121e")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533133f29aa6b4dfc54a123d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134b69aa6b4dfc54a123e")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0478")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd6")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0479")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a0473")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId("5317c0d1d4d57997ce3e6d7d"), "demo10", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 3, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457761e0b")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457762e0c")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("530d828cd7f2861457761e0d")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533134db9aa6b4dfc54a22ef")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo06", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a2375")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a2376")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1395")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1396")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1397")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1575")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd9")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1576")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1577")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1675")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1676")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1677")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd7")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1775")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1776")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1777")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffd5")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1875")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771bde"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("530d8010d7f2861457771bf8")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1876")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7288d7f2861457771bdf"), "Hairdressing", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47effff2")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("533151209aa6b4dfc54a1877")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),

      //美甲
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a31")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a31")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo07", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a31")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a32")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a32")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo08", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a33")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a33")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-11 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a34")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a35")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo09", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a36")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11")),
        Reservation(new ObjectId, "demo10", new ObjectId("530d7292d7f2861457771aae"), "Manicures", 1, dateTime("2014-04-12 10:00"), 90, Option(new ObjectId("53202c29d4d5e3cd47efffe3")), List(ResvItem("coupon", new ObjectId("5317c0d1d4d57997ce3e6d6a"), 1)), Option(new ObjectId("5317c0d1d4d57996ce4b2a38")), "051268320328", "准时到", 100, 0, 100, date("2014-04-11"), date("2014-04-11"))).foreach(Reservation.save)
    }
  }

  private def insertSampleComment {
    if (Comment.findAll.isEmpty) {
      Seq(
        Comment(new ObjectId("53195fb4a89e175858abce85"), 2, new ObjectId("5317c0d1d4d57997ce3e6d7a"), "布置的很精致,温馨。价格很适中,好地方,强力推荐", 5, 5, 5, 5, 5, "demo07", date("2014-02-18"), true),
        Comment(new ObjectId("53195fb4a89e175858abce87"), 3, new ObjectId("53195fb4a89e175858abce85"), "谢谢惠顾本店,您的满意是我们最大的幸福", 0, 0, 0, 0, 0, "demo01", date("2014-02-19"), true),
        Comment(new ObjectId("53195fb4a89e175858abce86"), 2, new ObjectId("5317c0d1d4d57997ce3e6d7b"), "发型师能根据我的脸型,发质提出多种方案供选择。最后剪出的发型非常适合自己。真的不错,下次还会来的。", 5, 5, 5, 5, 5, "demo08", date("2014-03-19"), true),
        Comment(new ObjectId, 2, new ObjectId("5317c0d1d4d57997ce3e6d7c"), "环境优雅,服务态度好。最重要的是发型师的技术非常不错。非常满意,下次还会来的。", 5, 5, 5, 5, 5, "demo07", date("2014-03-31"), true),
        Comment(new ObjectId, 2, new ObjectId("5317c0d1d4d57997ce3e6d7d"), "这家店是我有史以来去过的最好的一家店,强力推荐大家一起去。", 5, 5, 5, 5, 5, "demo07", date("2014-04-01"), true)).foreach(Comment.save)
    }
  }

  private def insertSampleBlog {
    if (Blog.findAll.isEmpty) {
      Seq(
        Blog(new ObjectId("53195fb4a89e175858abce92"), "美发店装修设计注意事项", "美发店是每个人都要去的地方,街上各种美发店大多数人们会怎样选择呢,个人觉得大众人群会选择装修店面好一些但也不是很豪华的那种。不同的美发店在装修设计上是改针对不同的客户群体的,那么在美发店的整体设计中都应该怎样做呢?<br>首先在街面上的自然是店面和招牌,对于最显眼的招牌来说,它直接反应的是店面的形象和店面的风格,因此应该着重注意一下色彩带的搭配,现在大多的以深色的主体配合其他颜色,以引人注目为主要目的。<br>再有就是重要的店面风格,不管选择什么样的风格,重要的是表现出店面的文化和专业的气氛。还应当和周围的店面在风格上区分开来,以显示不一样的经营,装饰用的话也不要尽选用那些美女帅哥的发型图片,安放一些工艺品和名人字画也是能够突出店面的文化档次。店面内应当设施适当的休息区域能够让客人坐下来休息,最好还是能够看到工作人员的工作,了解店面的服务质量,店面内的装修应当明快清新,暖色调能够使人感到轻松温馨,并且具有着依赖感和安全感,当然了店面内的设计还是应该注意所针对的消费群体的档次,不能太低,也不能让人看到后忘而却步。<br>对于美发店来说,店面内重要的就是操作区,它占有者店面一半以上的面积,对于操作区的设计,这里是直接服务顾客的地方,因此应当宽松干净和舒适,还应当考虑到行走的方便和顺畅,而且区域内的家具等尽量选择精美一些的,外星应当独特而色调统一。还有就是一些功能性的服务设施,例如卫生间,虽然大多客人可能用不到,但是还是应该重视的,这也会给顾客留下深刻的印象,卫生间可以相对的豪华一些,不能有异味,在卫生间内还可以挂上自己店的经营理念等具有销售意义的字画。当然店面内的一些小的注意事项也是应当在意的,例如毛巾的摆放,收银台的布置,饮水的安放等。最后是灯光的设置,应当选用明亮的有各种色彩的节能灯或者太阳灯,数量应当根据实际需要来定,灯光的照射应当冬季明快,夏天温馨,春秋舒畅。<br>美发店的装修要向让顾客回头光顾,还应当有热情的服务,室内的细节也应当去除冰冷,给人感觉温暖的感觉。<br>", "demo02", date("2014-02-18"), date("2014-02-18"), "其他", None, List("111", "2222"), true, Option(true), false, true),
        Blog(new ObjectId("532a8ef4a89ee221d679bdc1"), "生命是一场聚散", "生命是一场聚散,初涉人世的第一声啼哭就拉开了聚的序幕。于是以后的岁月里,花开花落,云卷云舒,就有了数不清的相遇、相识、相处、相爱、相恨,到最后的相离。", "demo02", date("2014-02-19"), new Date(), "其他", None, List("111", "2222"), true, Option(true), false, true),
        Blog(new ObjectId("532a8ef4a89ee221d679bdc2"), "白醋让你成为千年老妖", "白醋 让你年轻10岁（我的天,可怕的妖怪就要产生了！）<br>经常见的白醋,其实蕴藏着十分深刻的美容护肤秘密。只巧妙利用,平凡普通的白醋,就可以让你容颜焕发,拥有漂亮肌肤。", "demo01", date("2014-03-18"), new Date(), "健康养生", None, List("2222"), true, Option(true), false, true),
        Blog(new ObjectId("532a8ef4a89ee221d679bdc3"), "妙方让女人年轻10岁", "爱美的女性,谁不想使自己更年轻,并能留住一份健康的美？我们介绍的方法非常容易实现,只要你能够坚持。想要年轻10岁？没有想象中那么困难,但是也要持之以恒哦!", "demo05", date("2014-04-01"), new Date(), "健康养生", None, List("2222"), true, Option(true), false, true),
        Blog(new ObjectId("532a8ef4a89ee221d679bdc4"), "每天一杯酸奶", "肌肤要“润”常用酸奶,酸奶中含有大量的乳酸,作用温和,而且安全可靠。酸奶面膜就是利用这些乳酸,发挥剥离性面膜的功效,每日使用,会使肌肤柔嫩、细腻。做法也很简单,举手之劳而已。", "demo06", date("2014-04-02"), new Date(), "健康养生", None, List("2222"), true, Option(true), false, true),
        Blog(new ObjectId("53195fb4a89e175858abce90"), "一杯豆浆", "女人一到中年,体内的雌激素开始减退,这样就会加速体内的钙质流失,就会引起人体的各个功能的很快衰退,每天坚持喝一杯豆浆,能增加人体雌激素的及时补充,有不会因人为的用药调节而形成对身体器官的其他副作用。 ", "demo02", date("2014-03-31"), new Date(), "健康养生", None, List("111", "2222"), true, Option(true), false, true),
        Blog(new ObjectId("53195fb4a89e175858abce91"), "每日泡一次脚", "<b>可以在早上（只需20分钟）,也可在晚上（最好1小时）,用40度以上的热水加几滴醋泡脚,可以起到健身安神之效。</b>", "demo01", date("2014-03-30"), new Date(), "健康养生", None, List("2222"), true, Option(true), false, true)).foreach(Blog.save)
    }
  }

  private def insertSampleService {
    if (Service.findAll.isEmpty) {
      Seq(
        Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true),
        Service(new ObjectId("53168b61d4d5cb7e816db35e"), "数码烫", "数码烫是一种新型的技术", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true),
        Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true),
        Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true),
        Service(new ObjectId("5316be49d4d57997ce3e6d50"), "小脸剪", "该发型剪完之后会有很好地修饰脸蛋的作用", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 20, 30, date("2014-03-31"), null, true),
        Service(new ObjectId("5316be8ed4d57997ce3e6d52"), "3D彩色", "染后能让头发更有立体感", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 80, 150, date("2014-03-31"), null, true),
        Service(new ObjectId("5316beb4d4d57997ce3e6d54"), "低色短", "底色短", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 80, 140, date("2014-03-31"), null, true),
        Service(new ObjectId("5316bed4d4d57997ce3e6d56"), "蒸汽烫", "高温水蒸气使得头发弯曲", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 45, 80, date("2014-03-31"), null, true),
        Service(new ObjectId("5316bef6d4d57997ce3e6d58"), "纯护理", "对干枯分叉等问题修复", "Care", new ObjectId("530d7288d7f2861457771bdd"), 50, 66, date("2014-03-31"), null, true),
        Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true),
        Service(new ObjectId("5316c05bd4d57997ce3e6d5c"), "干洗", "先用干洗专用清洗剂洗一下,然后用水冲洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 15, 20, date("2014-03-31"), null, true),
        Service(new ObjectId("5316c07fd4d57997ce3e6d5e"), "简吹", "将湿的头发吹干", "Blow", new ObjectId("530d7288d7f2861457771bdd"), 10, 15, date("2014-03-31"), null, true),
        Service(new ObjectId("5316c0a2d4d57997ce3e6d60"), "豪华护理", "对严重干枯分叉等问题修复", "Care", new ObjectId("530d7288d7f2861457771bdd"), 70, 200, date("2014-03-31"), null, true),
        Service(new ObjectId("5316c0d1d4d57997ce3e6d62"), "拉直", "拉直头发", "Supple", new ObjectId("530d7288d7f2861457771bdd"), 30, 50, date("2014-03-31"), null, true),
        Service(new ObjectId("5316ec2fd4d57997ce3e6d97"), "盘发", "将长发盘起", "Other", new ObjectId("530d7288d7f2861457771bdd"), 50, 50, date("2014-03-31"), null, true),
        Service(new ObjectId("5316ecffd4d57997ce3e6d9d"), "盘发2", "将中长发盘起", "Other", new ObjectId("530d7288d7f2861457771bdd"), 100, 80, date("2014-03-31"), null, true),
        Service(new ObjectId("53168b38d4d5cb7e816db45c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7292d7f2861457771bde"), 10, 10, date("2014-03-31"), null, true),
        Service(new ObjectId("53168b38d4d5cb7e816db44c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7292d7f2861457771bbb"), 10, 10, date("2014-03-31"), null, true),
        Service(new ObjectId("53168b38d4d5cb7e816db99c"), "spa", "炫酷Spa", "Spa", new ObjectId("530d7288d7f2861457771aad"), 100, 80, date("2014-07-30"), null, true),
        Service(new ObjectId("53168b38d4d5cb7e816db98c"), "按摩", "按摩放松", "Massage", new ObjectId("530d7288d7f2861457771aad"), 100, 90, date("2014-07-30"), null, true)).foreach(Service.save)
    }
  }

  private def insertSampleMyFollow {
    if (MyFollow.findAll.isEmpty) {
      Seq(
        MyFollow(new ObjectId("532f9889d4d5f03ea49463fd"), new ObjectId("530d8010d7f2861457771bf8"), new ObjectId("530d7288d7f2861457771bdd"), "salon"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463fe"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("53202c29d4d5e3cd47efffe1"), "user"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e1"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("530d7288d7f2861457771bdd"), "salon"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e2"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("530d8010d7f2861457771bf8"), "stylist"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463a3"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("53202c29d4d5e3cd47efffd3"), "stylist"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e4"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("5317c0d1d4d57997ce3e6d6a"), "coupon"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e5"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("5317c0d1d4d57997ce3e6d6b"), "coupon"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e6"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("53195fb4a89e175858abce92"), "blog"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e7"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("532a8ef4a89ee221d679bdc1"), "blog"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e8"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("532a8ef4a89ee221d679bdc2"), "blog"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e9"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("530d828cd7f2861457771c0b"), "style"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e0"), new ObjectId("53202c29d4d5e3cd47efffd0"), new ObjectId("530d828cd7f2861457772c0c"), "style"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463ff"), new ObjectId("53202c29d4d5e3cd47efffe1"), new ObjectId("53202c29d4d5e3cd47efffd0"), "user"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463ed"), new ObjectId("53202c29d4d5e3cd47efffd3"), new ObjectId("5317c0d1d4d57997ce3e6d6a"), "coupon"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e1"), new ObjectId("53202c29d4d5e3cd47efffd3"), new ObjectId("5317c0d1d4d57997ce3e6d6b"), "coupon"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e3"), new ObjectId("530d8010d7f2861457771bf8"), new ObjectId("53195fb4a89e175858abce90"), "blog"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e4"), new ObjectId("53202c29d4d5e3cd47efffd3"), new ObjectId("53195fb4a89e175858abce91"), "blog"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e5"), new ObjectId("53202c29d4d5e3cd47efffd3"), new ObjectId("530d828cd7f2861457771c0b"), "style"),
        MyFollow(new ObjectId("532f9889d4d5f03ea49463e6"), new ObjectId("53202c29d4d5e3cd47efffd3"), new ObjectId("530d828cd7f2861457772c0c"), "style")).foreach(MyFollow.save)
    }
  }

  private def insertSampleMenu {
    if (Menu.findAll().isEmpty) {
      Seq(
        Menu(new ObjectId("5317c0d1d4d57337ce3e6d61"), "菜单1", "刘海剪 + 离子烫", new ObjectId("530d7288d7f2861457771bdd"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true)), 130, 100, date("2014-03-19"), null, true),
        Menu(new ObjectId("5317c0d1d4d57337ce3e6d62"), "菜单2", "刘海剪 + 3D彩色", new ObjectId("530d7288d7f2861457771bdd"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316be8ed4d57997ce3e6d52"), "3D彩色", "染后能让头发更有立体感", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 80, 150, date("2014-03-31"), null, true)), 90, 160, date("2014-03-19"), null, true),
        Menu(new ObjectId("5317c0d1d4d57337ce3e6d63"), "菜单3", "刘海剪 + 简洗", new ObjectId("530d7288d7f2861457771bdd"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true)), 20, 20, date("2014-03-19"), null, true)).foreach(Menu.save)
    }
  }

  private def insertSampleCoupon {
    if (Coupon.findAll.isEmpty) {
      Seq(
        Coupon(new ObjectId("5317c0d1d4d57997ce3e6d6a"), "xjc01", "吹剪经典组合", new ObjectId("530d7288d7f2861457771bdd"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 110, 100, 90, date("2014-03-01"), date("2015-03-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "◆体验到自己可爱虽然是♪我们自然要授予风格，你想仔细咨询☆◆您可以选择洗发水是一种根据请求的皮肤和头发，定型剂", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce3e6d6b"), "xjc03", "炫染经典组合", new ObjectId("530d7288d7f2861457771bdd"), List(Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 230, 190, 120, date("2014-03-01"), date("2015-01-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "仅售120元！最高价值1338元的白领美发美甲沙龙美发套餐，不限男女，不限长短发，店内提供免费WiFi。", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce3e6d6e"), "xjc04", "白领美发组合", new ObjectId("530d7292d7f2861457771bde"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 110, 100, 90, date("2014-03-01"), date("2015-03-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "◆体验到自己可爱虽然是♪我们自然要授予风格，你想仔细咨询☆◆您可以选择洗发水是一种根据请求的皮肤和头发，定型剂", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce3e6d6f"), "xjc05", "居家美发组合", new ObjectId("530d7292d7f2861457771bde"), List(Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 230, 190, 120, date("2014-03-01"), date("2015-01-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "仅售120元！最高价值1338元的白领美发美甲沙龙美发套餐，不限男女，不限长短发，店内提供免费WiFi。", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6d6f"), "xjc06", "唯美经典组合", new ObjectId("530d7288d7f2861457771bdf"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 110, 100, 90, date("2014-03-01"), date("2015-03-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "◆体验到自己可爱虽然是♪我们自然要授予风格，你想仔细咨询☆◆您可以选择洗发水是一种根据请求的皮肤和头发，定型剂", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6d61"), "xjc07", "干练经典组合", new ObjectId("530d7288d7f2861457771bdf"), List(Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 230, 190, 120, date("2014-03-01"), date("2015-01-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "仅售120元！最高价值1338元的白领美发美甲沙龙美发套餐，不限男女，不限长短发，店内提供免费WiFi。", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6d62"), "xjc08", "甜美美发组合", new ObjectId("530d7288d7f2861457771bdf"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 110, 100, 90, date("2014-03-01"), date("2015-03-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "◆体验到自己可爱虽然是♪我们自然要授予风格，你想仔细咨询☆◆您可以选择洗发水是一种根据请求的皮肤和头发，定型剂", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6ee3"), "xjc09", "适合party美发组合", new ObjectId("530d7292d7f2861457771aaa"), List(Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 230, 190, 120, date("2014-03-01"), date("2015-01-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "仅售120元！最高价值1338元的白领美发美甲沙龙美发套餐，不限男女，不限长短发，店内提供免费WiFi。", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6ee4"), "xjc10", "自然美发组合", new ObjectId("530d7292d7f2861457771bbb"), List(Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 230, 190, 120, date("2014-03-01"), date("2015-01-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "仅售120元！最高价值1338元的白领美发美甲沙龙美发套餐，不限男女，不限长短发，店内提供免费WiFi。", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6ee5"), "xjc11", "活泼经典组合", new ObjectId("530d7292d7f2861457771bbb"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 110, 100, 90, date("2014-03-01"), date("2015-03-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "◆体验到自己可爱虽然是♪我们自然要授予风格，你想仔细咨询☆◆您可以选择洗发水是一种根据请求的皮肤和头发，定型剂", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6ee6"), "xjc12", "青春经典组合", new ObjectId("530d7288d7f2861457771aab"), List(Service(new ObjectId("5316c048d4d57997ce3e6d5a"), "简洗", "简单清洗", "Wash", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb51d4d57997ce3e6d4b"), "局部染", "对发梢进行染色或者挑染", "Dye", new ObjectId("530d7288d7f2861457771bdd"), 90, 120, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 230, 190, 120, date("2014-03-01"), date("2015-01-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "仅售120元！最高价值1338元的白领美发美甲沙龙美发套餐，不限男女，不限长短发，店内提供免费WiFi。", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6ee7"), "xjc13", "复古美发组合", new ObjectId("530d7292d7f2861457771aac"), List(Service(new ObjectId("53168b38d4d5cb7e816db35c"), "剪刘海", "专门剪刘海", "Cut", new ObjectId("530d7288d7f2861457771bdd"), 10, 10, date("2014-03-31"), null, true), Service(new ObjectId("5316bb36d4d57997ce3e6d49"), "离子烫", "离子烫对头发保护性最强", "Perm", new ObjectId("530d7288d7f2861457771bdd"), 90, 100, date("2014-03-31"), null, true)), 110, 100, 90, date("2014-03-01"), date("2015-03-31"), "你看到的HOTPEPPER美容♪字", "在预订时间", "◆体验到自己可爱虽然是♪我们自然要授予风格，你想仔细咨询☆◆您可以选择洗发水是一种根据请求的皮肤和头发，定型剂", true),
        Coupon(new ObjectId("5317c0d1d4d57997ce2e6ee8"), "xjc14", "休闲组合", new ObjectId("530d7288d7f2861457771aad"), List(Service(new ObjectId("53168b38d4d5cb7e816db99c"), "spa", "炫酷Spa", "Spa", new ObjectId("530d7288d7f2861457771aad"), 100, 80, date("2014-07-30"), null, true), Service(new ObjectId("53168b38d4d5cb7e816db98c"), "按摩", "按摩放松", "Massage", new ObjectId("530d7288d7f2861457771aad"), 100, 90, date("2014-07-30"), null, true)), 200, 160, 170, date("2014-07-01"), date("2015-09-30"), "不能和其他优惠共用", "来店时出示", "仅售160元！最高价值200元的休闲套餐，不限男女，店内提供免费WiFi。", true)).foreach(Coupon.save)
    }
  }

  private def insertSampleNail {
    if (Nail.findAll.isEmpty) {
      Seq(
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a31"), "法式浪漫", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("pink", "white", "silver", "beige", "gold", "orange", "brown", "yellow"), List("rhinestone", "pearl", "metal"), List("hand", "foot"), "natural", List("office", "bridal"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "清新动人", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a32"), "反转法式", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("pink", "white", "purple", "red", "black", "clear", "multi"), List("metal", "peucine", "bridal", "fruitSlices"), List("foot"), "fresh", List("office", "party", "sport"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a33"), "清新~(ˇˍˇ）", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("green", "blue", "purple", "red", "black", "clear", "multi"), List("metal", "peucine", "bridal", "fruitSlices"), List("foot"), "fresh", List("office"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a34"), "炫丽", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("green", "blue", "purple", "beige", "gold", "orange", "brown", "yellow"), List("metal", "peucine", "bridal", "fruitSlices"), List("hand"), "fresh", List("office", "party"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a35"), "可爱(～ o ～)Y", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("green", "blue", "purple", "red", "black", "clear", "multi"), List("metal", "peucine", "bridal", "fruitSlices"), List("hand", "foot"), "fresh", List( "party", "sport"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a36"), "炫丽", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("green", "blue", "purple", "beige", "gold", "orange", "brown", "yellow"), List("metal", "peucine", "bridal", "fruitSlices"), List("hand"), "fresh", List("office", "party"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a38"), "可爱(～ o ～)Y", new ObjectId("53202c29d4d5e3cd47efffe3"), "Nail", List("green", "blue", "purple", "red", "black", "clear", "multi"), List("metal", "peucine", "bridal", "fruitSlices"), List("hand", "foot"), "fresh", List( "party", "sport"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a41"), "多色变幻", new ObjectId("53202c29d4d5e3cd47efffe3"), "HandCare", List("green", "white", "purple", "red", "black", "clear", "multi"), List("metal", "peucine", "bridal", "fruitSlices"), List("hand"), "fresh", List("office", "date"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true),
        Nail(new ObjectId("5317c0d1d4d57996ce4b2a42"), "跳动的精灵", new ObjectId("53202c29d4d5e3cd47efffe3"), "Eyelashes", List("silver", "white", "gold", "orange", "black", "clear", "multi"), List("metal", "peucine", "bridal", "fruitSlices"), List("hand", "foot"), "fresh", List( "date"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT"))), "超炫闪亮登场", date("2014-03-12"), true)).foreach(Nail.save)
    }
  }

  private def insertSampleStyle {
    if (Style.findAll.isEmpty) {
      Seq(
        //女超短
        Style(new ObjectId("530d828cd7f2861457771c0b"), "青春超短发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple"), "super-short", List("black", "chocolate", "brown", "flax", "red", "alternative"), List("much", "moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("round-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457772c0c"), "干练超短发", new ObjectId("53202c29d4d5e3cd47efffd9"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "gorgeous", List("Supple"), "super-short", List("black", "chocolate", "brown"), List("much", "moderate", "few"), List("greasy", "general"), List("moderate", "thin"), List("long-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457771c0d"), "知性超短发", new ObjectId("53202c29d4d5e3cd47efffd6"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple"), "super-short", List("black", "chocolate", "brown"), List("much", "moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("533133f29aa6b4dfc54a02ed"), "甜美超短发", new ObjectId("53202c29d4d5e3cd47efffd4"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple", "Perm"), "super-short", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face", "oval-face", "diamond-face"), "凌乱中带有一丝淡淡的忧伤！", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533134b69aa6b4dfc54a02ee"), "妩媚超短发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple", "Perm"), "super-short", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("long-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533133f29aa6b4dfc54a021d"), "清纯超短发", new ObjectId("53202c29d4d5e3cd47efffd3"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "super-short", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        //女短
        Style(new ObjectId("533134b69aa6b4dfc54a021e"), "青春可爱短发", new ObjectId("53202c29d4d5e3cd47efffd9"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple", "Perm"), "short", List("flax"), List("much", "moderate", "few"), List("silky", "greasy", "dry", "general"), List("bold", "thin", "moderate"), List("long-face", "round-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533133f29aa6b4dfc54a023d"), "凌乱短发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple", "Perm"), "short", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        Style(new ObjectId("533134b69aa6b4dfc54a023e"), "气质分头短发", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("black", "chocolate"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533134db9aa6b4dfc54a02ef"), "青春妩媚短发", new ObjectId("53202c29d4d5e3cd47efffd4"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple"), "short", List("black", "chocolate"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0374"), "知书内扣短发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("black", "chocolate"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        Style(new ObjectId("533134db9aa6b4dfc54a022f"), "文静内扣短发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple", "Perm"), "short", List("black", "chocolate"), List("much", "moderate", "few"), List("silky", "greasy", "dry", "general"), List("bold", "thin", "moderate"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        //女及肩
        Style(new ObjectId("533151209aa6b4dfc54a0324"), "斜刘海中短发型", new ObjectId("53202c29d4d5e3cd47efffd9"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple", "Perm"), "near-shoulder-length", List("red"), List("moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("533133f29aa6b4dfc54a022d"), "甜美梨花发", new ObjectId("53202c29d4d5e3cd47efffd8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple", "Perm"), "near-shoulder-length", List("red"), List("moderate", "few"), List("silky", "greasy", "general"), List("bold"), List("long-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("533134b69aa6b4dfc54a022e"), "内扣及肩发", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple"), "near-shoulder-length", List("red"), List("moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0375"), "及肩大批发", new ObjectId("53202c29d4d5e3cd47efffd4"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "near-shoulder-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0376"), "及肩斜刘海", new ObjectId("53202c29d4d5e3cd47efffd3"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "near-shoulder-length", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0377"), "知性斜刘海发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "near-shoulder-length", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "dry", "general"), List("bold", "thin"), List("long-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "others"), date("2014-03-12"), true),
        //女齐肩
        Style(new ObjectId("533151209aa6b4dfc54a0378"), "摇曳齐肩发", new ObjectId("53202c29d4d5e3cd47efffd6"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "shoulder-length", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0379"), "可爱内扣发", new ObjectId("53202c29d4d5e3cd47efffd9"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple"), "shoulder-length", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "general"), List("bold"), List("oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0373"), "斜刘海齐肩发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "shoulder-length", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457761c0b"), "懵懂齐肩发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple"), "shoulder-length", List("black", "chocolate", "brown", "flax", "red", "alternative"), List("much", "moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("round-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457762c0c"), "调皮内扣齐肩发", new ObjectId("53202c29d4d5e3cd47efffd4"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "gorgeous", List("Supple"), "shoulder-length", List("black", "chocolate", "brown"), List("much", "moderate", "few"), List("greasy", "general"), List("moderate", "thin"), List("long-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457761c0d"), "随性可爱发型", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple"), "shoulder-length", List("black", "chocolate", "brown"), List("much", "moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        //女中长
        Style(new ObjectId("533133f29aa6b4dfc54a12ed"), "中长卷发", new ObjectId("53202c29d4d5e3cd47efffd6"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple", "Perm"), "mid-length", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face", "oval-face", "diamond-face"), "凌乱中带有一丝淡淡的忧伤！", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533134b69aa6b4dfc54a12ee"), "知性分头中长发", new ObjectId("53202c29d4d5e3cd47efffd3"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple", "Perm"), "mid-length", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("long-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533133f29aa6b4dfc54a121d"), "可爱清纯发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "mid-length", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        Style(new ObjectId("533134b69aa6b4dfc54a121e"), "知书秀气发", new ObjectId("53202c29d4d5e3cd47efffd9"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple", "Perm"), "mid-length", List("flax"), List("much", "moderate", "few"), List("silky", "greasy", "dry", "general"), List("bold", "thin", "moderate"), List("long-face", "round-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533133f29aa6b4dfc54a123d"), "Little凌乱发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple", "Perm"), "mid-length", List("flax", "red", "alternative"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        Style(new ObjectId("533134b69aa6b4dfc54a123e"), "内扣中长发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "mid-length", List("black", "chocolate"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "others"), date("2014-03-12"), true),
        //女长
        Style(new ObjectId("533151209aa6b4dfc54a0478"), "波浪长发", new ObjectId("53202c29d4d5e3cd47efffd6"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "long", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "street", "evening-wear"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0479"), "可爱梨花头", new ObjectId("53202c29d4d5e3cd47efffd6"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple"), "long", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "general"), List("bold"), List("oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a0473"), "妩媚大长发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "long", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "general"), List("bold"), List("long-face", "round-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457761e0b"), "过肩大长发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple"), "long", List("black", "chocolate", "brown", "flax", "red", "alternative"), List("much", "moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("round-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457762e0c"), "过肩长卷发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "gorgeous", List("Supple"), "long", List("black", "chocolate", "brown"), List("much", "moderate", "few"), List("greasy", "general"), List("moderate", "thin"), List("long-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        Style(new ObjectId("530d828cd7f2861457761e0d"), "山间的美丽", new ObjectId("53202c29d4d5e3cd47efffd3"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "fresh", List("Supple"), "long", List("black", "chocolate", "brown"), List("much", "moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--fifteen", "fifty-five--", "one--twenty-five", "twenty-five--thirty-five"), "female", List("star"), date("2014-03-12"), true),
        //男超短
        Style(new ObjectId("533134db9aa6b4dfc54a22ef"), "青春短寸", new ObjectId("53202c29d4d5e3cd47efffd9"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "natural", List("Supple"), "short", List("black", "chocolate"), List("much", "moderate", "few"), List("silky", "greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a2375"), "大碎发", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "mid-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a2376"), "欧美超短发", new ObjectId("53202c29d4d5e3cd47efffd3"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "super-short", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        //男短
        Style(new ObjectId("533151209aa6b4dfc54a1395"), "摇曳的风", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "mid-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1396"), "青春短发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "super-short", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1397"), "青春卷发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "dry", "general"), List("bold", "thin"), List("long-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        //男及肩
        Style(new ObjectId("533151209aa6b4dfc54a1575"), "披头客", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "mid-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1576"), "纹理披发", new ObjectId("53202c29d4d5e3cd47efffd6"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "super-short", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1577"), "分头及肩发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "dry", "general"), List("bold", "thin"), List("long-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        //男齐肩
        Style(new ObjectId("533151209aa6b4dfc54a1675"), "分头齐肩大长发", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "mid-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1676"), "男士斜刘海长发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "super-short", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1677"), "男士齐肩长发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "dry", "general"), List("bold", "thin"), List("long-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        //男中长
        Style(new ObjectId("533151209aa6b4dfc54a1775"), "男士斜刘海中长发", new ObjectId("53202c29d4d5e3cd47efffd7"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "mid-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1776"), "随性中长发", new ObjectId("53202c29d4d5e3cd47efffd3"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "super-short", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1777"), "分头中长发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "dry", "general"), List("bold", "thin"), List("long-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        //男长
        Style(new ObjectId("533151209aa6b4dfc54a1875"), "文艺大长发", new ObjectId("53202c29d4d5e3cd47efffd5"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "intellectual", List("Supple"), "mid-length", List("brown", "flax"), List("moderate", "few"), List("greasy", "general"), List("bold", "thin"), List("long-face", "round-face", "oval-face", "diamond-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1876"), "男士过肩大长发", new ObjectId("530d8010d7f2861457771bf8"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "personality", List("Supple", "Perm"), "super-short", List("brown", "flax"), List("much"), List("silky", "greasy", "general"), List("bold", "thin", "moderate"), List("round-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "evening-wear", "others"), date("2014-03-12"), true),
        Style(new ObjectId("533151209aa6b4dfc54a1877"), "中分长发", new ObjectId("53202c29d4d5e3cd47effff2"), List(new OnUsePicture(new ObjectId, "main", Some(1), Option("FRONT")), new OnUsePicture(new ObjectId, "side", Some(1), Option("SIDE")), new OnUsePicture(new ObjectId, "back", Some(1), Option("BACK"))), "sweet", List("Supple", "Perm"), "short", List("chocolate", "brown", "flax", "red"), List("much"), List("silky", "greasy", "dry", "general"), List("bold", "thin"), List("long-face", "oval-face"), "此种发型清爽怡人,迎面而过,回眸一笑百媚生", List("one--twenty-five", "twenty-five--thirty-five"), "male", List("star", "street", "others"), date("2014-03-12"), true)).foreach(Style.save)
    }
  }

  private def insertSampleSalonAndStylist {
    if (SalonAndStylist.findAll.isEmpty) {
      Seq(
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("530d8010d7f2861457771bf8"), List(new IndustryAndPosition(new ObjectId, "美甲师", "店长")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("53202c29d4d5e3cd47efffd3"), List(new IndustryAndPosition(new ObjectId, "美甲师", "首席技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("53202c29d4d5e3cd47efffd9"), List(new IndustryAndPosition(new ObjectId, "美甲师", "高级技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("53202c29d4d5e3cd47efffd4"), List(new IndustryAndPosition(new ObjectId, "美甲师", "助手")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("53202c29d4d5e3cd47efffd8"), List(new IndustryAndPosition(new ObjectId, "美甲师", "技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7292d7f2861457771bde"), new ObjectId("53202c29d4d5e3cd47efffd7"), List(new IndustryAndPosition(new ObjectId, "美甲师", "首席技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7292d7f2861457771bde"), new ObjectId("53202c29d4d5e3cd47efffd6"), List(new IndustryAndPosition(new ObjectId, "美甲师", "高级技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7292d7f2861457771bde"), new ObjectId("53202c29d4d5e3cd47efffd5"), List(new IndustryAndPosition(new ObjectId, "美甲师", "高级技师")), new Date, Option(new Date), false),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdf"), new ObjectId("53202c29d4d5e3cd47effff1"), List(new IndustryAndPosition(new ObjectId, "美甲师", "高级技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdf"), new ObjectId("53202c29d4d5e3cd47effff2"), List(new IndustryAndPosition(new ObjectId, "美甲师", "技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdf"), new ObjectId("53202c29d4d5e3cd47effff3"), List(new IndustryAndPosition(new ObjectId, "美甲师", "首席技师")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7288d7f2861457771bdf"), new ObjectId("53202c29d4d5e3cd47effff4"), List(new IndustryAndPosition(new ObjectId, "美甲师", "店长")), new Date, None, true),
        SalonAndStylist(new ObjectId, new ObjectId("530d7292d7f2861457771aae"), new ObjectId("53202c29d4d5e3cd47efffe3"), List(new IndustryAndPosition(new ObjectId, "美甲师", "店长")), new Date, None, true)).foreach(SalonAndStylist.save)
    }
  }

  private def insertSampleUser {
    if (User.findAll.isEmpty) {
      Seq(
        User(new ObjectId("530d8010d7f2861457771bf8"), "demo01", "维达沙宣", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "wzw1991@126.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "Administrator", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd3"), "demo02", "苏小魂", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "wzw19910109@163.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "Administrator", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd4"), "demo03", "阿哲", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12301@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd9"), "demo04", "李莫愁", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12302@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd8"), "demo05", "西门吹雪", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12303@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd7"), "demo06", "叶孤城", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12304@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd6"), "demo07", "陆小风", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12305@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd5"), "demo08", "花满楼", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12306@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47effff1"), "demo12", "豆豆", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12307@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47effff2"), "demo13", "平平", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12308@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47effff3"), "demo14", "晓晓", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12309@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47effff4"), "demo15", "西部牛仔", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12310@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffd0"), "demo09", "独孤求败", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12311@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "normalUser", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffe1"), "demo10", "中原一点红", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12312@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "normalUser", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffe2"), "demo11", "红叶", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12313@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("程序员"), "normalUser", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true),
        User(new ObjectId("53202c29d4d5e3cd47efffe3"), "demo16", "百晓生", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU.", "F", Some(date("1991-03-18")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "")), new ObjectId, Some("15269845698"), "12313@123.com", Seq(OptContactMethod("QQ", List {
          "845654891"
        })), Some("首席美甲师"), "stylist", "userLevel.0", 20, 0, date("2014-03-18").getTime, "LoggedIn", true)).foreach(User.save)
    }
  }

  private def insertSampleStylist {
    if (Stylist.findAll.isEmpty) {
      Seq(
        Stylist(new ObjectId, new ObjectId("530d8010d7f2861457771bf8"), 5, List(new IndustryAndPosition(new ObjectId, "Manager", "Hairdressing")), List("natural"), List("evening-wear"), List("Wash", "Cut", "Blow"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "用思想设计发型,用语言解释发型。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd3"), 5, List(new IndustryAndPosition(new ObjectId, "ChiefStylist", "Hairdressing")), List("intellectual"), List("brief"), List("Dye", "Care", "Perm"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "先求稳,再求准,最后求快。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd4"), 5, List(new IndustryAndPosition(new ObjectId, "Stylist", "Hairdressing")), List("intellectual"), List("brief"), List("Supple", "setColor", "Care"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "先了解发型的形,在做发型的型。 先求稳,再求准,最后求快。 先看上下,再看左右,最后看手位。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd9"), 5, List(new IndustryAndPosition(new ObjectId, "AdvancedStylist", "Hairdressing")), List("sweet"), List("star"), List("Perm", "Dye", "Care"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆剪发：宁长勿短,宁厚勿薄,宁低勿高。 ", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd8"), 5, List(new IndustryAndPosition(new ObjectId, "Assistant", "Hairdressing")), List("fashion"), List("street"), List("Perm", "Supple", "Wash"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆烫发：卷度宁小勿大,卷芯选择/宁小勿大。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd7"), 5, List(new IndustryAndPosition(new ObjectId, "Assistant", "Hairdressing")), List("fashion"), List("street"), List("Blow", "Care", "Perm"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆染发：色度/宁深一度,不浅半度。色调/宁暗勿亮。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd6"), 5, List(new IndustryAndPosition(new ObjectId, "Stylist", "Hairdressing")), List("fresh"), List("T-stage"), List("Cut", "Wash", "Care"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆美发师的成长经历 =模仿 + 吸收 +发挥。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffd5"), 5, List(new IndustryAndPosition(new ObjectId, "Stylist", "Hairdressing")), List("personality"), List("T-stage"), List("Blow", "Dye", "Wash"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "苦和甜来自于外界,而坚强则来自内心,来自于一个人坚持不懈的努力！", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47effff1"), 5, List(new IndustryAndPosition(new ObjectId, "Assistant", "Hairdressing")), List("fresh"), List("street"), List("Cut", "Wash", "Care"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆染发：色度/宁深一度,不浅半度。色调/宁暗勿亮。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47effff2"), 5, List(new IndustryAndPosition(new ObjectId, "ChiefStylist", "Hairdressing")), List("fresh"), List("street"), List("Blow", "Dye", "Wash"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆美发师的成长经历 =模仿 + 吸收 +发挥。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47effff3"), 5, List(new IndustryAndPosition(new ObjectId, "Assistant", "Hairdressing")), List("fresh"), List("T-stage"), List("Blow", "Dye", "Wash"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆染发：色度/宁深一度,不浅半度。色调/宁暗勿亮。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47effff4"), 5, List(new IndustryAndPosition(new ObjectId, "Assistant", "Hairdressing")), List("fresh"), List("T-stage"), List("Perm", "Supple", "Wash"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆美发师的成长经历 =模仿 + 吸收 +发挥。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true),
        Stylist(new ObjectId, new ObjectId("53202c29d4d5e3cd47efffe3"), 5, List(new IndustryAndPosition(new ObjectId, "Assistant", "Manicures")), List("natural"), List("office"), List("Pen", "Delineation", "Gel"), List("male", "female"), List("1~10", "10~20", "20~30", "30~40"), "☆美发师的成长经历 =模仿 + 吸收 +发挥。", "按照头皮通过样式建议☆脑袋矿泉根据（季节）的麻烦的建议,一个健康的状态样式建议☆日本四季可再生按照客户的后顾之忧,即使从后面的房子,改善☆（头按摩）", "*（自主开发,本作的,运动员都特别喜欢）（印象系统,人类系统特别喜欢）阅读*电影DVD观看是一个（*易清洁,整齐*厨师,健康食物卡在）", "建议适合女性的身材和喜好的麻烦,希望每一位客户,下一次不只是这一次,它建的故事,包括发型到下一个,但一☆砍你强​​调“易用性对待”,风格建议♪", (List(new OnUsePicture(new ObjectId, "logo", Some(1), None))), true, true)).foreach(Stylist.save)
    }
  }

  private def insertSampleSalon {
    if (Salon.findAll.isEmpty) {
      Seq(
        Salon(new ObjectId("530d7288d7f2861457771bdd"), SalonAccount("salon01", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "悦美月容吧", Some("悦容吧"), List("Hairdressing"), Some("www.yuerong.com"), Some("美丽从这里开始！"), Some(BriefIntroduction("美丽从这里开始", "最新的潮流资讯、最顶尖的时尚发布、最贴心的造型设计、最合理的价格优势。我们立志以最时尚的理念,打造最生活的状态,塑造最自信的你！", "欢迎光临悦美月容！")), Contact("0512-68320328", "王经理", "wzw1991@126.com"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "竹园路209号", Some(100.0), Some(110.0), "地铁一号线汾湖路站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, false, false, true, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-01-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7292d7f2861457771bde"), SalonAccount("salon02", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "千美千寻吧", Some("美寻吧"), List("Hairdressing"), Some("www.meixun.com"), Some("选择我们,选择美丽！"), Some(BriefIntroduction("选择我们,选择美丽", "将细节打造为上品,力求体贴与舒适,经典与时尚。您的秀发将在这里得到个性的张扬,以更加自我的方式追寻生活的美和真谛。您的美丽人生,将在这里从“头”开始。专业和完善的美发设备,将尽显您的身份与尊贵。专业的美发产品和精湛的技术,体贴入微的全程服务,完美是您的追求,专业是我的宗旨。", "欢迎光临千美千寻！")), Contact("051268320328", "李经理", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "邓尉路110号", Some(100.0), Some(110.0), "地铁一号线滨河路站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, true, true, false, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", None, None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-03-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7288d7f2861457771bdf"), SalonAccount("salon03", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "忆荣吧", Some("忆荣吧"), List("Hairdressing"), Some("www.yirong.com"), Some("丽质自天成、魅力在忆容"), Some(BriefIntroduction("丽质自天成、魅力在忆容", " 简约、干净、充满新古典风格的环境里,忆容沙龙将让每一位客人在自在的空间里,在优美旋律的陪伴下,感受专业美发技术和造型设计的美妙。", "欢迎光临忆容吧！")), Contact("0512-68320328", "冯先生", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("金阊区"), None, "爱河桥路18号(近石路国际)", Some(100.0), Some(110.0), "地铁二号线石路站3号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, false, false, true, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-01-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7292d7f2861457771aaa"), SalonAccount("salon04", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "虞美人", Some("虞美人"), List("Hairdressing"), Some("www.yumeiren.com"), Some("做个美丽的人"), Some(BriefIntroduction("专业、时尚、 亲和、 信赖", "为了您更美好的生活,我们一直在关注,我们一直在努力！您的肯定是我们最大的收获,您的信赖更是我们最满足的奖赏！", "欢迎光临虞美人！")), Contact("0512-68320328", "马先生", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("沧浪区"), None, "人民路23-29号泰华商城1楼103", Some(100.0), Some(110.0), "地铁一号线乐桥站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, true, true, false, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", None, None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-03-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7292d7f2861457771bbb"), SalonAccount("salon05", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "美吧", Some("美吧"), List("Hairdressing"), Some("www.meiba.com"), Some("滋润,护理,完美女人,从你做起！"), Some(BriefIntroduction("专业、时尚、 亲和、 信赖", "为了您更美好的生活,我们一直在关注,我们一直在努力！您的肯定是我们最大的收获,您的信赖更是我们最满足的奖赏！", "欢迎光临美吧！")), Contact("0512-68320328", "张女士", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("姑苏区"), None, "观前街345号美罗商城1楼", Some(100.0), Some(110.0), "地铁一号线乐桥站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, true, true, false, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", None, None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-03-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7288d7f2861457771aab"), SalonAccount("salon06", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "佳人阁", Some("佳人阁"), List("Hairdressing"), Some("www.jiaren.com"), Some("只为佳人顾！"), Some(BriefIntroduction("只为佳人顾", "最新的潮流资讯、最顶尖的时尚发布、最贴心的造型设计、最合理的价格优势。我们立志以最时尚的理念,打造最生活的状态,塑造最自信的你！", "欢迎光临悦美月容！")), Contact("0512-68320328", "王经理", "wzw1991@126.com"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("重庆市", Option("重庆市"), Option("沙坪坝区"), None, "三峡广场", Some(100.0), Some(110.0), "轻轨三号号线汾湖路站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, false, false, true, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-01-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7292d7f2861457771aac"), SalonAccount("salon07", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "浪漫满屋", Some("浪漫满屋"), List("Hairdressing"), Some("www.langman.com"), Some("美丽,你也可以！"), Some(BriefIntroduction("美丽,你也可以", "将细节打造为上品,力求体贴与舒适,经典与时尚。您的秀发将在这里得到个性的张扬,以更加自我的方式追寻生活的美和真谛。您的美丽人生,将在这里从“头”开始。专业和完善的美发设备,将尽显您的身份与尊贵。专业的美发产品和精湛的技术,体贴入微的全程服务,完美是您的追求,专业是我的宗旨。", "欢迎光临千美千寻！")), Contact("051268320328", "李经理", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "狮山路58号汇豪国际1-2楼", Some(100.0), Some(110.0), "地铁一号线滨河路站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, true, true, false, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", None, None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-03-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7288d7f2861457771aad"), SalonAccount("salon08", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "圣康Spa", Some("圣康Spa"), List("Healthcare"), Some("www.zhurong.com"), Some("青春常在,驻容有方！"), Some(BriefIntroduction("青春常在,驻容有方", " 简约、干净、充满新古典风格的环境里,圣康Spa将让每一位客人在自在的空间里,在优美旋律的陪伴下,感受Spa带给您的美妙。", "欢迎光临圣康Spa！")), Contact("0512-68320328", "冯先生", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("工业园区"), None, "星港街158号湖滨新天地103-1店铺", Some(100.0), Some(110.0), "地铁一号线汾湖路站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, false, false, true, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-01-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7292d7f2861457771aae"), SalonAccount("salon09", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "靓甲吧", Some("靓甲"), List("Manicures"), Some("www.nailnice.com"), Some("指甲护理,美丽依旧！"), Some(BriefIntroduction("肌肤护理,美丽依旧", "为了您更美好的生活,我们一直在关注,我们一直在努力！您的肯定是我们最大的收获,您的信赖更是我们最满足的奖赏！", "欢迎光临虞美人！")), Contact("0512-68320328", "马先生", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("姑苏区"), None, "人民路1322-1326号", Some(100.0), Some(110.0), "地铁一号线汾湖路站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, true, true, false, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", None, None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-03-12"), new SalonStatus(1, true)),
        Salon(new ObjectId("530d7292d7f2861457771aaf"), SalonAccount("salon10", "$2a$10$q0rl.qI.X9UTPZ6mDRbVhOvxYjk9S7RsrAmJ3aXaJaEcLV/3f/bU."), "炫甲风暴", Some("炫甲"), List("Manicures"), Some("www.nailnice.com"), Some("我们都会美！"), Some(BriefIntroduction("我们都会美", "为了您更美好的生活,我们一直在关注,我们一直在努力！您的肯定是我们最大的收获,您的信赖更是我们最满足的奖赏！", "欢迎光临美吧！")), Contact("0512-68320328", "张女士", "hz-han@rontech.co.jp"), List(OptContactMethod("QQ", List("99198121"))), Some(date("2014-03-12")), Some(Address("江苏省", Option("苏州市"), Option("高新区"), None, "长江路211号泉屋百货1楼", Some(100.0), Some(110.0), "地铁一号线玉山站1号出口向西步行500米可达")), Some(WorkTime("9:00", "18:00")), Some(RestDay("Fixed", List("Monday"))), Some(25), Some(SalonFacilities(true, true, true, true, false, true, true, true, true, "附近有")), List(new OnUsePicture(new ObjectId, "LOGO", None, None), new OnUsePicture(new ObjectId, "Navigate", Some(1), None), new OnUsePicture(new ObjectId, "Navigate", Some(2), None), new OnUsePicture(new ObjectId, "Navigate", Some(3), None), new OnUsePicture(new ObjectId, "Atmosphere", Some(1), Some("清新怡人")), new OnUsePicture(new ObjectId, "Atmosphere", Some(2), Some("环境优雅！")), new OnUsePicture(new ObjectId, "Atmosphere", Some(3), Some("安静典雅！")), new OnUsePicture(new ObjectId, "SalonCheck", Some(1), Some("营业执照！"))), date("2014-03-12"), new SalonStatus(1, true))).foreach(Salon.save)
    }
  }

}

