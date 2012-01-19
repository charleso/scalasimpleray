package ray

case class Scene(eye: Vector, objects: List[Sphere], lights: List[Light])

case class Ray(position: Vector, direction: Vector) {
  def *(v: Double) = position + direction * v
}

case class Vector(x: Double, y: Double, z: Double) {

  def +(v: Vector) = Vector(x + v.x, y + v.y, z + v.z)
  def -(v: Vector) = Vector(x - v.x, y - v.y, z - v.z)
  def *(v: Vector) = Vector(x * v.x, y * v.y, z * v.z)
  def /(v: Vector) = Vector(x / v.x, y / v.y, z / v.z)

  def *(v: Double) = Vector(x * v, y * v, z * v)
  def /(v: Double) = Vector(x / v, y / v, z / v)

  def dot(v: Vector) = x * v.x + y * v.y + z * v.z

  def *+(v: Vector) = {
    val t = this * v
    t.x + t.y + t.z
  }

  def lengthSquared = this *+ this
  def length = Math.sqrt(lengthSquared)
  def normalized = this / length
}

case class Sphere(position: Vector, radius: Int) {

  def intersectRay(ray: Ray): Double = {
    val v = position - ray.position
    val a = v *+ ray.direction
    val b = v.lengthSquared - (radius * radius)
    val c = a * a - b
    if (c >= 0) a - Math.sqrt(c) else Double.PositiveInfinity
  }
}

case class Light(position: Vector)
