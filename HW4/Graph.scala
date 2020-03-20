package HW4

class Graph {

  var redis:Redis = new Redis()

  def addNode(v: String) = {
    redis.set(v, "exists")
  }

  def addEdge(u: String, v: String) = {
    redis.rpush(u, v)
  }

  def adjacent(v: String): List[String] = {
    redis.lrange(v, 0, redis.llen(v))
  }

  def f[T](v: T) = v match {
    case _: Int    => "Int"
    case _: String => "String"
    case _         => "Unknown"
  }

  // q is list of already seen nodes on this path
  // cur is current node to be explored
  case class State(q: List[String], cur: String)

  def shortestPath(u: String, v: String): List[String] = {
    var seen: List[String] = List()
    var queue: List[State] = List(State(List(), u))

    // queue is a frontier
    while(queue.size != 0) {
      val next: State = queue.last
      queue = queue.init
      seen ::= next.cur

      val result = next.cur :: next.q

      // if found goal node, end and return path so far
      if (next.cur == v) {
        return result.reverse
      }
      else {
        this.adjacent(next.cur).foreach { x =>
          // if not in seen, add adjacent nodes to queue with list so far
          if (!seen.contains(x)) {
            seen ::= x
            queue ::= State(result, x)

          }
        }
      }
    }

    List("No path")

  }



}
