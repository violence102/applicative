import org.scalatest.{FlatSpec, Matchers}

class Test extends FlatSpec with Matchers {

  it should "maybe greet user" in {
    val name = "tom"
    val condition = "sun"

    val user = UserRepository.getUser(name)
    val weather = WeatherRepository.getWeather(condition)

    val greet = user.mapF(u => weather.mapF(w => Greeter.greet(u, w)))

    greet shouldBe Option("Welcome Tomek in this sunny day.")
  }

  it should "maybe greet user using zip" in {
    val name = "krzys"
    val condition = "rain"

    val user = UserRepository.getUser(name)
    val weather = WeatherRepository.getWeather(condition)

    val greet = optionApp.mapF(user zip weather) {
      case (u, w) => Greeter.greet(u, w)
    }

    greet shouldBe Option("Welcome Krzysiek in this rainy day.")
  }

  it should "maybe greet user using map2" in {
    val name = "pshem"
    val condition = "wind"

    val user = UserRepository.getUser(name)
    val weather = WeatherRepository.getWeather(condition)

    val greet = optionApp.map2(user, weather)(Greeter.greet)

    greet shouldBe Option("Welcome Przemek in this windy day.")
  }

  it should "maybe greet user using ap" in {
    val name = "voytech"
    val condition = "cloud"

    val user = UserRepository.getUser(name)
    val weather = WeatherRepository.getWeather(condition)

    val f = (u: User, w: Weather) => Greeter.greet(u, w)
    val fcur = f.curried
    val mf = user.mapF(fcur)

    val greet = optionApp.ap(mf)(weather)

    greet shouldBe Option("Welcome Wojtek in this cloudy day.")
  }
}
