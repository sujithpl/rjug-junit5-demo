package com.sujithpaul.junit5demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * From apache/commons-collections
 * 
 */
@DisplayName("Tests for ListUtils")
public class ListUtilsTest {
	@Test
	@DisplayName("Test emptyIfNull")
	public void testEmptyIfNull() {
		assertTrue(ListUtils.emptyIfNull(null).isEmpty());

		final List<Long> list = new ArrayList<Long>();
		assertSame(list, ListUtils.emptyIfNull(list));
	}

	@Test
	@DisplayName("Test defaultIfNull")
	public void testDefaultIfNull() {
		assertTrue(ListUtils.defaultIfNull(null, Collections.emptyList()).isEmpty());

		final List<Long> list = new ArrayList<Long>();
		assertSame(list, ListUtils.defaultIfNull(list, Collections.<Long>emptyList()));
	}

	@Test
	@DisplayName("Test isEqualList")
	public void testEquals() {
		final Collection<String> data = Arrays.asList("a", "b", "c");

		final List<String> a = new ArrayList<String>(data);
		final List<String> b = new ArrayList<String>(data);

		assertEquals(true, a.equals(b));
		assertEquals(true, ListUtils.isEqualList(a, b));
		a.clear();
		assertEquals(false, ListUtils.isEqualList(a, b));
		assertEquals(false, ListUtils.isEqualList(a, null));
		assertEquals(false, ListUtils.isEqualList(null, b));
		assertEquals(true, ListUtils.isEqualList(null, null));
	}

	@Test
	@Disabled
	@DisplayName("Test hashCodeForList")
	public void testHashCode() {
		final Collection<String> data = Arrays.asList("a", "b", "c");

		final List<String> a = new ArrayList<String>(data);
		final List<String> b = new ArrayList<String>(data);

		assertEquals(true, a.hashCode() == b.hashCode());
		assertEquals(true, a.hashCode() == ListUtils.hashCodeForList(a));
		assertEquals(true, b.hashCode() == ListUtils.hashCodeForList(b));
		assertEquals(true, ListUtils.hashCodeForList(a) == ListUtils.hashCodeForList(b));
		a.clear();
		assertEquals(false, ListUtils.hashCodeForList(a) == ListUtils.hashCodeForList(b));
		assertEquals(0, ListUtils.hashCodeForList(null));
	}
}
