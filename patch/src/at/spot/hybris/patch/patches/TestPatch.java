package at.spot.hybris.patch.patches;

import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;

import org.apache.log4j.Logger;

import at.spot.hybris.patch.AbstractPatch;
import at.spot.hybris.patch.annotation.PatchDefinition;


@PatchDefinition(uid = "TestPatch", author = "matthias.fuchs", patchType = Type.ESSENTIAL, setupPhase = Process.UPDATE)
public class TestPatch extends AbstractPatch
{
	private static final Logger LOG = Logger.getLogger(TestPatch.class);

	@Override
	public void execute() throws Exception
	{
		LOG.info("This is a test patch");

	}

}
