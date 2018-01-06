package com.kps.hybris.patch.service.impl;

import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.annotation.AnnotationUtils;

import com.kps.hybris.patch.AbstractPatch;
import com.kps.hybris.patch.annotation.PatchDefinition;
import com.kps.hybris.patch.exception.PatchException;
import com.kps.hybris.patch.model.PatchExecutionModel;
import com.kps.hybris.patch.service.PatchService;


public class DefaultPatchService implements PatchService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultPatchService.class);

	protected List<String> patchScanPaths;

	@Resource
	protected FlexibleSearchService flexibleSearchService;

	@Resource
	protected ModelService modelService;

	@Override
	public void executeUnappliedPatches(final Process systemSetupPhase, final Type patchType) throws PatchException
	{
		Map<PatchDefinition, AbstractPatch> patches;

		try
		{
			patches = getPatches();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new PatchException("Scan for patches failed", e);
		}

		final Map<String, PatchExecutionModel> alreadyExecutedPatches = getPatchExecutions();

		for (final Map.Entry<PatchDefinition, AbstractPatch> patch : patches.entrySet())
		{
			try
			{
				if (patch.getKey().setupPhase().equals(systemSetupPhase) && patch.getKey().patchType().equals(patchType)
						&& !patch.getKey().runOnce() || alreadyExecutedPatches.get(patch.getKey().uid()) == null)
				{
					patch.getValue().execute();
					final PatchExecutionModel exeution = modelService.create(PatchExecutionModel.class);
					exeution.setUid(patch.getKey().uid());
					exeution.setAuthor(patch.getKey().author());

					modelService.save(exeution);
				}
			}
			catch (final Exception e)
			{
				LOG.error(String.format("Could not execute patch %s", patch.getKey().uid()), e);
			}
		}
	}

	protected Map<String, PatchExecutionModel> getPatchExecutions()
	{
		final FlexibleSearchQuery qry = new FlexibleSearchQuery(
				String.format("SELECT {pk} FROM {%s}", PatchExecutionModel._TYPECODE));

		final SearchResult<PatchExecutionModel> result = flexibleSearchService.search(qry);

		final Map<String, PatchExecutionModel> map = result.getResult().stream()
				.collect(Collectors.toMap(PatchExecutionModel::getUid,
						Function.identity()));

		return map;
	}

	protected Map<PatchDefinition, AbstractPatch> getPatches() throws InstantiationException, IllegalAccessException
	{
		final Map<PatchDefinition, AbstractPatch> patches = new TreeMap<>(PATCH_COMPARATOR);

		for (final String scanPath : patchScanPaths)
		{
			final Reflections reflections = new Reflections(scanPath);
			final Set<Class<? extends AbstractPatch>> annotated = reflections.getSubTypesOf(AbstractPatch.class);

			for (final Class<? extends AbstractPatch> c : annotated)
			{
				final PatchDefinition patchDescription = AnnotationUtils.findAnnotation(c, PatchDefinition.class);

				patches.put(patchDescription, c.newInstance());
			}
		}

		return patches;
	}

	protected static final Comparator<PatchDefinition> PATCH_COMPARATOR = new Comparator<PatchDefinition>()
	{
		@Override
		public int compare(final PatchDefinition o1, final PatchDefinition o2)
		{
			return o1.uid().compareTo(o2.uid());
		}
	};

	public List<String> getPatchScanPaths()
	{
		return patchScanPaths;
	}

	@Required
	public void setPatchScanPaths(final List<String> patchScanPaths)
	{
		this.patchScanPaths = patchScanPaths;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
