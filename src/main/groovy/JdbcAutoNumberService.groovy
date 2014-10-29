import groovy.sql.Sql

public class JdbcAutoNumberService implements AutoNumberService {

	Sql sql = null;

	JdbcAutoNumberService() {
		sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver");
		String query = '''
		CREATE TABLE auto_number (
			id          VARCHAR2(50) NOT NULL,
			val         INTEGER NOT NULL,
			CONSTRAINT AutoNumber_pk0 PRIMARY KEY (id)
		);
		'''
		sql.execute(query);
	}

	@Override
	public long nextNumber(String id) {
		def val = 0

		String query = "select * from auto_number where id = '"
		query = query + id + "'"
		def an = sql.firstRow(query)
		//println(an)
		if(an == null) {
			val += 1
			query = "insert into auto_number (id, val) values ('"
			query += id + "', " + val + ")"
			//println query
			sql.executeInsert(query);
		}
		else {
			val = an.val + 1
			query = "update auto_number set val = "
			query = query + val + " where id = '"
			query = query + id + "'"
			//println query
			sql.executeUpdate(query);
		}

		return val;
	}
}
