package com.kps.hybris.patch.service;

import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;

import com.kps.hybris.patch.exception.PatchException;


public interface PatchService
{
	void executeUnappliedPatches(Process systemSetupPhase, Type patchType) throws PatchException;
}
