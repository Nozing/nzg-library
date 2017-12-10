package gz.nozing.library.dal.common.annotation;

import gz.nozing.library.common.utils.exception.runtime.ClassNotAnnotatedRuntimeException;
import gz.nozing.library.common.utils.exception.runtime.ConfigurationRuntimeException;
import gz.nozing.library.common.utils.exception.runtime.FieldIncorrectAnnotatedRuntimeException;
import gz.nozing.library.common.utils.exception.runtime.InstantiatingClassException;
import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBCollection;
import gz.nozing.library.dal.common.annotation.mongo.DBField;
import gz.nozing.library.dal.common.annotation.mongo.DBKey;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/* TODO Deberiamos crear una serie de excepciones para controlar los errores de
 * una forma más elegante. Plantear el uso de RuntimeExceptions o */
/**
 * <p>
 * Clase utilidad que nos permite manejar los objetos de la base de datos
 * </p>
 * 
 * @author nozing
 * 
 */
public class MongoDBUtil {

    private static Logger log = Logger.getLogger(MongoDBUtil.class);

    /**
     * <p>
     * Método que devuelve el nombre de la colección en la base de datos a la
     * que está asociada la clase.
     * </p>
     * 
     * <p>
     * La clase debe estar correctamente anotada con la anotación <code>
     * {@link DBCollection}</code>.
     * </p>
     * 
     * @param clazz
     *            <code>java.lang.Class</code> de la cual queremos conocer su
     *            colección asociada
     * @return Devuelve un <code>java.lang.String</code> con el nombre de la
     *         colección
     * @throws ClassNotAnnotatedRuntimeException
     *             Excepción que se lanza cuando la clase indicada no está
     *             anotada con <code>{@link DBCollection}</code>
     */
    public static String getColletionNameFromEntity(final Class<?> clazz)
	    throws ClassNotAnnotatedRuntimeException {

	if (clazz.isAnnotationPresent(DBCollection.class)) {

	    return clazz.getAnnotation(DBCollection.class).name();
	} else {

	    throw new ClassNotAnnotatedRuntimeException("Annotation '"
		    + DBCollection.class.getName() + "' expected");
	}
    }

    /**
     * <p>
     * Método que extrae la información de los atributos de una clase que están
     * anotados para tratar con la base de datos
     * </p>
     * 
     * @param object
     *            <code>&lt;></code> con el objeto a mapear
     * @return Devuelve un <code>{@link Document}</code> que se podrá
     *         utilizar para consultar la base de datos con los valores que
     *         tenía el objeto
     * @throws ConfigurationRuntimeException
     *             Excepcion que se lanza cuando ocurre algún error en el
     *             tratamiento del objeto
     */
    public static <T extends BaseDO> Document createDBObj(final T object)
	    throws ConfigurationRuntimeException {

	return MongoDBUtil.marshall(object, true);
    }

    /**
     * <p>
     * Método que mapea un objeto a un <code>{@link BasicDBObject}</code>
     * </p>
     * 
     * @param object
     *            <code>&lt;></code> con el objeto a mapear
     * @param restrictive
     *            <code>java.lang.Boolean</code> que indica si se comprobarán
     *            las restricciones indicadas en los atributos [EN DESARROLLO]
     * @return Devuelve un <code>{@link BasicDBObject}</code> que se podrá
     *         utilizar para consultar la base de datos con los valores que
     *         tenía el objeto
     * @throws ConfigurationRuntimeException
     *             Excepcion que se lanza cuando ocurre algún error en el
     *             tratamiento del objeto
     * @throws FieldIncorrectAnnotatedRuntimeException
     *             Excepción que indica que alguno de los atributos de la clase
     *             no está correctamente anotado
     */
    @SuppressWarnings("unchecked")
    private static <T extends BaseDO> Document marshall(final T object,
	    boolean restrictive) throws ConfigurationRuntimeException,
	    FieldIncorrectAnnotatedRuntimeException {

	log.debug("Marshalling '" + object.getClass().getName() + "'");
	Document dbObj = null;

	Class<T> clazz = (Class<T>) object.getClass();

	/*
	 * Buscamos primero el atributo 'key' para, si existe, instanciar el
	 * objeto para la búsqueda correctamente
	 */
	for (Field field : clazz.getDeclaredFields()) {

	    field.setAccessible(true);
	    if (field.isAnnotationPresent(DBKey.class)) {

		log.trace("Field '" + field.getName() + "' is a key");

		try {
		    if (field.isAnnotationPresent(DBField.class)) {

			throw new FieldIncorrectAnnotatedRuntimeException(
				"Field '" + field.getName() + "' of class '"
					+ clazz.getName() + "'"
					+ "can't annotate as a field and a key");
		    }

		    if (field.get(object) != null) {

			dbObj = new Document("_id", new ObjectId(
				String.valueOf(field.get(object))));
		    }
		} catch (Exception exc) {

		    throw new ConfigurationRuntimeException(
			    "Error accessing field '" + field.getName()
				    + "' of class '" + clazz.getName() + "'",
			    exc);
		}
	    }
	}

	if (dbObj == null) {

	    dbObj = new Document();
	}

	/* Ahora buscamos los campos normales anotados con 'DBField' */
	for (Field field : clazz.getDeclaredFields()) {

	    field.setAccessible(true);

	    try {

		if (field.isAnnotationPresent(DBField.class)) {

		    log.trace("Field '" + field.getName()
			    + "' annotated as a field");

		    Object obj = field.get(object);
		    if (obj != null) {

			/* Comprobamos si el objeto es del dominio */
			if (obj instanceof BaseDO) {
			    
			    log.debug("Field is a BaseDO instance");
			    /*
			     * Si lo es, tendremos que mapearlo al objeto JSON
			     * segun las anotaciones
			     */
			    dbObj.append(field.getAnnotation(DBField.class)
				    .name(), MongoDBUtil.marshall((BaseDO) obj,
				    restrictive));

			} else if (obj instanceof Iterable<?>) {

			    log.debug("Field is an Iterable instance");
			    Set<Object> tmp = new HashSet<Object>(); 

			    for (Object objInSet : (Iterable<?>) obj) {

				if (objInSet instanceof BaseDO) {
				    tmp.add(MongoDBUtil.marshall((T) objInSet,
					    restrictive));
				} else {
				    tmp.add(objInSet);
				}
			    }

			    if (!tmp.isEmpty()) {
				dbObj.append(field.getAnnotation(DBField.class)
					.name(), tmp);
			    }
			} else {
			    
			    log.debug("Field is an unknown instance");
			    /* Si no lo conocemos, lo mapeamos directamente */
			    Object fieldContent = field.get(object);
			    if (fieldContent != null) {
				dbObj.append(field.getAnnotation(DBField.class)
				    .name(), fieldContent);
			    }
			}
		    } 
//		    else {
//			/* Si el valor del campo es NULL, entonces lo 
//			 * almacenamos como tal */
//			dbObj.append(field.getAnnotation(DBField.class)
//				.name(), null);
//		    }
		}
	    } catch (Exception exc) {

		throw new ConfigurationRuntimeException(
			"Error accessing field '" + field.getName()
				+ "' of class '" + clazz.getName() + "'", exc);
	    }
	}

	log.trace("dbObj generated: " + dbObj);
	return dbObj;
    }

    private static Field processSimpleType(BaseDO instance, Field field,
	    Object objToSet) {

	if (objToSet instanceof String) {

	    log.trace("Value of type '" + String.class.getName()
		    + "' retrieved from database");
	    String stringValue = (String) objToSet;

	    if (stringValue != "" && !stringValue.equals("null")) {

		log.trace("Value retrieved from database is not null: "
			+ stringValue);

		/* Buscamos el constructor al que se le pasa un sólo argumento
		 * de tipo String
		 */
		@SuppressWarnings("rawtypes")
		Constructor constructor = MongoDBUtil
			.findConstructorWithStringParam(field.getType());

		try {

		    field.set(instance, constructor.newInstance(stringValue));

		} catch (Exception exc) {

		    throw new ConfigurationRuntimeException(String.format(
			    "Error setting string '%s' to field '%s",
			    stringValue, field.getName()), exc);
		}
	    }
	} else {
	  
	    log.trace("Value of type '" + objToSet.getClass().getName()
		    + "' retrieved from database: '" + objToSet + "'");
	    
	    try {
		field.set(instance, objToSet);
	    } catch (Exception exc) {

		throw new ConfigurationRuntimeException(String.format(
			"Error seting '%s' to field '%s", objToSet.getClass().getName(),
			field.getName()), exc);
	    }
	}

	return field;
    }

    /**
     * <p>
     * Método que mapea información de la base de datos a un objeto del dominio
     * </p>
     * 
     * @param obj
     *            <code>{@ling DBObject}</code> que contiene la información
     *            extraída de la base de datos en crudo
     * @param clazz
     *            <code>java.lang.Class</code> que indica el tipo de objeto al
     *            que mapearemos la información de la base de datos
     * @return Devuelve una instancia de un objeto de tipo <code>&lt;T></code>
     *         con la información de la base de datos
     * @throws ConfigurationRuntimeException
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseDO> T unmarshall(final DBObject obj,
	    final Class<T> clazz) throws ConfigurationRuntimeException {

	T instance;
	try {
	    instance = clazz.newInstance();
	} catch (Exception exc) {

	    throw new ConfigurationRuntimeException(
		    "Error instantiating class '" + clazz.getName() + "'", exc);
	}

	log.trace("Unmarshalling to '" + clazz.getName() + "'");

	/*
	 * Recorremos los atributos de la clase instanciada para comprobar si
	 * estan anotados y asignarles los valores apropiados
	 */
	for (Field field : instance.getClass().getDeclaredFields()) {

	    field.setAccessible(true);

	    /* Variable donde guardaremos lo que hemos recuperado de la BBDD */
	    Object objValue = null;
	    /* Comprobamos si el atributo está anotado como clave */
	    if (field.isAnnotationPresent(DBKey.class)) {

		if (field.isAnnotationPresent(DBField.class)) {
		    /*
		     * Si además esta anotado como un campo normal, devolvemos
		     * un error
		     */
		    throw new FieldIncorrectAnnotatedRuntimeException("Field '"
			    + field.getName() + "' of class '"
			    + clazz.getName() + "'"
			    + "can't annotate as a field and a key");
		} else {

		    objValue = String.valueOf(obj.get("_id"));
		}

	    } else if (field.isAnnotationPresent(DBField.class)) {
		/*
		 * Si está anotado como un 'field', cogeremos del objeto de la
		 * BBDD el valor asociado al nombre del campo
		 */
		log.trace("Working with '" + field.getName() + "' field");
		objValue = obj.get(field.getAnnotation(DBField.class).name());
	    }

	    try {

		/* Si la anotacion está presente, vemos si el objeto de la BBDD
		 * tiene un valor para este campo */
		if (objValue != null) {

		    if (objValue instanceof DBObject) {

			try {

			    log.trace("Value of type '"
				    + DBObject.class.getName()
				    + "' retrieved from database: '" + objValue
				    + "'");

			    DBObject dbObject = (DBObject) objValue;

			    @SuppressWarnings("rawtypes")
			    Class fieldClass = field.getType();

			    if (fieldClass.equals(Set.class)) {

				BasicDBList list = (BasicDBList) dbObject;

				Type fieldType = field.getGenericType();

				log.trace(String.format(
					"Type of field: '%s'", fieldType));

				Set<Object> set = new HashSet<Object>();
				if (CollectionUtils.isNotEmpty(list)) {

				    @SuppressWarnings("rawtypes")
				    Class paramTypeClass = (Class) ((ParameterizedType) fieldType)
					.getActualTypeArguments()[0];
				    
				    /* Si el contenido del conjunto es uns BasicDBObjetc,
				     * recorreremos la lista llamando a esta función 
				     * recursivamente */
				    if (BaseDO.class.isAssignableFrom(paramTypeClass)) {

					for (Object l : list) {
					    log.trace(String.format(
						    "Let's unmarhall '%s'", l));
					    set.add(MongoDBUtil.unmarshall(
						    (BasicDBObject) l,
						    paramTypeClass));
					}
				    } else {
					/* Si no lo es, asumimos que es un tipo simple */
					for (Object l : list) {
					    log.debug(l);
					    set.add(l);
					}
				    }
				}

				field.set(instance, set);

			    } else if (fieldClass.equals(List.class)) {

				log.trace("Special type '"
					+ List.class.getName() + "' found");
				throw new ConfigurationRuntimeException("'"
					+ List.class.getName()
					+ "' mapper not implemented");
			    } else if (fieldClass.equals(Collection.class)) {

				log.trace("Special type '"
					+ Collection.class.getName()
					+ "' found");
				throw new ConfigurationRuntimeException("'"
					+ Collection.class.getName()
					+ "' mapper not implemented");
			    } else {

				log.trace("Making the assumption that it is a BaseDO...");
				field.set(instance, MongoDBUtil.unmarshall(
					dbObject, ((T) field.getType()
						.newInstance()).getClass()));
			    }

			} catch (Exception exc) {

			    throw new ConfigurationRuntimeException(
				    "Error instantiating class from field '"
					    + field.getName() + "'", exc);
			}
		    } else {
			/* En caso de que no sea un DBObject, lo seteamos directamente
			 * en el atributo de la instancia */
			processSimpleType(instance, field, objValue);
		    }
		} else {

		    log.trace("No value retrieved from database");
		}
	    } catch (Exception e) {

		throw new InstantiatingClassException("Can't instantiate '"
			+ clazz.getCanonicalName() + "'. "
			+ "Error trying to set to '" + field.getName()
			+ "' (type '" + field.getType().getName()
			+ "') value '" + objValue + "'", e);
	    }
	}

	return instance;
    }

    @SuppressWarnings("unchecked")
    private static <T> Constructor<T> findConstructorWithStringParam(
	    final Class<T> type) throws ConfigurationRuntimeException {

	/*
	 * TODO Podemos hacer una caché que almacene los constructores de tipos
	 * básicos con String
	 */
	for (Constructor<?> cons : type.getConstructors()) {

	    Class<?>[] parameters = cons.getParameterTypes();

	    if (parameters.length == 1
		    && parameters[0].getName().equals(String.class.getName())) {

		return (Constructor<T>) cons;
	    }
	}

	throw new ConfigurationRuntimeException(
		"Constructor with a parameter of type 'java.lang.String' "
			+ "not found in class '" + type.getCanonicalName()
			+ "'");
    }
}
