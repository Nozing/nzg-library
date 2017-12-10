/**
 * 
 */
package gz.nozing.library.dal.common.annotation.mongo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Anotación que permite indicar para un atributo de una clase el nombre
 * del campo en la base de datos en el que se almacerá el valor </p>
 * 
 * @author nozing
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DBField {


    /**
     * @return <code>java.lang.String</code> con el nombre del campo en la 
     * base de datos
     */
    String name();
}
