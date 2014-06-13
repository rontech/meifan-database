package models.manager.admin


import com.meifannet.framework.db.{MeifanNetModelCompanion, MeifanNetDAO}

import mongoContext._
import com.meifannet.framework.db._
import com.mongodb.casbah.commons.MongoDBObject
import org.mindrot.jbcrypt.BCrypt

/**
 * Created by CCC on 14/06/12.
 */

case class Admin (
      adminId  : String,
     password  : String)



object Admin  extends MeifanNetModelCompanion[Admin] {
  val dao = new MeifanNetDAO[Admin](collection = loadCollection()) {}
  def authenticate(adminId: String, password: String) : Option[Admin]= {
    //val admin = dao.findOne(MongoDBObject("adminId" -> adminId))
    if ( adminId.equals("admin")&&password.equals("123456")) {// admin.nonEmpty && BCrypt.checkpw(password, admin.get.password)
       val a = new Admin("admin","123456")
     Option(a)
    } else {
      None
    }
  }
}
