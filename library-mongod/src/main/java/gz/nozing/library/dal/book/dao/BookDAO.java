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
     * <p>Método que devuelve un conjunto de libros que responden al
     * criterio indicado</p>
     * 
     * @param bookCriteria <code>{@link BookDO}</code> que se usara como
     * criterio de búsqueda
     * @return Devuelve una <code>java.util.List&lt;{@link BookDO}></code> con 
     * los libros o una lista vacía en caso de no encontrar ninguna
     * @throws DALException
     */
    List<BookDO> find(BookDO bookCriteria) throws DALException;
    
    /**
     * <p>Método que dada una configuración de paginación con un <code>{@link 
     * BookDO}</code> para usar como criterio devolverá un resultado según esos
     * criterios</p>
     * 
     * @param paginationCriteria <code>{@link PaginationSearchDTO}</code> de
     * <code>{@link BookDO}</code> que contiene la configuración a buscar en la
     * base de datos
     * @return Devuelve un <code>{@link PaginationResultDTO} de <code>@link 
     * BookDO</code> con el resultado de la búsqueda
     * @throws DALException
     */
    PaginationResultDTO<BookDO> findBooksPaginated(
	    PaginationSearchDTO<BookDO> paginationCriteria) throws DALException;
}
