import spock.lang.Specification
import spock.lang.Unroll

public class SampleSpec extends Specification {

	@Unroll
	def "maximum of two numbers type one"() {

		expect :
		Math.max(a, b) == c

		where :
		a | b | c
		1 | 3 | 3
		7 | 4 | 7
		0 | 0 | 0
	}

	@Unroll
	def "maximum of two numbers type two"() {

		expect :
		Math.max(a, b) == c

		where :
		a << [1, 7, 0]
		b << [3, 4, 0]
		c << [3, 7, 0]
	}

	@Unroll
	def "#person.name is a #sex.toLowerCase() person"() {
		
		expect:
		person.getSex() == sex

		where:
		person                      || sex
		new Person(name: "Arun")    || "Male"
		new Person(name: "Lakshmi") || "Female"
	}

	static class Person {
		String name

		String getSex() {
			name == "Arun" ? "Male" : "Female"
		}
	}
}