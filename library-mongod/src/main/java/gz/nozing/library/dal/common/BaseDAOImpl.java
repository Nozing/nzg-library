package gz.nozing.library.dal.common;


import org.apache.log4j.Logger;

import com.mongodb.client.MongoDatabase;

//TODO Implementar los metodos CRUD de forma generica...
public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(BaseDAOImpl.class);

    private MongoDatabase database;

    public BaseDAOImpl() { }

    public BaseDAOImpl(final MongoDatabase database) {

	this.database = database;
    }

    @Override
    public void setDatabase(MongoDatabase database) {

	this.database = database;
    }

    protected MongoDatabase getDatabase() {
	return this.database;
    }
}
