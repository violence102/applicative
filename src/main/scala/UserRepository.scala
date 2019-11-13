case class User(name: String)
case class Weather(condition: String)

object UserRepository {
  private val users = Map(
    "pshem" -> User("Przemek"),
    "tom" -> User("Tomek"),
    "krzys" -> User("Krzysiek"),
    "voytech" -> User("Wojtek")
  )

  def getUser(name: String): Option[User] = users.get(name)
}

object WeatherRepository {
  private val conditions = Map(
    "rain" -> Weather("rainy"),
    "wind" -> Weather("windy"),
    "sun" -> Weather("sunny"),
    "cloud" -> Weather("cloudy")
  )

  def getWeather(condition: String): Option[Weather] = conditions.get(condition)
}

object Greeter {
  def greet(user: User, weather: Weather): String = s"Welcome ${user.name} in this ${weather.condition} day."
}