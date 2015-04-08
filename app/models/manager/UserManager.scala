package models.manager

import com.mongodb.casbah.Imports._
import java.util.Date
import models.portal.user._
import com.mongodb.casbah.commons.Imports.{DBObject => commonsDBObject, _}



/**
 * Created by ping-dou on 14/06/17.
 */
case class UserManager(id: ObjectId,
                  userId: String,
                  nickName: String,
                  password: String,
                  tel: Option[String],
                  email: String,
                  registerTime: Date,
                  isVaild: Boolean)

object UserManager {

  /**
   * find all users who is valid, then encapsulate
   * in a userManager type of collection
   * @param isVaild  the flag for verify user's valid
   * @return
   */
  def findAllUsers(isVaild: Boolean = true): List[UserManager] = {
    val users: List[User] = User.findAll().toList
    var list = List.empty[UserManager]
    users.map{ user =>
      list :::= List(apply(user))
    }
    list
  }

  /**
   * encapsulate user in UserManager object
   * @param user user object
   * @return
   */
  def apply(user: User) = {
    //val sdf = new SimpleDateFormat("yyyy-MM-dd");
    val registerTime= new Date(user.registerTime);
    //val register = sdf.format(date)
    new UserManager(user.id, user.userId, user.nickName,
      user.password, user.tel, user.email, registerTime, user.isValid)

  }

  /**
   * set the user to invalid which is
   * find from User DB by objectId
   * @param id user objectId
   * @return
   */
  def setUserInvalid(id: ObjectId) = {
    User.findOneById(id).map{ user =>
      User.save(user.copy(isValid = false))
    }
  }

  /**
   * set the user to valid which is
   * find from User DB by objectId
   * @param id user id
   * @return
   */
  def setUserValid(id: ObjectId) = {
    User.findOneById(id).map{ user =>
      User.save(user.copy(isValid = true))
    }
  }

  /**
   * find user by search form field
   * @param userSearch
   * @return
   */
  def findUserByCondition(userSearch :UserSearch) :List[UserManager] = {
    var srchConds: List[commonsDBObject] = Nil
    userSearch.id.filterNot(_.isEmpty).map{ id =>
      val obj="""[0-9a-z]{24}"""
      //check the id is objectId or accountId
      if(id.matches(obj)){
        srchConds :::= List(commonsDBObject("_id" -> userSearch.id))
      }else{
        srchConds :::= List(commonsDBObject("userId" -> userSearch.id))
      }
    }

    Option(userSearch.startTime).filterNot(_.isEmpty).map{ start =>
      srchConds :::= List("registerTime" $gte start.get.getTime)
    }

    Option(userSearch.endTime).filterNot(_.isEmpty).map{ end =>
      srchConds :::= List("registerTime" $lte end.get.getTime)
    }
    userSearch.isVaild.filterNot(_.isEmpty).map{ isValid =>
      isValid match {
        case "1" => srchConds :::= List(commonsDBObject("isValid" -> true))
        case "0" => srchConds :::= List(commonsDBObject("isValid" -> false))
        case _ => None
      }
    }
    val users :List[User] = if(srchConds.nonEmpty){ User.find($and(srchConds)).toList } else User.findAll.toList
    val UserManagers = users.flatMap{ user =>
      List(UserManager.apply(user))
    }
    UserManagers
  }
}

/**
 * the class for mapping search form
 * @param id the objectId or userId
 * @param startTime
 * @param endTime
 * @param isVaild
 */
case class UserSearch(id: Option[String], startTime: Option[Date], endTime: Option[Date], isVaild: Option[String])


