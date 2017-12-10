/**
 * 
 */
package gz.nozing.library.dal.common;

import gz.nozing.library.dal.exception.DALException;

import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;

/**
 * @author nozing
 *
 */
public abstract class BaseDAOFinderImpl<T> implements BaseDAO<T> {

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#setDatabase(com.mongodb.client.MongoDatabase)
     */
    @Override
    public void setDatabase(MongoDatabase database) {

	throw new UnsupportedOperationException(
		"'setDatabase' operation is not supported");
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#save(java.lang.Object)
     */
    @Override
    public T save(T entity) throws DALException {

	throw new UnsupportedOperationException(
		"'save' operation is not supported");
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#update(java.lang.Object)
     */
    @Override
    public T update(T entity) throws DALException {

	throw new UnsupportedOperationException(
		"'update' operation is not supported");
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#delete(java.lang.Object)
     */
    @Override
    public void delete(T entity) throws DALException {

	throw new UnsupportedOperationException(
		"'delete' operation is not supported");
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#delete(java.lang.String)
     */
    @Override
    public void delete(String entityId) throws DALException {

	throw new UnsupportedOperationException(
		"'delete' operation is not supported");
    }
}
