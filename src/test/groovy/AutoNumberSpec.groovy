import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

public class AutoNumberSpec extends Specification {

	@Shared
	AutoNumberService memAns = new MemoryAutoNumberService()

	@Shared
	AutoNumberService jdbcAns = new JdbcAutoNumberService()

	@Unroll
	def "NextNumber by Memory"() {

		expect :

		memAns.nextNumber(a) == b

		where :

		a << ['testId', 'testId', 'customerId', 'customerId']
		b << [1, 2, 1, 2]
	}

	@Unroll
	def "NextNumber by Jdbc"() {

		expect :

		jdbcAns.nextNumber(a) == b

		where :

		a << ['testId', 'testId', 'customerId', 'customerId']
		b << [1, 2, 1, 2]
	}
}