/**
 * 
 */
package gz.nozing.library.dal.author.dao;

import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.common.BaseDAO;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.util.annotation.DefaultImplementationClass;

import java.util.List;

/**
 * @author nozing
 *
 */
@DefaultImplementationClass(
	implementationClass = "gz.nozing.library.dal.author.dao.AuthorDAOImpl"
	)
public interface AuthorDAO extends BaseDAO<AuthorDO> {
    
    /**
     * @param criteria
     * @return
     * @throws DALException
     */
    List<AuthorDO> findByExample(AuthorDO criteria) throws DALException;
    
    /**
     * @param paginatedCriteria
     * @return
     * @throws DALException
     */
    PaginationResultDTO<AuthorDO> findByCriteria(PaginationSearchDTO<AuthorDO> paginatedCriteria) throws DALException;
}
