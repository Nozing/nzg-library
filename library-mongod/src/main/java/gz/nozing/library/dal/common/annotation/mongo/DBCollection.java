/**
 * 
 */
package gz.nozing.library.dal.common.annotation.mongo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Anotaci�n que permitir� indicar a qu� colecci�n de la base de datos est�
 * asociada la clase anotada</p>
 * 
 * @author nozing
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DBCollection {

    /**
     * @return <code>java.lang.String</code> con el nombre de la colecci�n
     */
    String name();

}
