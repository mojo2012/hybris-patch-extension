/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 06.01.2018 15:07:54                         ---
 * ----------------------------------------------------------------
 */
package at.spot.hybris.patch.jalo;

import at.spot.hybris.patch.constants.PatchConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link at.spot.hybris.patch.jalo.Patch PatchExecution}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPatch extends GenericItem
{
	/** Qualifier of the <code>PatchExecution.uid</code> attribute **/
	public static final String UID = "uid";
	/** Qualifier of the <code>PatchExecution.author</code> attribute **/
	public static final String AUTHOR = "author";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(UID, AttributeMode.INITIAL);
		tmp.put(AUTHOR, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PatchExecution.author</code> attribute.
	 * @return the author - The author of the patch.
	 */
	public String getAuthor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AUTHOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PatchExecution.author</code> attribute.
	 * @return the author - The author of the patch.
	 */
	public String getAuthor()
	{
		return getAuthor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PatchExecution.author</code> attribute. 
	 * @param value the author - The author of the patch.
	 */
	public void setAuthor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AUTHOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PatchExecution.author</code> attribute. 
	 * @param value the author - The author of the patch.
	 */
	public void setAuthor(final String value)
	{
		setAuthor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PatchExecution.uid</code> attribute.
	 * @return the uid - The unique uid.
	 */
	public String getUid(final SessionContext ctx)
	{
		return (String)getProperty( ctx, UID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PatchExecution.uid</code> attribute.
	 * @return the uid - The unique uid.
	 */
	public String getUid()
	{
		return getUid( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PatchExecution.uid</code> attribute. 
	 * @param value the uid - The unique uid.
	 */
	public void setUid(final SessionContext ctx, final String value)
	{
		setProperty(ctx, UID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PatchExecution.uid</code> attribute. 
	 * @param value the uid - The unique uid.
	 */
	public void setUid(final String value)
	{
		setUid( getSession().getSessionContext(), value );
	}
	
}
