/**
 * 
 */
package gz.nozing.library.dal.common.annotation.mongo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Anotación que permitirá indicar a qué colección de la base de datos está
 * asociada la clase anotada</p>
 * 
 * @author nozing
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DBCollection {

    /**
     * @return <code>java.lang.String</code> con el nombre de la colección
     */
    String name();

}
