package gz.nozing.library.dal.common;

import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;

import com.mongodb.client.MongoDatabase;

/** Interfaz parametrizada que define los m�todos b�sicos para persistir las 
 * entidades
 * 
 * @author fparreno
 *
 * @param <T> Indica el tipo de entidad sobre la que trabajar� esta intefaz
 */
public interface BaseDAO <T> {

    void setDatabase(MongoDatabase database);

    /** M�todo que busca una entidad por su identificador
     * 
     * @param entityId <code>java.lang.String</code> con el valor del identificador
     * de la entidad que queremos recuperar
     * @return Instancia de un objeto de tipo <code>T</code> con los datos de la entidad
     * buscada
     * @throws EntityNotFoundException Excepci�n que se lanza cuando no se 
     * encuentra una entidad asociada al identificador indicado
     */
    T findEntityById(String entityId) throws EntityNotFoundException, DALException;

    /** M�todo que persiste los datos de una entidad
     * 
     * @param entity <code>T</code> con los datos que se persistir�n
     * @return <code>T</code> con los datos que se han persistido y los valores
     * que se han modificado/creado tras la inserci�n
     * @throws DAOException Excepci�n que se lanza cuando 
     */
    T save(T entity) throws DALException;

    /** M�todo que actualiza los datos de una entidad. Si la entidad no existe, 
     * entonces la crear�
     * 
     * @param entity <code>T</code> que contiene los datos que queremos actualizar.
     * @return <code>T</code> que contiene la nueva versi�n de la entidad actualizada
     */
    T update (T entity) throws DALException;

    /** M�todo que elimina una entidad
     * 
     * @param entity <code>T</code> que contiene los datos de la entidad a eliminar
     */
    void delete(T entity) throws DALException;

    /** M�todo que elimina una entidad en funci�n de su identificador
     * 
     * @param entityId <code>java.lang.String</code> que contiene el 
     * identificador de la entidad a elimina
     * @throws EntityNotFoundException Excepci�n que se lanza cuando no se encuentra
     * la entidad indicada para borrar
     * @throws MethodParameterIsEmptyException Excepci�n que se lanza cuando 
     * el par�metro est� vac�o
     */
    void delete(String entityId) throws DALException;
}
