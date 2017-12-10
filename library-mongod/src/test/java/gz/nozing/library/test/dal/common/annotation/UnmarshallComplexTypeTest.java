package gz.nozing.library.test.dal.common.annotation;

import gz.nozing.library.dal.common.annotation.MongoDBUtil;
import gz.nozing.library.test.dal.common.annotation.dto.SetOfDateObj;
import gz.nozing.library.test.dal.common.annotation.dto.SetOfStringObj;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.SetUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class UnmarshallComplexTypeTest {

    private static Logger log = Logger.getLogger(UnmarshallComplexTypeTest.class);
    
    @Test
    public void unmarshalSetOfString() {
	
	Set<String> setAttr = new HashSet<String>();
	setAttr.add("value1");
	setAttr.add("value2");
	
	SetOfStringObj obj = new SetOfStringObj(setAttr);
	
	BasicDBList basicDBList = new BasicDBList();
	basicDBList.add("value1");
	basicDBList.add("value2");
	
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", basicDBList);
	log.trace(basicDBObj.toString());
	
	SetOfStringObj newObj = MongoDBUtil.unmarshall(basicDBObj, SetOfStringObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertNotNull("Can't be null", newObj.getSetAttr());
	Assert.assertTrue("Results have to be equals", 
		SetUtils.isEqualSet(obj.getSetAttr(), newObj.getSetAttr()));;
    }
    
    @Test
    public void unmarshalSetOfDate() {
	
	Date date1 = new Date(12345);
	Date date2 = new Date(67890);
	
	Set<Date> setAttr = new HashSet<Date>();
	setAttr.add(date1);
	setAttr.add(date2);
	
	SetOfDateObj obj = new SetOfDateObj(setAttr);
	
	BasicDBList basicDBList = new BasicDBList();
	basicDBList.add(date1);
	basicDBList.add(date2);
	
	BasicDBObject basicDBObj = new BasicDBObject();
	basicDBObj.put("att", basicDBList);
	log.trace(basicDBObj.toString());
	
	SetOfStringObj newObj = MongoDBUtil.unmarshall(basicDBObj, SetOfStringObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertNotNull("Can't be null", newObj.getSetAttr());
	Assert.assertTrue("Results have to be equals", 
		SetUtils.isEqualSet(obj.getSetAttr(), newObj.getSetAttr()));
    }
}
