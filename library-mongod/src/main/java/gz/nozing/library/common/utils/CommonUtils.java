/**
 * 
 */
package gz.nozing.library.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>Clase utilidad que contiene m�todos de uso com�n en la aplicaci�n</p>
 * 
 * @author nozing
 *
 */
public class CommonUtils {

    /**
     * <p>M�todo que comprueba si una cadena est� vac�a o es <code>NULL</code></p>
     * 
     * @param string <code>java.lang.String</code> con la cadena a comprobar
     * @return Devuelve <code>TRUE</code> si la cadena es <code>""</code> o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(String string) {

	return (string != null && "".equals(string));
    }

    /**
     * <p>M�todo que comprueba si una lista est� vac�a o es <code>NULL</code></p>
     * 
     * @param list <code>java.util.List</code> con la lista a comprobar
     * @return Devuelve <code>TRUE</code> si la lista es vac�a o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(List<?> list) {

	return (list == null || list.size() == 0);
    }

    /**
     * <p>M�todo que comprueba si una collection est� vac�a o es <code>NULL</code></p>
     * 
     * @param collection <code>java.util.Collection</code> con la colecci�n a comprobar
     * @return Devuelve <code>TRUE</code> si la colecci�n es vac�a o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(Collection<?> collection) {

	return (collection == null || collection.size() == 0);
    }

    /**
     * <p>M�todo que comprueba si un conjunto est� vac�o o es <code>NULL</code></p>
     * 
     * @param set <code>java.util.Set</code> con el conjunto a comprobar
     * @return Devuelve <code>TRUE</code> si el conjunto est� vac�o o si
     * es <code>NULL</code> y <code>FALSE</code> en caso contrario
     */
    public static boolean isEmpty(Set<?> set) {

	return (set == null || set.size() == 0);
    }
}
