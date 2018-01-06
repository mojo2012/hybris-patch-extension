package com.kps.hybris.patch;

import de.hybris.platform.core.Registry;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;

import java.io.InputStream;


public abstract class AbstractPatch
{
	public abstract void execute() throws Exception;

	protected void importImpex(final String impexFilePath) throws ImpExException
	{
		try
		{
			final InputStream stream = getClass().getResourceAsStream(impexFilePath);

			final ImportConfig importConfig = new ImportConfig();
			importConfig.setScript(new StreamBasedImpExResource(stream, "UTF-8"));
			importConfig.setLegacyMode(Boolean.valueOf(true));

			final ImportResult importResult = getImportService().importData(importConfig);
			if (importResult.isError())
			{
				throw new ImpExException(String.format("Could not import file %s", impexFilePath));
			}
		}
		catch (final Exception e)
		{
			throw new ImpExException(e, String.format("Could not import file %s", impexFilePath), -1);
		}
	}

	protected ImportService getImportService()
	{
		return Registry.getApplicationContext().getBean(ImportService.class);
	}

	protected <T> T getBean(final Class<T> beanType)
	{
		return Registry.getApplicationContext().getBean(beanType);
	}

	protected <T> T getBean(final String beanId, final Class<T> beanType)
	{
		return Registry.getApplicationContext().getBean(beanId, beanType);
	}
}
