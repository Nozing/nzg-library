package gz.nozing.library.dal.common;

import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;

import com.mongodb.client.MongoDatabase;

/** Interfaz parametrizada que define los métodos básicos para persistir las 
 * entidades
 * 
 * @author fparreno
 *
 * @param <T> Indica el tipo de entidad sobre la que trabajará esta intefaz
 */
public interface BaseDAO <T> {

    void setDatabase(MongoDatabase database);

    /** Método que busca una entidad por su identificador
     * 
     * @param entityId <code>java.lang.String</code> con el valor del identificador
     * de la entidad que queremos recuperar
     * @return Instancia de un objeto de tipo <code>T</code> con los datos de la entidad
     * buscada
     * @throws EntityNotFoundException Excepción que se lanza cuando no se 
     * encuentra una entidad asociada al identificador indicado
     */
    T findEntityById(String entityId) throws EntityNotFoundException, DALException;

    /** Método que persiste los datos de una entidad
     * 
     * @param entity <code>T</code> con los datos que se persistirán
     * @return <code>T</code> con los datos que se han persistido y los valores
     * que se han modificado/creado tras la inserción
     * @throws DAOException Excepción que se lanza cuando 
     */
    T save(T entity) throws DALException;

    /** Método que actualiza los datos de una entidad. Si la entidad no existe, 
     * entonces la creará
     * 
     * @param entity <code>T</code> que contiene los datos que queremos actualizar.
     * @return <code>T</code> que contiene la nueva versión de la entidad actualizada
     */
    T update (T entity) throws DALException;

    /** Método que elimina una entidad
     * 
     * @param entity <code>T</code> que contiene los datos de la entidad a eliminar
     */
    void delete(T entity) throws DALException;

    /** Método que elimina una entidad en función de su identificador
     * 
     * @param entityId <code>java.lang.String</code> que contiene el 
     * identificador de la entidad a elimina
     * @throws EntityNotFoundException Excepción que se lanza cuando no se encuentra
     * la entidad indicada para borrar
     * @throws MethodParameterIsEmptyException Excepción que se lanza cuando 
     * el parámetro está vacío
     */
    void delete(String entityId) throws DALException;
}
