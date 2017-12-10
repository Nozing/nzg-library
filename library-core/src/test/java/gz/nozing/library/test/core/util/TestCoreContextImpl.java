/**
 * 
 */
package gz.nozing.library.test.core.util;

import gz.nozing.library.core.command.common.CoreContext;
import gz.nozing.library.test.dal.util.TestDALUtils;

import com.mongodb.client.MongoDatabase;

/**
 * @author nozing
 * 
 */
public class TestCoreContextImpl implements CoreContext {

	private MongoDatabase database;

	private static TestCoreContextImpl instance;

	private TestCoreContextImpl() {

		this.database = TestDALUtils.instance().getDatabase();
	}

	public static TestCoreContextImpl instance() {

		if (instance == null) {

			instance = new TestCoreContextImpl();
		}

		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gz.nozing.library.core.command.common.CoreContext#getDatabase()
	 */
	@Override
	public MongoDatabase getDatabase() {

		return this.database;
	}
}
