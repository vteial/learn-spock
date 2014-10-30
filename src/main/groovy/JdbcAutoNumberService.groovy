import groovy.sql.Sql

public class JdbcAutoNumberService implements AutoNumberService {

	Sql sql = null;

	JdbcAutoNumberService(Sql sql) {
		this.sql = sql
	}

	@Override
	public long nextNumber(String id) {
		long val = 0

		def query = 'select * from auto_number where id = ?'
		def an = sql.firstRow(query, [id])
		if(an) {
			val = an.val + 1
			query = 'update auto_number set val = ? where id = ?'
			sql.executeUpdate(query, [val, id]);
		}
		else {
			val = 1
			query = 'insert into auto_number (id, val) values (?, ?)'
			sql.executeInsert(query, [id, val]);
		}

		return val
	}

	public long nextNumberX(String id) {
		def val = 0

		String query = "select * from auto_number where id = '"
		query = query + id + "'"
		def an = sql.firstRow(query)
		if(an == null) {
			val += 1
			query = "insert into auto_number (id, val) values ('"
			query += id + "', " + val + ")"
			sql.executeInsert(query);
		}
		else {
			val = an.val + 1
			query = "update auto_number set val = "
			query = query + val + " where id = '"
			query = query + id + "'"
			sql.executeUpdate(query);
		}

		return val;
	}
}
