package com.sujithpaul.junit5demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * From apache/commons-collections
 *
 */
public class ListUtils {
	/**
	 * <code>ListUtils</code> should not normally be instantiated.
	 */
	private ListUtils() {
	}

	// -----------------------------------------------------------------------

	/**
	 * Returns an immutable empty list if the argument is <code>null</code>, or the
	 * argument itself otherwise.
	 *
	 * @param      <T> the element type
	 * @param list the list, possibly <code>null</code>
	 * @return an empty list if the argument is <code>null</code>
	 */
	public static <T> List<T> emptyIfNull(final List<T> list) {
		return list == null ? Collections.<T>emptyList() : list;
	}

	/**
	 * Returns either the passed in list, or if the list is {@code null}, the value
	 * of {@code defaultList}.
	 *
	 * @param             <T> the element type
	 * @param list        the list, possibly {@code null}
	 * @param defaultList the returned values if list is {@code null}
	 * @return an empty list if the argument is <code>null</code>
	 * @since 4.0
	 */
	public static <T> List<T> defaultIfNull(final List<T> list, final List<T> defaultList) {
		return list == null ? defaultList : list;
	}

	/**
	 * Returns a new list containing all elements that are contained in both given
	 * lists.
	 *
	 * @param       <E> the element type
	 * @param list1 the first list
	 * @param list2 the second list
	 * @return the intersection of those two lists
	 * @throws NullPointerException if either list is null
	 */
	public static <E> List<E> intersection(final List<? extends E> list1, final List<? extends E> list2) {
		final List<E> result = new ArrayList<E>();

		List<? extends E> smaller = list1;
		List<? extends E> larger = list2;
		if (list1.size() > list2.size()) {
			smaller = list2;
			larger = list1;
		}

		final HashSet<E> hashSet = new HashSet<E>(smaller);

		for (final E e : larger) {
			if (hashSet.contains(e)) {
				result.add(e);
				hashSet.remove(e);
			}
		}
		return result;
	}

	/**
	 * Returns a new list containing the second list appended to the first list. The
	 * {@link List#addAll(Collection)} operation is used to append the two given
	 * lists into a new list.
	 *
	 * @param       <E> the element type
	 * @param list1 the first list
	 * @param list2 the second list
	 * @return a new list containing the union of those lists
	 * @throws NullPointerException if either list is null
	 */
	public static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2) {
		final ArrayList<E> result = new ArrayList<E>(list1);
		result.addAll(list2);
		return result;
	}

	/**
	 * Tests two lists for value-equality as per the equality contract in
	 * {@link java.util.List#equals(java.lang.Object)}.
	 * <p>
	 * This method is useful for implementing <code>List</code> when you cannot
	 * extend AbstractList. The method takes Collection instances to enable other
	 * collection types to use the List implementation algorithm.
	 * <p>
	 * The relevant text (slightly paraphrased as this is a static method) is:
	 * <blockquote> Compares the two list objects for equality. Returns {@code true}
	 * if and only if both lists have the same size, and all corresponding pairs of
	 * elements in the two lists are <i>equal</i>. (Two elements {@code e1} and
	 * {@code e2} are <i>equal</i> if <tt>(e1==null ? e2==null :
	 * e1.equals(e2))</tt>.) In other words, two lists are defined to be equal if
	 * they contain the same elements in the same order. This definition ensures
	 * that the equals method works properly across different implementations of the
	 * {@code List} interface. </blockquote>
	 *
	 * <b>Note:</b> The behaviour of this method is undefined if the lists are
	 * modified during the equals comparison.
	 *
	 * @see java.util.List
	 * @param list1 the first list, may be null
	 * @param list2 the second list, may be null
	 * @return whether the lists are equal by value comparison
	 */
	public static boolean isEqualList(final Collection<?> list1, final Collection<?> list2) {
		if (list1 == list2) {
			return true;
		}
		if (list1 == null || list2 == null || list1.size() != list2.size()) {
			return false;
		}

		final Iterator<?> it1 = list1.iterator();
		final Iterator<?> it2 = list2.iterator();
		Object obj1 = null;
		Object obj2 = null;

		while (it1.hasNext() && it2.hasNext()) {
			obj1 = it1.next();
			obj2 = it2.next();

			if (!(obj1 == null ? obj2 == null : obj1.equals(obj2))) {
				return false;
			}
		}

		return !(it1.hasNext() || it2.hasNext());
	}

	/**
	 * Generates a hash code using the algorithm specified in
	 * {@link java.util.List#hashCode()}.
	 * <p>
	 * This method is useful for implementing <code>List</code> when you cannot
	 * extend AbstractList. The method takes Collection instances to enable other
	 * collection types to use the List implementation algorithm.
	 *
	 * @see java.util.List#hashCode()
	 * @param list the list to generate the hashCode for, may be null
	 * @return the hash code
	 */
	public static int hashCodeForList(final Collection<?> list) {
		if (list == null) {
			return 0;
		}
		int hashCode = 1;
		final Iterator<?> it = list.iterator();

		while (it.hasNext()) {
			final Object obj = it.next();
			hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
		}
		return hashCode;
	}
}
