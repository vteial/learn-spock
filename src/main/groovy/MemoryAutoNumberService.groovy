
public class MemoryAutoNumberService implements AutoNumberService {

	def store = [:]

	@Override
	public long nextNumber(String key) {
		def an = this.store[key]

		if(!an) {
			an = new AutoNumber()
			this.store[key] = an
		}
		an.value += 1

		return an.value;
	}
}
