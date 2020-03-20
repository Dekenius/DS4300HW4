package HW4

object Partition extends App {


    def moved(records: Int, startN: Int, endN: Int): Double = {
      var diff = 0
      for (a <- 1 until records) {
        if (a % startN != a % endN) {
          diff += 1
        }
      }

      diff

    }

//    val l = List(1,2,3)
//    val r =
//    print(l)

    val movednum = moved(1000000, 100, 107)
    print(movednum)
}
