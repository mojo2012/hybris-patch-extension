# Hybris Patch Extension

## Description
This extension allows to define so-called "patches" to be executed during system *initialization* and *update*. A patch is a java class that can do anything (import impex, modify item models, ...).

```
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
```

For a Class to be detected as *patch* it has to be annotated with `PatchDefinition`. The setupPhase property allows to define in which system initialization phase the patch should be executed.
The patchType defines which kind of patch this is (eg. essential data or sample data).
