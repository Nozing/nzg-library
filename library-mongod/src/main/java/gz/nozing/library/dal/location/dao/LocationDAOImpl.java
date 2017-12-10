/**
 * 
 */
package gz.nozing.library.dal.location.dao;

import gz.nozing.library.dal.book.dao.BookDAOImpl;
import gz.nozing.library.dal.common.BaseDAOImpl;
import gz.nozing.library.dal.common.annotation.MongoDBUtil;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;
import gz.nozing.library.dal.location.LocationDO;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author nozing
 *
 */
public class LocationDAOImpl extends BaseDAOImpl<LocationDO> implements LocationDAO {

    private static Logger log = Logger.getLogger(BookDAOImpl.class);

    /**
     * @param database
     */
    public LocationDAOImpl() {
	super();
    }

    /**
     * @param database
     */
    public LocationDAOImpl(MongoDatabase database) {

	this.setDatabase(database);
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#findEntityById(java.lang.String)
     */
    @Override
    public LocationDO findEntityById(String entityId)
	    throws EntityNotFoundException, DALException {

	log.debug("Entering 'findEntityById'");
	log.trace("Identifier for search: '" + entityId + "'");

	BasicDBObject objId = new BasicDBObject("_id", new ObjectId(entityId));

	MongoCollection<Document> col = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(LocationDO.class));
	
	FindIterable<Document> dbObj = col.find(objId);

	Document doc = dbObj.first();
	if (doc == null) {

	    throw new EntityNotFoundException(
		    "No 'location' with id '" + entityId + "'");
	} else {
	    
	    LocationDO location = new LocationDO();
	    location.setId(doc.getObjectId("_id").toHexString());
	    location.setCode(doc.getString("code"));
	    location.setDescription(doc.getString("description"));
	    
	    /* TODO Falta recuperar las 'shelvings'*/
	    
	    return location;
	}
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#save(java.lang.Object)
     */
    @Override
    public LocationDO save(LocationDO entity) throws DALException {

	log.debug("Entering 'save'");

	Document bookObj = 
		MongoDBUtil.createDBObj(entity);
	try {

	    this.getDatabase().getCollection(
		    MongoDBUtil.getColletionNameFromEntity(
			    entity.getClass())).insertOne(bookObj);

	} catch (Exception e) {
	    
	    e.printStackTrace();
	    throw new DALException("Error inserting 'location'", e);
	}

	entity.setId(bookObj.getObjectId("_id").toHexString());

	return entity;
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#update(java.lang.Object)
     */
    @Override
    public LocationDO update(LocationDO entity) throws DALException {

	if (entity.getId() == null) {

	    throw new EntityNotFoundException(
		    "Entity 'book' doesn't have an '_id'");
	}

	BasicDBObject queryObj = 
		new BasicDBObject("_id", new ObjectId(entity.getId()));

	this.getDatabase().getCollection(MongoDBUtil.getColletionNameFromEntity(
		LocationDO.class)).findOneAndUpdate(queryObj, MongoDBUtil.createDBObj(entity));

	return this.findEntityById(entity.getId());
    }


    @Override
    public void delete(String entityId) throws DALException {

	log.debug("Entering 'delete'");
	if (entityId == null) {

	    throw new EntityNotFoundException(
		    "Entity doesn't have an '_id'");
	}

	this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(LocationDO.class)).deleteOne(
			new BasicDBObject("_id", new ObjectId(entityId)));
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#delete(java.lang.Object)
     */
    @Override
    public void delete(LocationDO entity) throws DALException {

	this.delete(entity.getId());
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.location.dao.LocationDAO#findAll()
     */
    @Override
    public Collection<LocationDO> findAll() throws DALException {
	// TODO Auto-generated method stub
	return null;
    }
}
