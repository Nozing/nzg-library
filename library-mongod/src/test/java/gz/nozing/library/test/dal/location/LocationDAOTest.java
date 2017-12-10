/**
 * 
 */
package gz.nozing.library.test.dal.location;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;
import gz.nozing.library.dal.location.LocationDO;
import gz.nozing.library.dal.location.dao.LocationDAO;
import gz.nozing.library.dal.location.dao.LocationDAOImpl;
import gz.nozing.library.dal.shelf.ShelfDO;
import gz.nozing.library.dal.shelving.ShelvingDO;
import gz.nozing.library.test.dal.util.TestDALUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

/**
 * @author nozing
 *
 */
public class LocationDAOTest {

    private static Logger log = Logger.getLogger(LocationDAOTest.class);

    private LocationDAO locationDAO;

    private List<LocationDO> locations;

    public LocationDAOTest() {

	this.locationDAO = new LocationDAOImpl(
		TestDALUtils.instance().getDatabase());
	this.locations = new LinkedList<LocationDO>();
    }

    @Test
    public void createTest() throws Exception {

	log.info("Test started");

	LocationDO location = this.createCompleteLocation();

	LocationDO newLocation = this.locationDAO.save(location);

	this.locations.add(location);

	Assert.assertNotNull("New location 'id' can't be null. ",
		newLocation.getId());
	Assert.assertEquals(
		location.getCode(), newLocation.getCode());

	/* Compruebo las estanterías */
	for (ShelvingDO origShelving : location.getShelvings()) {

	    log.info("Checking shelving '" + origShelving.getCode() + "'");
	    Assert.assertTrue("shelving '" + origShelving.getCode() + "' missing", 
		    newLocation.getShelvings().contains(origShelving));

	    /* Para cada estantería, compruebo que los estantes son los mismos */
	    for (ShelvingDO retShelving : newLocation.getShelvings()) {

		if (origShelving.equals(retShelving)) {

		    for (ShelfDO origShelf : origShelving.getShelfs()) {

			log.info("Checking shelf '" + origShelf.getCode() + "'");
			Assert.assertTrue("shelf '" + origShelf.getCode() + "' missing", 
				retShelving.getShelfs().contains(origShelf));
		    }
		}
	    }
	}

	log.info("Test finished");
    }

    @Test
    public void findByIdTest() throws Exception {

	log.info("Test started");
	
	LocationDO location = this.locationDAO.save(
		this.createCompleteLocation());

	this.locations.add(location);

	LocationDO searchedLocation = 
		this.locationDAO.findEntityById(location.getId());

	Assert.assertNotNull("Location searched can't be null", searchedLocation);
	Assert.assertEquals("Locations aren't equals", 
		searchedLocation, location);

	/* Compruebo las estanterías */
	for (ShelvingDO origShelving : location.getShelvings()) {

	    log.info("Checking shelving '" + origShelving.getCode() + "'");
//	    Assert.assertTrue("shelving '" + origShelving.getCode() + "' missing", 
//		    searchedLocation.getShelvings().contains(origShelving));

	    boolean shelvingFound = false;
	    /* Para cada estantería, compruebo que los estantes son los mismos */
	    for (ShelvingDO retShelving : searchedLocation.getShelvings()) {
		
		if (origShelving.equals(retShelving)) {
		    
		    shelvingFound = true;
		    for (ShelfDO origShelf : origShelving.getShelfs()) {

			log.info("Checking shelf '" + origShelf.getCode() + "'");
//			Assert.assertTrue("shelf '" + origShelf.getCode() + "' missing", 
//				retShelving.getShelfs().contains(origShelf));
		    }
		}
	    }
	    
	    Assert.assertTrue(String.format("'%s' has not been retrieved", origShelving.getCode()), shelvingFound);
	}

	log.info("Test finished");
    }

    @Test
    public void updateLocationTest() throws Exception {

	log.info("Test started");

	LocationDO loc = this.locationDAO.save(this.createCompleteLocation());
	this.locations.add(loc);

	log.info("Testing code location update");
	loc.setCode("LOCMOD");

	LocationDO updatedLoc = this.locationDAO.update(loc);

	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);
	Assert.assertTrue("'code' has not been updated", 
		loc.getCode().equals(updatedLoc.getCode()));

	log.info("Test finished");
    }

    @Test
    public void updateShelvingLocationTest() throws Exception {

	log.info("Test started");

	LocationDO loc = this.locationDAO.save(this.createCompleteLocation());
	this.locations.add(loc);

	ShelvingDO shelving = loc.getShelvings().iterator().next();
	loc.getShelvings().remove(shelving);

	LocationDO updatedLoc = this.locationDAO.update(loc);

	/* Probamos que podemos eliminar una estanteria del objeto */
	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);
	Assert.assertFalse("'shelving' has not been updated", 
		updatedLoc.getShelvings().contains(shelving));

	loc.getShelvings().add(shelving);

	updatedLoc = this.locationDAO.update(loc);

	/* Probamos que podemos añadir una estanteria del objeto */
	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);
	Assert.assertTrue("'shelving' has not been updated", 
		updatedLoc.getShelvings().containsAll(loc.getShelvings()));

	/* Probamos a actualizar un campo de la estanteria */
	log.info("Testing a shelving atribute update");
	shelving = loc.getShelvings().iterator().next();
	shelving.setCode("SHELV02");

	updatedLoc = this.locationDAO.update(loc);

	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);

	boolean equalFound = false;
	for (ShelvingDO retShelving : updatedLoc.getShelvings()) {

	    if (shelving.equals(retShelving)) {

		equalFound = true;
		break;
	    }
	}

	Assert.assertTrue("'shelving.code' has not been updated", equalFound);

	log.info("Test finished");
    }

    @Test
    public void updateShelfLocationTest() throws Exception {

	log.info("Test started");

	LocationDO loc = this.locationDAO.save(this.createCompleteLocation());
	this.locations.add(loc);	

	/* Probamos que podemos eliminar una estanteria del estante */
	log.info("Testing a whole shelf update");
	ShelvingDO origShelving = loc.getShelvings().iterator().next();
	ShelfDO origShelf = origShelving.getShelfs().iterator().next();
	origShelving.getShelfs().remove(origShelf);

	LocationDO updatedLoc = this.locationDAO.update(loc);

	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);
	for (ShelvingDO retShelving : updatedLoc.getShelvings()) {

	    if (origShelving.equals(retShelving)) {

		Assert.assertFalse("'shelf' has not been updated", 
			retShelving.getShelfs().contains(origShelf));
	    }
	}

	/* Probamos que podemos añadir una estantería al estante */
	origShelving.getShelfs().add(origShelf);
	updatedLoc = this.locationDAO.update(loc);

	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);
	for (ShelvingDO retShelving : updatedLoc.getShelvings()) {

	    if (origShelving.equals(retShelving)) {

		Assert.assertTrue("'shelf' has not been updated", 
			retShelving.getShelfs().contains(origShelf));
	    }
	}

	/* Probamos a modificar un campo del estante */
	origShelf.setCode("CODE02");
	updatedLoc = this.locationDAO.update(loc);

	Assert.assertNotSame("Objects can't be the same", updatedLoc, loc);
	for (ShelvingDO retShelving : updatedLoc.getShelvings()) {

	    if (origShelving.equals(retShelving)) {

		Assert.assertTrue("'shelf' has not been updated", 
			retShelving.getShelfs().contains(origShelf));

		for (ShelfDO retShelf : retShelving.getShelfs()) {

		    if (origShelf.equals(retShelf)) {

			Assert.assertTrue("'shelf.code' with differents values", origShelf.getCode().equals(retShelf.getCode()));
			Assert.assertTrue("'shelf.description' with differents values", origShelf.getDescription().equals(retShelf.getDescription()));
		    }
		}
	    }
	}

	log.info("Test finished");
    }

    @Test
    public void deleteTest() throws Exception {

	log.info("Test started");
	LocationDO locationTest01 = new LocationDO();
	locationTest01.setCode("LOC01");
	locationTest01.setDescription("Object location 01");

	LocationDO locationCreated = this.locationDAO.save(locationTest01);

	this.locations.add(locationCreated);

	boolean deleted = false;
	this.locationDAO.delete(locationCreated);

	try {

	    this.locationDAO.findEntityById(locationCreated.getId());

	} catch (EntityNotFoundException enfe) {
	    deleted = true;
	}

	if (!deleted) {

	    Assert.fail("Location not deleted");
	}

	log.info("Test finished");
    }

    /**
     * <p>Method that removes the data inserted for testing</p>
     * 
     * @throws DALException
     */
    @After
    public void tearDown () throws Exception {

	log.info("Excuting 'tearDown' method");
	if (!CommonUtils.isEmpty(this.locations)) {

	    for (LocationDO loc : this.locations) {

		log.info("Deleting location '" + loc.getId() + "'");
		this.locationDAO.delete(loc);
		log.info("Location '" + loc.getId() + "' deleted");
	    }
	}
    }

    private LocationDO createCompleteLocation() {

	Set<ShelvingDO> shelvings = new HashSet<ShelvingDO>();
	Set<ShelfDO> shelves = new HashSet<ShelfDO>();

	ShelfDO shelf = new ShelfDO();
	shelf.setCode("SHELF01");
	shelf.setDescription("Object shelf 01");
	shelves.add(shelf);

	shelf = new ShelfDO();
	shelf.setCode("SHELF02");
	shelf.setDescription("Object shelf 02");
	shelves.add(shelf);

	ShelvingDO shelving = new ShelvingDO();
	shelving.setCode("SHELVING01");
	shelving.setDescription("Object shelving 01");

	shelving.setShelfs(shelves);

	shelvings.add(shelving);

	shelves = new HashSet<ShelfDO>();

	shelf = new ShelfDO();
	shelf.setCode("SHELF01");
	shelf.setDescription("Object shelf 01");
	shelves.add(shelf);

	shelving = new ShelvingDO();
	shelving.setCode("SHELVING02");
	shelving.setDescription("Object shelving 02");

	shelving.setShelfs(shelves);

	shelvings.add(shelving);

	LocationDO location = new LocationDO();
	location.setCode("LOC01");
	location.setDescription("Object location 01");
	location.setShelvings(shelvings);

	return location;
    }
}
