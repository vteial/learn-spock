import groovy.sql.Sql;


//def an = new AutoNumber()
//an.id = 'testKey'
//an.val = 1
//println an
//
//AutoNumberService ans = new MemoryAutoNumberService()
//println ans.nextNumber('testId')
//println ans.nextNumber('testId')
//
//ans = new JdbcAutoNumberService()
//println ans.nextNumber('testId')
//println ans.nextNumber('testId')

Sql sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver");
String query = '''
		CREATE TABLE auto_number (
			id          VARCHAR2(50) NOT NULL,
			val         INTEGER NOT NULL,
			CONSTRAINT auto_number_pk0 PRIMARY KEY (id)
		);
		'''
sql.execute(query);

JdbcAutoNumberService ans = new JdbcAutoNumberService(sql);

println 'testId = ' + ans.nextNumberX('testId')
println '---------------------------------------'
println 'testId = ' + ans.nextNumberX('testId')

sql.close()

