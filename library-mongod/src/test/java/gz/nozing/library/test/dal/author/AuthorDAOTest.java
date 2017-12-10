/**
 * 
 */
package gz.nozing.library.test.dal.author;

import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.author.dao.AuthorDAO;
import gz.nozing.library.dal.author.dao.AuthorDAOImpl;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;
import gz.nozing.library.test.dal.util.TestDALUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

/**
 * @author nozing
 * 
 */
public class AuthorDAOTest {

    private static Logger log = Logger.getLogger(AuthorDAOTest.class);

    private AuthorDAO authorDAO;

    private List<AuthorDO> authors;

    public AuthorDAOTest() {

	this.authorDAO = new AuthorDAOImpl();
	this.authorDAO.setDatabase(TestDALUtils.instance().getDatabase());

	this.authors = new LinkedList<AuthorDO>();
    }

    @Test
    public void createTest() throws DALException {

	log.info("Test started");

	/* Creamos un autor sólo con nombre */
	AuthorDO newAuthor = new AuthorDO();
	newAuthor.setName("Author name");

	AuthorDO authorCreated = this.authorDAO.save(newAuthor);
	this.authors.add(authorCreated);

	Assert.assertNotNull("Author inserted not retrieved", authorCreated);
	Assert.assertNotNull("New 'id' can't be null", authorCreated.getId());

	Assert.assertEquals("'name' has been modified", newAuthor.getName(),
		authorCreated.getName());
	Assert.assertNull("'surname' has to be null",
		authorCreated.getSurname());
	Assert.assertNotNull("'creationDate' can't be null",
		authorCreated.getCreationDate());
	Assert.assertNotNull("'modificationDate' can't be null",
		authorCreated.getModificationDate());

	/* Creamos un autor sólo con nombre y apellidos */
	newAuthor = new AuthorDO();
	newAuthor.setName("Author2 name");
	newAuthor.setSurname("Author2 surname");

	authorCreated = this.authorDAO.save(newAuthor);
	this.authors.add(authorCreated);

	Assert.assertNotNull("Author inserted not retrieved", authorCreated);
	Assert.assertNotNull("New 'id' can't be null", authorCreated.getId());

	Assert.assertEquals("'name' has been modified", newAuthor.getName(),
		authorCreated.getName());
	Assert.assertEquals("'surname' has been modified", newAuthor.getName(),
		authorCreated.getName());
	Assert.assertNotNull("'creationDate' can't be null",
		authorCreated.getCreationDate());

	log.info("Test finished");
    }

    @Test
    public void findByIdTest() throws DALException {

	log.info("Test started");
	AuthorDO newAuthor = new AuthorDO();
	newAuthor.setName("Author2 name");
	newAuthor.setSurname("Author2 surname");

	newAuthor = this.authorDAO.save(newAuthor);
	this.authors.add(newAuthor);

	AuthorDO authorSearched = this.authorDAO.findEntityById(newAuthor
		.getId());

	Assert.assertNotNull("Author inserted not retrieved", authorSearched);
	Assert.assertNotNull("New 'id' can't be null", authorSearched.getId());

	Assert.assertEquals("'id' has been modified", newAuthor.getId(),
		authorSearched.getId());
	Assert.assertEquals("'name' has been modified", newAuthor.getName(),
		authorSearched.getName());
	Assert.assertEquals("'surname' has been modified", newAuthor.getName(),
		authorSearched.getName());
	Assert.assertEquals("'creationDate' has been modified",
		newAuthor.getCreationDate(), authorSearched.getCreationDate());
	Assert.assertEquals("'modificationDate' has been modified",
		newAuthor.getModificationDate(),
		authorSearched.getModificationDate());

	log.info("Test finished");
    }

    @Test
    public void updateTest() throws Exception {

	log.info("Test started");
	
	String originalName = "Author2 name";
	String originalSurname = "Author2 surname";
	
	AuthorDO newAuthor = new AuthorDO();
	newAuthor.setName(originalName);
	newAuthor.setSurname(originalSurname);

	newAuthor = this.authorDAO.save(newAuthor);
	this.authors.add(newAuthor);
	
	newAuthor.setName("Author2 newName");
	Date originalDate = newAuthor.getModificationDate();
	
	AuthorDO updatedAuthor = this.authorDAO.update(newAuthor);

	Assert.assertNotNull("Author inserted not retrieved", updatedAuthor);
	Assert.assertNotNull("New 'id' can't be null", updatedAuthor.getId());
	Assert.assertEquals("'id' has been modified", newAuthor.getId(),
		updatedAuthor.getId());

	Assert.assertFalse("'name' has not been updated", 
		originalName.equals(updatedAuthor.getName()));
	Assert.assertEquals("'surname'has not been updated",
		originalSurname, updatedAuthor.getSurname());
	Assert.assertEquals("'creationDate' has to be equals",
		newAuthor.getCreationDate(), updatedAuthor.getCreationDate());
	Assert.assertFalse(
		"'modificationDate' has not been updated",
		updatedAuthor.getModificationDate().equals(originalDate));
	
	Assert.assertTrue(
		"'modificationDate' can't be before initial date",
		updatedAuthor.getModificationDate().after(originalDate));

	log.info("Test finished");
    }

    @Test
    public void deleteEntityTest() throws DALException {

	log.info("Test started");
	AuthorDO newAuthor = new AuthorDO();
	newAuthor.setName("Author2 name");
	newAuthor.setSurname("Author2 surname");

	newAuthor = this.authorDAO.save(newAuthor);

	this.authorDAO.delete(newAuthor);

	boolean deleted = false;
	try {
	    
	    this.authorDAO.findEntityById(newAuthor.getId());
	} catch (EntityNotFoundException enfe) {
	    
	    deleted = true;
	}
	
	Assert.assertTrue("Author has not been deleted", deleted);
	log.info("Test finished");
    }

    @Test
    public void deleteByIdTest() throws DALException {

	log.info("Test started");
	AuthorDO newAuthor = new AuthorDO();
	newAuthor.setName("Author2 name");
	newAuthor.setSurname("Author2 surname");

	newAuthor = this.authorDAO.save(newAuthor);

	this.authorDAO.delete(newAuthor.getId());

	boolean deleted = false;
	try {
	    
	    this.authorDAO.findEntityById(newAuthor.getId());
	    
	} catch (EntityNotFoundException enfe) {
	    
	    deleted = true;
	}
	
	Assert.assertTrue("Author has not been deleted", deleted);
	log.info("Test finished");
    }

    /**
     * @throws DALException
     */
    @Test
    public void findByCriteriaTest() throws DALException {
	
	AuthorDO author = new AuthorDO();
	author.setName("Author1 name");
	author.setSurname("Author1 surname");

	this.authors.add(this.authorDAO.save(author));
	
	author = new AuthorDO();
	author.setName("Author2 name");
	author.setSurname("Author2 surname");
	
	this.authors.add(this.authorDAO.save(author));
	
	author = new AuthorDO();
	author.setName("Author3 name");
	author.setSurname("Author3 surname");
	
	this.authors.add(this.authorDAO.save(author));
	
	author = new AuthorDO();
	author.setName("Author4 name");
	author.setSurname("Author4 surname");
	
	this.authors.add(this.authorDAO.save(author));
	
	author = new AuthorDO();
	author.setName("Author5 name");
	author.setSurname("Author5 surname");
	
	this.authors.add(this.authorDAO.save(author));
	
	AuthorDO criteria = new AuthorDO();
	String nameCriteria = "AUTH";
	criteria.setName(nameCriteria);
	
	log.info("Testing filtering only by name");
	Integer initialPosition = 2;
	Integer pageSize = 2;
	PaginationSearchDTO<AuthorDO> pagination = 
		new PaginationSearchDTO<AuthorDO>(criteria, initialPosition, pageSize);
	
	PaginationResultDTO<AuthorDO> paginatedResult = 
		this.authorDAO.findByCriteria(pagination);
	
	Assert.assertNotNull("'initialPosition' can't be null", paginatedResult.getInitialPosition());
	Assert.assertEquals(initialPosition, paginatedResult.getInitialPosition());
	Assert.assertNotNull("'pageSize' can't be null", paginatedResult.getPageSize());
	Assert.assertEquals(pageSize, paginatedResult.getPageSize());
	Assert.assertNotNull("'numberOfReults' can't be null", paginatedResult.getNumberOfResults());
	
	Assert.assertNotNull("Result can't be null", paginatedResult.getResult());
	Assert.assertFalse("Result can't be empty", paginatedResult.getResult().isEmpty());
	
	Assert.assertEquals("Number of results unexpected", 
		pageSize, (Integer) paginatedResult.getResult().size());
	
	for (AuthorDO result : paginatedResult.getResult()) {
	    
	    Assert.assertNotNull("'id' can't be null", result.getId());
	    Assert.assertNotNull("'name' cant't be null", result.getName());
	    Assert.assertNotNull("'creationDate' can't be null", result.getCreationDate());
	    Assert.assertNotNull("'modificationDate' can't be null", result.getModificationDate());
	    
	    Assert.assertTrue("'name' daesn't match criteria",
		    result.getName().toUpperCase().startsWith(nameCriteria.toUpperCase()));
	}
	
//	log.info("Testing filtering by name and surname");
//	criteria = new AuthorDO();
//	nameCriteria = "AUTH";
//	criteria.setName(nameCriteria);
//	String surnameCriteria = "Author2 s";
//	criteria.setSurname(surnameCriteria);
//	
//	results = this.authorDAO.findByCriteria(criteria);
//	
//	Assert.assertNotNull("Result can't be null", results);
//	Assert.assertFalse("Result can't be empty", results.isEmpty());
//	
//	for (AuthorDO result : results) {
//	    
//	    Assert.assertNotNull("'id' can't be null", result.getId());
//	    Assert.assertNotNull("'name' cant't be null", result.getName());
//	    Assert.assertNotNull("'creationDate' can't be null", result.getCreationDate());
//	    Assert.assertNotNull("'modificationDate' can't be null", result.getModificationDate());
//	    
//	    Assert.assertTrue("'name' daesn't match criteria",
//		    result.getName().toUpperCase().startsWith(nameCriteria.toUpperCase()));
//	    Assert.assertTrue("'surname' daesn't match criteria",
//		    result.getSurname().toUpperCase().startsWith(surnameCriteria.toUpperCase()));
//	}
	
	log.info("Existing test");
    }
    
    /**
     * <p>
     * Method that removes the data inserted for testing
     * </p>
     * 
     * @throws DALException
     */
    @After
    public void tearDown() throws DALException {

	log.info("Entering 'tearDown'");
	for (AuthorDO author : this.authors) {

	    log.info("Deleting author '" + author.getId() + "'");
	    this.authorDAO.delete(author);
	    log.info("Author '" + author + "' deleted");
	}
	log.info("Exiting 'tearDown'");
    }
}
