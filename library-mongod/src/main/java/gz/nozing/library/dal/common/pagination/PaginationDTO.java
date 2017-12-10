/**
 * 
 */
package gz.nozing.library.dal.common.pagination;

import gz.nozing.library.dal.common.BaseDTO;

/**
 * <p>
 * Clase base para los objetos de paginación en BBDD
 * </p>
 * 
 * @author nozing
 *
 */
public abstract class PaginationDTO<T> extends BaseDTO {

    private static final long serialVersionUID = 738465640227857392L;
    
    private Integer intialPosition;
    private Integer pageSize;
    
    
    /**
     * <p>
     * Constructor por defecto
     * </p>
     * 
     * @param initialPosition <code>{@link Integer}</code> con la posición del primer 
     * elemento a recuperar
     * @param pageSize <code>{@link Integer}</code> con el número total de 
     * elementos a recuperar
     */
    public PaginationDTO(Integer initialPosition, Integer pageSize) {
	super();
	
	this.intialPosition = initialPosition;
	this.pageSize = pageSize;
    }

    /**
     * @return
     */
    public Integer getInitialPosition() {
	return intialPosition;
    }
    
    /**
     * @return
     */
    public Integer getPageSize() {
	return pageSize;
    }
}
