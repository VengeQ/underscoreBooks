package essential

import essential.part3.models.Cat

package object part3 {
  object ChipShop{
    def willServe(cat:Cat) = cat.food.toLowerCase.trim match {
      case "chips" => true
      case _ => false
    }
  }
}
