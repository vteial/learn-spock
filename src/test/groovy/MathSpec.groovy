import spock.lang.Specification
import spock.lang.Unroll

public class MathSpec extends Specification {
	
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
			c << [3, 7, 1]
	}

}