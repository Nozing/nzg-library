package gz.nozing.library.test.dal.util;

import gz.nozing.library.dal.book.dao.AuthorDTOCodec;
import gz.nozing.library.dal.book.dao.AuthorDTOCodecProvider;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class TestDALUtils {

    private static TestDALUtils instance;
    
    private final static String HOST = "mongodb://localhost";
    
    private final static String mongoHost = "localhost";

    private MongoDatabase database;

    private TestDALUtils() {

	configure();
    }

    public static TestDALUtils instance() {

	if (TestDALUtils.instance == null) {

	    TestDALUtils.instance = new TestDALUtils();
	}

	return TestDALUtils.instance;
    }

    public final MongoDatabase getDatabase() {

	return this.database;
    }

    private void configure() throws RuntimeException {

	MongoClient mongoClient;
	try {

	    CodecRegistry codecRegistry = CodecRegistries
		    .fromRegistries(CodecRegistries
			    .fromCodecs(new AuthorDTOCodec()), CodecRegistries
			    .fromProviders(new AuthorDTOCodecProvider()),
			    MongoClient.getDefaultCodecRegistry());
	    MongoClientOptions options = MongoClientOptions.builder()
		    .codecRegistry(codecRegistry).build();

	    mongoClient = new MongoClient(new ServerAddress(mongoHost), options);

	} catch (Exception uhe) {

	    throw new RuntimeException("Error accessing client on '" + HOST
		    + "'", uhe);
	}

	this.database = mongoClient.getDatabase("library_unitary_test");
    }
}
