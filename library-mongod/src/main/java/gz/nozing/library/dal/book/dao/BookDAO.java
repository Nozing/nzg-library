/**
 * 
 */
package gz.nozing.library.dal.book.dao;


import gz.nozing.library.dal.book.BookDO;
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
	implementationClass = "gz.nozing.library.dal.book.dao.BookDAOImpl"
	)
public interface BookDAO extends BaseDAO<BookDO> {

    /**
     * <p>M�todo que devuelve un conjunto de libros que responden al
     * criterio indicado</p>
     * 
     * @param bookCriteria <code>{@link BookDO}</code> que se usara como
     * criterio de b�squeda
     * @return Devuelve una <code>java.util.List&lt;{@link BookDO}></code> con 
     * los libros o una lista vac�a en caso de no encontrar ninguna
     * @throws DALException
     */
    List<BookDO> find(BookDO bookCriteria) throws DALException;
    
    /**
     * <p>M�todo que dada una configuraci�n de paginaci�n con un <code>{@link 
     * BookDO}</code> para usar como criterio devolver� un resultado seg�n esos
     * criterios</p>
     * 
     * @param paginationCriteria <code>{@link PaginationSearchDTO}</code> de
     * <code>{@link BookDO}</code> que contiene la configuraci�n a buscar en la
     * base de datos
     * @return Devuelve un <code>{@link PaginationResultDTO} de <code>@link 
     * BookDO</code> con el resultado de la b�squeda
     * @throws DALException
     */
    PaginationResultDTO<BookDO> findBooksPaginated(
	    PaginationSearchDTO<BookDO> paginationCriteria) throws DALException;
}
