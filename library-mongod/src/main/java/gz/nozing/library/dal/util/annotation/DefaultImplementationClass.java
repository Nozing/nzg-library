package gz.nozing.library.dal.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** <p>Clase anotación que permite indicar en una interfaz cual se la clase que
 * la implementa.</p>
 * 
 * @author fparreno
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultImplementationClass {

    /** <p>Método que devuelve el nombre de la clase que implementa la interfaz
     * anotada.</p>
     * 
     * @return <code>java.lang.String</code> con el nombre de la clase que
     * implementa la interfaz
     */
    String implementationClass();
}
