package models.manager.admin


import com.meifannet.framework.db.MeifanNetModelCompanion


import mongoContext._
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.MongoDBObject
import org.mindrot.jbcrypt.BCrypt
import se.radley.plugin.salat.Binders._
import com.mongodb.casbah.Imports._

/**
 * Created by CCC on 14/06/12.
 */

case class Admin(
                  id: ObjectId = new ObjectId,
                  adminId: String,
                  password: String)

/*
admin class for manager
 */
object Admin extends MeifanNetModelCompanion[Admin] {
  //check the ID and Password if they can find in database return an Admin object else return none
  val dao = new MeifanNetDAO[Admin](collection = loadCollection()) {}

  def authenticate(adminId: String, password: String): Option[Admin] = {
    val admin = dao.findOne(MongoDBObject("adminId" -> adminId))
    if (admin.nonEmpty && BCrypt.checkpw(password, admin.get.password)) {
      admin
    } else {
      None
    }
  }

}
