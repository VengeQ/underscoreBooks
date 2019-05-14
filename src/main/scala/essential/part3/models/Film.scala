package essential.part3.models

case class Film(name:String, yearOfRelease:Int, imdbRating:Double, director: Director) {
  def directorAge:Int = this.yearOfRelease-director.yearOfBirth
  def isDirectedBy(director: Director) = if (director == this.director) true else false
}
