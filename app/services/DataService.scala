case class Data(retrievalDate: java.util.Date)

trait DataService {
  def findData: Data
}