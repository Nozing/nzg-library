/**
 * 
 */
package gz.nozing.library.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>Clase utilidad que contiene métodos de uso común en la aplicación</p>
 * 
 * @author nozing
 *
 */
public class CommonUtils {

    /**
     * <p>Método que comprueba si una cadena está vacía o es <code>NULL</code></p>
     * 
     * @param string <code>java.lang.String</code> con la cadena a comprobar
     * @return Devuelve <code>TRUE</code> si la cadena es <code>""</code> o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(String string) {

	return (string != null && "".equals(string));
    }

    /**
     * <p>Método que comprueba si una lista está vacía o es <code>NULL</code></p>
     * 
     * @param list <code>java.util.List</code> con la lista a comprobar
     * @return Devuelve <code>TRUE</code> si la lista es vacía o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(List<?> list) {

	return (list == null || list.size() == 0);
    }

    /**
     * <p>Método que comprueba si una collection está vacía o es <code>NULL</code></p>
     * 
     * @param collection <code>java.util.Collection</code> con la colección a comprobar
     * @return Devuelve <code>TRUE</code> si la colección es vacía o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(Collection<?> collection) {

	return (collection == null || collection.size() == 0);
    }

    /**
     * <p>Método que comprueba si un conjunto está vacío o es <code>NULL</code></p>
     * 
     * @param set <code>java.util.Set</code> con el conjunto a comprobar
     * @return Devuelve <code>TRUE</code> si el conjunto está vacío o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(Set<?> set) {

	return (set == null || set.size() == 0);
    }
}
