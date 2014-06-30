package models.manager

/**
 * Created by HZ-HAN on 14/06/19.
 */

case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

object ManagerCommon {
  //a constant for page size
  val pageSize :Int = 10

  //0表示正在申请中状态
  val meifanSalonFlagisApy = 0

  val meifanSalonSearch = "meifanSalonSearch"

  val meifanUserSearch = "meifanUserSearch"
  //
  val meifanAppliedSalons :String = "meifanAppliedSalons"

  val salonPage = "salonCurrentPage"

  val userPage = "userCurrentPage"

  val meifanCommentSearch = "meifanCommentSearch"

  val commentPage = "commentCurrentPage"
}
