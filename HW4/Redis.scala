package HW4

class Redis {

  var store:Map[String,String] = Map()
  var liststore = Map[String, List[String]]()

  def get(key: String): String = {
    if (store(key) != None) {
      store(key)
    }
    "Not found"
  }

  def set(key: String, value: String) = {
    store = store + (key -> value)
  }

  def lpush(key: String, value: String) = {
    if (liststore(key) != None) {

      liststore += (key -> (value :: liststore(key)))
    }
    else liststore += (key -> List(value))
  }

  def rpush(key: String, value: String) = {
    if (liststore.get(key) != None) {
      liststore += (key -> (liststore(key) :+ value))

    }
    else {
      liststore = liststore + (key -> List(value))
    }
  }

  def lpop(key: String): String = {
    val result = liststore(key).head
    liststore += (key -> liststore(key).tail)
    result
  }

  def rpop(key: String): String = {
    val result = liststore(key).last
    liststore += (key -> liststore(key).init)
    result
  }

  def lrange(key: String, start:Int, stop:Int): List[String] = {
    try {
      liststore(key).slice(start, stop + 2)
    }
    catch {
      case e: NoSuchElementException => List()
    }
  }

  def llen(key: String): Int = {
    if (store(key) != None) 1
    else if (liststore(key) != None) liststore(key).size
    else 0
  }

  def flushall() = {
    store = Map()
    liststore = Map()
  }


}
