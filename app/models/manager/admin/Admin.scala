package models.manager.admin


import com.meifannet.framework.db.MeifanNetModelCompanion


import mongoContext._
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.MongoDBObject
import org.mindrot.jbcrypt.BCrypt
import se.radley.plugin.salat.Binders._
import com.mongodb.casbah.Imports._


case class Admin(
                  id: ObjectId = new ObjectId,
                  email: String,
                  name: String,
                  password: String)

/*
admin class for manager
 */
object Admin extends MeifanNetModelCompanion[Admin] {
  //check the ID and Password if they can find in database return an Admin object else return none
  val dao = new MeifanNetDAO[Admin](collection = loadCollection()) {}

  def authenticate(email: String, password: String): Option[Admin] = {
    val admin = dao.findOne(MongoDBObject("email" -> email))
    // TODO BCrypt.checkpw(password, admin.get.password)
    if (admin.nonEmpty && password.compareTo(admin.get.password) == 0) {
      admin
    } else {
      None
    }
  }

  /**
   * Retrieve a User from email.
   */
  def findByEmail(email: String): Option[Admin] = {
    val admin = dao.findOne(MongoDBObject("email" -> email))
    admin
  }


}
