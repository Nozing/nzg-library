package gz.nozing.library.dal.location.dao;

import gz.nozing.library.dal.common.BaseDAO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.location.LocationDO;

import java.util.Collection;

/**
 * <p>
 * Interface donde se declaran las operaciones disponibles para manipular
 * las localizaciones
 * </p>
 * 
 * @see LocationDO
 * @author nozing
 *
 */
public interface LocationDAO extends BaseDAO<LocationDO> {

    /**
     * <p>
     * Metodo que devuelve todas las localizaciones disponibles en la
     * base de datos
     * </p>
     * 
     * @return <code>java.util.Collection&lt;{@link LocationDO}></code> con 
     * el resultado de la busqueda o una colección vacía en caso de que no 
     * exista ningún elemento
     * @throws DALException
     */
    Collection<LocationDO> findAll() throws DALException;
}
