/**
 * 
 */
package gz.nozing.library.dal.common.annotation.mongo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Anotación que nos permite indicar que el atributo anotado se corresponde
 * con la clave primaria de la base de datos</p>
 * 
 * @author nozing
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DBKey {

}
