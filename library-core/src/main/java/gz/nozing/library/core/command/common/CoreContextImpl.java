package gz.nozing.library.core.command.common;

import gz.nozing.library.dal.book.dao.AuthorDTOCodec;
import gz.nozing.library.dal.book.dao.AuthorDTOCodecProvider;

import org.apache.log4j.Logger;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class CoreContextImpl implements CoreContext {

	private static Logger log = Logger.getLogger(CoreContextImpl.class);
	
	private final static String clientUri = "mongodb://localhost";
	private final static String mongoHost = "localhost";
	private final static String databaseName = "library_test";
	
	private MongoDatabase database;

	/**
	 * 
	 */
	public CoreContextImpl() {
		
		this.configure();
	}
	
	@Override
	public MongoDatabase getDatabase() {

		return this.database;
	}

	private void configure() throws RuntimeException {
		
		log.debug("Entering 'configure'");
		MongoClient mongoClient;
		try {
			
			CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
		            CodecRegistries.fromCodecs(
		            		new AuthorDTOCodec()),
		            CodecRegistries.fromProviders(
		            		new AuthorDTOCodecProvider()
		            ),
//		            CodecRegistries.fromProviders(
//		                    new RaCodecProvider(),
//		                    new DutyBlockCodecProvider(),
//		                    new ScheduledDutyCodecProvider()),
		            MongoClient.getDefaultCodecRegistry());  
		    MongoClientOptions options = MongoClientOptions.builder()
		            .codecRegistry(codecRegistry).build();
			
			log.debug(String.format("Database URI: %s", clientUri));
			mongoClient = new MongoClient(new ServerAddress(
					mongoHost), options);

		} catch (Exception uhe) {

			throw new RuntimeException(String.format(
					"Error accessing client on '%s'", clientUri), uhe);
		}

		log.debug("Database used: " + databaseName);
		this.database = mongoClient.getDatabase(databaseName);
		
		log.debug("Database configured: " + this.database.toString());
	}
}
