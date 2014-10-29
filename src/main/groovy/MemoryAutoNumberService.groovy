
public class MemoryAutoNumberService implements AutoNumberService {

	def store = [:]

	@Override
	public long nextNumber(String id) {
		def an = this.store[id]

		if(!an) {
			an = new AutoNumber()
			this.store[id] = an
		}
		an.val += 1

		return an.val;
	}
}
