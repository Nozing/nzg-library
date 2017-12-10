package gz.nozing.library.test.dal.common.annotation;

import java.util.Date;

import gz.nozing.library.dal.common.annotation.MongoDBUtil;
import gz.nozing.library.test.dal.common.annotation.dto.DateObj;
import gz.nozing.library.test.dal.common.annotation.dto.DoubleObj;
import gz.nozing.library.test.dal.common.annotation.dto.IntegerObj;
import gz.nozing.library.test.dal.common.annotation.dto.LongObj;
import gz.nozing.library.test.dal.common.annotation.dto.ShortObj;
import gz.nozing.library.test.dal.common.annotation.dto.StringObj;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.mongodb.BasicDBObject;

public class UnmarshallSimpleTypeTest {

    private static Logger log = Logger.getLogger(UnmarshallSimpleTypeTest.class);
    
    @Test
    public void unmarshalString() {
	
	/* Creamos el objeto que queremos que contenga el resultado de la 
	 * consulta de la BBDD */
	StringObj obj = new StringObj("value retrieved");
	
	/* Creamos el objeto devuelvo por la query a la BBDD */
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", obj.getStringAtt());
	log.trace(basicDBObj.toString());
	
	StringObj newObj = MongoDBUtil.unmarshall(basicDBObj, StringObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertEquals("Results have to be equals", obj, newObj);
    }
   
    @Test
    public void unmarshalDate() {
	
	/* Creamos el objeto que queremos que contenga el resultado de la 
	 * consulta de la BBDD */
	DateObj obj = new DateObj(new Date());
	
	/* Creamos el objeto devuelvo por la query a la BBDD */
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", obj.getDateAtt());
	log.trace(basicDBObj.toString());
	
	DateObj newObj = MongoDBUtil.unmarshall(basicDBObj, DateObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertEquals("Results have to be equals", obj, newObj);
    }
    
    @Test
    public void unmarshalInteger() {
	
	/* Creamos el objeto que queremos que contenga el resultado de la 
	 * consulta de la BBDD */
	IntegerObj obj = new IntegerObj(1234);
	
	/* Creamos el objeto devuelvo por la query a la BBDD */
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", obj.getIntegerAtt());
	log.trace(basicDBObj.toString());
	
	IntegerObj newObj = MongoDBUtil.unmarshall(basicDBObj, IntegerObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertEquals("Results have to be equals", obj, newObj);
    }
    
    @Test
    public void unmarshalLong() {
	
	/* Creamos el objeto que queremos que contenga el resultado de la 
	 * consulta de la BBDD */
	LongObj obj = new LongObj(1234L);
	
	/* Creamos el objeto devuelvo por la query a la BBDD */
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", obj.getLongAtt());
	log.trace(basicDBObj.toString());
	
	LongObj newObj = MongoDBUtil.unmarshall(basicDBObj, LongObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertEquals("Results have to be equals", obj, newObj);
    }
    
    @Test
    public void unmarshalDouble() {
	
	/* Creamos el objeto que queremos que contenga el resultado de la 
	 * consulta de la BBDD */
	DoubleObj obj = new DoubleObj(1234D);
	
	/* Creamos el objeto devuelvo por la query a la BBDD */
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", obj.getDoubleAtt());
	log.trace(basicDBObj.toString());
	
	DoubleObj newObj = MongoDBUtil.unmarshall(basicDBObj, DoubleObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertEquals("Results have to be equals", obj, newObj);
    }
    
    @Test
    public void unmarshalShort() {
	
	/* Creamos el objeto que queremos que contenga el resultado de la 
	 * consulta de la BBDD */
	ShortObj obj = new ShortObj(new Short("123"));
	
	/* Creamos el objeto devuelvo por la query a la BBDD */
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", obj.getShortAtt());
	log.trace(basicDBObj.toString());
	
	ShortObj newObj = MongoDBUtil.unmarshall(basicDBObj, ShortObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertEquals("Results have to be equals", obj, newObj);
    }
}
