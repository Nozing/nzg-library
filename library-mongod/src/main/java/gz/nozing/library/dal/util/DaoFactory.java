/**
 * 
 */
package gz.nozing.library.dal.util;

import gz.nozing.library.common.utils.exception.runtime.InstantiatingClassException;
import gz.nozing.library.dal.common.BaseDAO;
import gz.nozing.library.dal.util.annotation.DefaultImplementationClass;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/** <p>Factoría que se encarga de instanciar los DAOs</p>
 * 
 * <p>Para instanciar un DAO se le pasará como parámetro la clase interfaz
 * del DAO que queremos instanciar. Esta interfaz, tiene que ir anotada
 * con la clase <code>{@link DefaultImplementationClass}</code> donde se 
 * indica la clase concreta que implementa dicho DAO.</p>
 * 
 * <p>Las instancias de los DAO se cachearán en un mapa para optimizar
 * su acceso.</p>
 * 
 * @author fparreno
 *
 */
public class DaoFactory {

    private static Logger log = Logger.getLogger(DaoFactory.class);

    @SuppressWarnings("rawtypes")
    private static Map<String, BaseDAO> map = new HashMap<String, BaseDAO>();

    /** <p>Método que devuelve una instancia del DAO que se le indica como parámetro</p>
     * 
     * <p>En caso de que el DAO no esté en la caché interna, una vez instanciado lo 
     * almacenaremos en la misma.</p>
     * 
     * @param <T> Tipo de la interfaz que se va a instanciar
     * @param clazz <code>java.lang.Class</code> con la interfaz del DAO que se
     * quiere instanciar
     * @return Devuelve una instancia de tipo <code>&lt;T&gt;</code> que implementa
     * el DAO solicitado
     * @throws InstantiatingClassException Excepción que se lanza cuando la 
     * interfaz indicada no está correctamente anotada o cuando se produce un
     * error en la instanciación de la clase concreta
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final <T extends BaseDAO> T getDAO (Class<T> clazz) 
	    throws InstantiatingClassException {

	try {
	    String className = clazz.getCanonicalName();

	    log.trace("Asking for class '" + className + "'");
	    BaseDAO<T> classToInstance = map.get(className);
	    if (classToInstance == null) {

		log.trace("Calling factory");
		classToInstance = DaoFactory.instantiateDao(clazz);

		map.put(className, classToInstance);
	    }

	    return (T) classToInstance.getClass().newInstance();

	} catch (InstantiatingClassException ice) {

	    throw ice;
	}
	catch (Exception e) {

	    throw new InstantiatingClassException(
		    "No se puede instanciar la clase '" + clazz.getCanonicalName() + "'", e);
	}
    }

    /** <p>Método que instancia un DAO.</p> 
     * 
     * <p>Este método recupera la anotación <code>{@link DefaultImplementationClass}</code>
     * de la interfaz e instancia la clase indicada en ella. En caso de que la clase 
     * solicitada no está anotada, lanzaría la excepción {@link InstantiatingClassException}</p>
     * 
     * @param <T> Tipo de la interfaz que se va a instanciar
     * @param clazz <code>java.lang.Class</code> con la interfaz que indica el 
     * DAO que se quiere instanciar
     * @return Devuelve una instancia de una clase que implementa la interfaz indicada
     * @throws InstantiatingClassException Excepción que se lanza cuando la 
     * interfaz indicada no está correctamente anotada o cuando se produce un
     * error en la instanciación de la clase concreta
     */
    @SuppressWarnings("unchecked")
    private static <T> BaseDAO<T> instantiateDao(Class<T> clazz) throws InstantiatingClassException {

	DefaultImplementationClass dic = clazz.getAnnotation(DefaultImplementationClass.class);
	if (dic != null) {

	    String implClassName = dic.implementationClass();
	    log.trace("Trying to instantiate '" + implClassName + "' class");
	    try {

		return (BaseDAO<T>) Class.forName(implClassName).newInstance();

	    } catch (Exception e) {

		log.trace("Class '" + implClassName + "' can't be instantiated");
		log.trace(e);
		throw new InstantiatingClassException(
			"Class '" + implClassName + "' can't be instantiated", e);
	    }
	}
	log.trace("Class not annoted");
	throw new InstantiatingClassException(
		"Class '" + clazz + "' has not been properly annotated");
    }
}
