package com.sujithpaul.junit5demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * From apache/commons-collections
 * 
 */
@DisplayName("Tests for ListUtils")
public class ListUtilsTest {
    private static final String a = "a";
    private static final String b = "b";
    private static final String c = "c";
    private static final String d = "d";
    private static final String e = "e";

    private String[] fullArray;
    private List<String> fullList;

    @BeforeEach
    void setUp() {
        fullArray = new String[]{a, b, c, d, e};
        fullList = new ArrayList<String>(Arrays.asList(fullArray));
    }

    /**
     * Tests intersecting a non-empty list with an empty list.
     */
    @Test
    void testIntersectNonEmptyWithEmptyList() {
        final List<String> empty = Collections.<String>emptyList();
        assertTrue(ListUtils.intersection(empty, fullList).isEmpty(), "result not empty");
    }

    /**
     * Tests intersecting a empty list with an empty list.
     */
    @Test
    void testIntersectEmptyWithEmptyList() {
        final List<?> empty = Collections.EMPTY_LIST;
        assertTrue(ListUtils.intersection(empty, empty).isEmpty(), "result not empty");
    }

    /**
     * Tests intersecting a non-empty list with an subset of itself.
     */
    @Test
    void testIntersectNonEmptySubset() {
        // create a copy
        final List<String> other = new ArrayList<String>(fullList);

        // remove a few items
        assertNotNull(other.remove(0));
        assertNotNull(other.remove(1));

        // make sure the intersection is equal to the copy
        assertEquals(other, ListUtils.intersection(fullList, other));
    }

    /**
     * Tests intersecting a non-empty list with a list of different types.
     */
    @Test
    void testIntersectListWithNoOverlapAndDifferentTypes() {
        @SuppressWarnings("boxing")
        final List<Integer> other = Arrays.asList(1, 23);
        assertTrue(ListUtils.intersection(fullList, other).isEmpty());
    }

    /**
     * Tests intersecting a non-empty list with itself.
     */
    @Test
    void testIntersectListWithSelf() {
        assertEquals(fullList, ListUtils.intersection(fullList, fullList));
    }

    /**
     * Tests intersecting two lists in different orders.
     */
    @Test
    void testIntersectionOrderInsensitivity() {
        final List<String> one = new ArrayList<String>();
        final List<String> two = new ArrayList<String>();
        one.add("a");
        one.add("b");
        two.add("a");
        two.add("a");
        two.add("b");
        two.add("b");
        assertEquals(ListUtils.intersection(one,two),ListUtils.intersection(two, one));
    }

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
