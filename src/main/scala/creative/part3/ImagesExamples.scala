package creative.part3
import doodle.core._
import doodle.image.Image
import doodle.image.Image.Elements.Triangle
import doodle.image.syntax._
import doodle.java2d._
import doodle.image.Image._
import doodle.syntax._
object ImagesExamples {
  val triangles =(triangle(50,50)
    strokeWidth(10)
    strokeColor(Color.blue))  below
    (triangle(50,50)
      strokeWidth(10)
      strokeColor(Color.blue)
      fillColor(Color.red spin(-20.degrees)) beside
    (triangle(50,50).strokeWidth(10).strokeColor(Color.blue).fillColor(Color.red.spin(40 degrees))))

  def drawTriangles = triangles.draw()


  circle(10)

  val simpleArcheryTarget = (circle(150) on circle(100) on circle(50))
  val colorArcheryTarget = (
        (circle(10) strokeColor Color.red fillColor Color.red) on
        (circle(20) strokeColor Color.white fillColor Color.white) on
        (circle(30) fillColor Color.red strokeWidth(3))at(0,13) above
          ( rectangle(6, 20)  above rectangle(20, 6) fillColor Color.brown ) above
          ( rectangle(80, 25)  fillColor Color.green )
    )
  def drawSat = simpleArcheryTarget.draw()
  def drawCat = colorArcheryTarget.draw()
}
