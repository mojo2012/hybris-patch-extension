package at.spot.hybris.patch.jalo;

import de.hybris.platform.testframework.HybrisJUnit4TransactionalTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JUnit Tests for the Patch extension.
 */
public class PatchTest extends HybrisJUnit4TransactionalTest
{
	/** Edit the local|project.properties to change logging behaviour (properties log4j2.logger.*). */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(PatchTest.class);

	@Before
	public void setUp()
	{
		// implement here code executed before each test
	}

	@After
	public void tearDown()
	{
		// implement here code executed after each test
	}

	/**
	 * This is a sample test method.
	 */
	@Test
	public void testPatch()
	{
		final boolean testTrue = true;
		Assert.assertTrue(testTrue);
	}
}
