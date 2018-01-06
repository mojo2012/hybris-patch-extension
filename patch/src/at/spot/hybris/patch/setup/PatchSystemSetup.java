package at.spot.hybris.patch.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import at.spot.hybris.patch.constants.PatchConstants;
import at.spot.hybris.patch.exception.PatchException;
import at.spot.hybris.patch.service.PatchService;


/**
 * This class provides hooks into the system's initialization and update processes.
 */
@SystemSetup(extension = PatchConstants.EXTENSIONNAME)
public class PatchSystemSetup extends AbstractSystemSetup
{
	private static final Logger LOG = Logger.getLogger(PatchSystemSetup.class.getName());

	protected PatchService patchService;

	public PatchSystemSetup(final PatchService patchService)
	{
		this.patchService = patchService;
	}

	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		try
		{
			getPatchService().executeUnappliedPatches(context.getProcess(), context.getType());
		}
		catch (final PatchException e)
		{
			LOG.error("Could not execute patches for essential data.", e);
		}
	}

	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		try
		{
			getPatchService().executeUnappliedPatches(context.getProcess(), context.getType());
		}
		catch (final PatchException e)
		{
			LOG.error("Could not execute patches for project data.", e);
		}
	}

	/**
	 * Generates the Dropdown and Multi-select boxes for the project data import
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		//		params.add(createBooleanSystemSetupParameter(IMPORT_CUSTOM_REPORTS, "Import Custom Reports", true));

		return params;
	}

	public PatchService getPatchService()
	{
		return patchService;
	}
}
