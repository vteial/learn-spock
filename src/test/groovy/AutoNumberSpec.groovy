import groovy.sql.Sql
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

public class AutoNumberSpec extends Specification {

	@Shared
	Sql sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver");

	@Shared
	AutoNumberService memAns = null

	@Shared
	AutoNumberService jdbcAns = null

	def setupSpec() {
		this.memAns = new MemoryAutoNumberService()

		String query = '''
		CREATE TABLE auto_number (
			id          VARCHAR2(50) NOT NULL,
			val         INTEGER NOT NULL,
			CONSTRAINT auto_number_pk0 PRIMARY KEY (id)
		);
		'''
		this.sql.execute(query);

		this.jdbcAns = new JdbcAutoNumberService(sql)
	}

	def cleanupSpec() {
		this.sql.close()
	}

	@Unroll
	@Ignore
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

		//		a << ['testId', 'testId']
		//		b << [1, 2]

		//		a << ['customerId', 'customerId']
		//		b << [1, 2]
	}
}