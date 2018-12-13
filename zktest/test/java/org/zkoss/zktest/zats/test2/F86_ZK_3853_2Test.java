/* F86_ZK_3853_1Test.java

	Purpose:
		
	Description:
		
	History:
		Thu Dec 13 12:02:27 CST 2018, Created by rudyhuang

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.zkoss.zktest.zats.WebDriverTestCase;

/**
 * @author rudyhuang
 */
public class F86_ZK_3853_2Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		click(jq(".z-treerow").eq(1).toWidget().$n("cm"));
		waitResponse();
		assertFalse(jq(".z-treerow").eq(0).hasClass("z-treerow-selected"));
		for (int i = 1; i <= 5; i++) {
			assertTrue(jq(".z-treerow").eq(i).hasClass("z-treerow-selected"));
		}
		assertFalse(jq(".z-treerow").eq(6).hasClass("z-treerow-selected"));
		waitResponse();

		click(jq(".z-treerow").eq(2).toWidget().$n("cm"));
		waitResponse();
		for (int i = 0; i <= 4; i++) {
			assertFalse(Boolean.parseBoolean(jq(".z-treerow").eq(i).toWidget().eval("parent.isSelected()")));
		}
		assertTrue(Boolean.parseBoolean(jq(".z-treerow").eq(5).toWidget().eval("parent.isSelected()")));
		assertFalse(Boolean.parseBoolean(jq(".z-treerow").eq(6).toWidget().eval("parent.isSelected()")));
		assertTrue(jq(".z-treerow").eq(1).hasClass("z-treerow-partial"));

		click(jq("$ind"));
		waitResponse();
		for (int i = 0; i <= 6; i++) {
			assertFalse(Boolean.parseBoolean(jq(".z-treerow").eq(i).toWidget().eval("parent.isSelected()")));
			assertFalse(jq(".z-treerow").eq(i).hasClass("z-treerow-partial"));
		}

		click(jq(".z-treerow").eq(0).toWidget().$n("cm"));
		waitResponse();
		for (int i = 1; i <= 6; i++) {
			assertFalse(Boolean.parseBoolean(jq(".z-treerow").eq(i).toWidget().eval("parent.isSelected()")));
			assertFalse(jq(".z-treerow").eq(i).hasClass("z-treerow-partial"));
		}
	}
}
