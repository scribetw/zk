/* IdGenerator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Jun 21 12:00:55     2007, Created by tomyeh
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.ui.sys;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Component;

/**
 * Used to generate UUID of components and pages and ID of desktops.
 *
 * @author tomyeh
 * @see Component#getUuid
 * @see Page#getUuid
 * @see Desktop#getId
 * @since 2.4.1
 */
public interface IdGenerator {
	/** Returns the next component UUID for the specified component,
	 * or null to generate the default UUID.
	 *
	 * <p>This method is called when {@link Component#getUuid}
	 * is called at the first time. It is usually when
	 * the component is attached to a page, unless the application
	 * invokes {@link Component#getUuid} earlier.
	 *
	 * @param desktop the current desktop (never null)
	 * @param comp the component (never null)
	 * @return the next component UUID, or null to generate the
	 * default UUID.
	 */
	public String nextComponentUuid(Desktop desktop, Component comp);
	/** Returns the next page UUID for the specified page,
	 * or null to generate the default UUID.
	 *
	 * <p>Note: we can retrieve the execution by use of
	 * {@link org.zkoss.zk.ui.Executions#getCurrent}.
	 *
	 * @return the next page UUID, or null to generate the
	 * default UUID.
	 */
	public String nextPageUuid(Page page);
	/** Returns the next desktop ID for the specified desktop,
	 * or null to generate the default UUID.
	 *
	 * <p>Note: we can retrieve the execution by use of
	 * {@link Desktop#getExecution}, or {@link org.zkoss.zk.ui.Executions#getCurrent}.
	 *
	 * @return the next desktop UUID, or null to generate the
	 * default UUID.
	 */
	public String nextDesktopId(Desktop desktop);
}
