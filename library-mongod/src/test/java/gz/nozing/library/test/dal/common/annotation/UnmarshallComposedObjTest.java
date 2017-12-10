package gz.nozing.library.test.dal.common.annotation;

import java.util.HashSet;
import java.util.Set;

import gz.nozing.library.dal.common.annotation.MongoDBUtil;
import gz.nozing.library.test.dal.common.annotation.dto.ComposedComplexObj;
import gz.nozing.library.test.dal.common.annotation.dto.ComposedSimpleObj;
import gz.nozing.library.test.dal.common.annotation.dto.IntegerObj;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class UnmarshallComposedObjTest {

    @Test
    public void unmarshalComposedSimpleObj() {
	
	IntegerObj integerObj = new IntegerObj(1234);
	
	ComposedSimpleObj composedObj = new ComposedSimpleObj("attribute", integerObj);
	
	BasicDBObject basicInt = new BasicDBObject();
	basicInt.put("att", integerObj.getIntegerAtt());
	
	BasicDBObject dbObj = new BasicDBObject();
	dbObj.put("stringAtt", composedObj.getStringAtt());
	dbObj.put("integerAtt", basicInt);
	
	ComposedSimpleObj newObj = MongoDBUtil.unmarshall(dbObj, ComposedSimpleObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertNotNull("Attribute 'stringAtt' can't be null",
		newObj.getStringAtt());
	Assert.assertNotNull("Attribute 'integerAttDO' can't be null",
		newObj.getIntegerAttDO());
	Assert.assertNotNull("Attribute 'integerAtt' can't be null", newObj
		.getIntegerAttDO().getIntegerAtt());
	Assert.assertEquals("Object's are not equals", composedObj, newObj);
    }
    
    @Test
    public void unmarshalComposedComplexObj() {
	
	IntegerObj integerObj = new IntegerObj(1234);
	
	Set<IntegerObj> set = new HashSet<IntegerObj>();
	set.add(integerObj);
	
	ComposedComplexObj composedObj = new ComposedComplexObj("attribute", set);
	
	BasicDBObject basicInt = new BasicDBObject();
	basicInt.put("att", integerObj.getIntegerAtt());
	
	BasicDBList basicList = new BasicDBList();
	basicList.add(basicInt);
	
	BasicDBObject dbObj = new BasicDBObject();
	dbObj.put("stringAtt", composedObj.getStringAtt());
	dbObj.put("setAtt", basicList);
	
	ComposedComplexObj newObj = MongoDBUtil.unmarshall(dbObj, ComposedComplexObj.class);
	
	Assert.assertNotNull("Can't be null", newObj);
	Assert.assertNotNull("Attribute 'stringAtt' can't be null",
		newObj.getStringAtt());
	Assert.assertNotNull("Attribute 'integerAttDO' can't be null",
		newObj.getSetAttDO());
	Assert.assertEquals("Object's are not equals", 
		composedObj.getSetAttDO(), newObj.getSetAttDO());
    }
}
