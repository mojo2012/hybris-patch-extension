package at.spot.hybris.patch.annotation;

import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Describes the patch.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PatchDefinition
{
	/**
	 * The order of execution. The higher the value the less prioity the patch has.
	 */
	int order() default -1;

	/**
	 * The unique id of the patch.
	 */
	String uid();

	/**
	 * The author of the patch.
	 */
	String author() default "";

	/**
	 * Defines when in the system setup the patch is being executed.
	 */
	Process setupPhase();

	/**
	 * Defines the type patch..
	 */
	Type patchType();

	/**
	 * Defines if a patch only should be run once.
	 */
	boolean runOnce() default true;
}