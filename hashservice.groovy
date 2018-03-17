@Grab('org.hashids:hashids:1.0.3')
import java.util.logging.Logger 
import org.hashids.Hashids

class HashService{
	static Logger log = Logger.getLogger("")
	static Hashids hashids = new Hashids("salt this with a flick and let it slide of the elbow bae",32);

	static encode(long numberToHash){
		def hash= hashids.encode(numberToHash);
		log.info "|encode| Internal Id -> External Id = ${numberToHash} -> ${hash}"
		return hash
	}

	static decode(String hashToDecode){
		def decodedHash= hashids.decode(hashToDecode);
		log.info "|decode| External Id -> Internal Id = ${hashToDecode} -> ${decodedHash}"
		return decodedHash
	}

}



def tests=[5,18,101,6547,33429,388923,1234567, 99913333, 883233235678]

tests.each{ id ->

	println "Test: ${id} ( long value ) "+Long.parseLong(id+"")
	def encoded = HashService.encode(id)
	def decoded = HashService.decode(encoded)
	assert id == decoded.collect { "$it" }.join( '' ) as long
}
