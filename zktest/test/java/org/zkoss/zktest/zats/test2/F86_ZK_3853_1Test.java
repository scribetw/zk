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
public class F86_ZK_3853_1Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		click(jq(".z-treerow").eq(0));
		waitResponse();
		click(jq(".z-treerow .z-tree-icon").eq(0));
		waitResponse();
		click(jq(".z-treerow .z-tree-icon").eq(1));
		waitResponse();
		for (int i = 0; i < 10; i++) {
			assertTrue(jq(".z-treerow").eq(i).hasClass("z-treerow-selected"));
		}

		click(jq(".z-treerow").eq(0));
		waitResponse();
		for (int i = 0; i < 10; i++) {
			assertFalse(jq(".z-treerow").eq(i).hasClass("z-treerow-selected"));
		}

		click(jq(".z-treerow").eq(2));
		waitResponse();
		for (int i = 0; i < 2; i++) {
			assertTrue(jq(".z-treerow").eq(i).hasClass("z-treerow-partial"));
		}

		click(jq("$ind"));
		waitResponse();
		for (int i = 0; i < 10; i++) {
			assertFalse(jq(".z-treerow").eq(i).hasClass("z-treerow-selected"));
			assertFalse(jq(".z-treerow").eq(i).hasClass("z-treerow-partial"));
		}

		click(jq(".z-treerow").eq(0));
		waitResponse();
		for (int i = 1; i < 10; i++) {
			assertFalse(jq(".z-treerow").eq(i).hasClass("z-treerow-selected"));
		}
	}
}
