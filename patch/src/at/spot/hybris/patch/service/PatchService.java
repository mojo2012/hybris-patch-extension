package at.spot.hybris.patch.service;

import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;

import at.spot.hybris.patch.exception.PatchException;


public interface PatchService
{
	void executeUnappliedPatches(Process systemSetupPhase, Type patchType) throws PatchException;
}
