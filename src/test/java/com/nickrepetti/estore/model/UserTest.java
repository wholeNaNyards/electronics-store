package com.nickrepetti.estore.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User userA;
	private User userB;
	private User userC;
	private User notUserA;

	@Before
	public void setup() {
		userA = createUser(new Long(0), "Super", "User", "sUser");
		userB = createUser(new Long(0), "Super", "User", "sUser");
		userC = createUser(new Long(0), "Super", "User", "sUser");
		notUserA = createUser(new Long(5), "John", "Doe", "jDoe");
	}
	
	@Test
	public void testEquals_isReflexive() {
		assertTrue("Class should be equal to itself.", userA.equals(userA));
	}
	
	@Test
	public void testEquals_incompatibleTypeComparison() {
		assertFalse("Passing incompatible object should return false", userA.equals("Test"));
	}
	
	@Test
	public void testEquals_nullComparison() {
		assertFalse("Passing null should return false", userA.equals(null));
	}
	
	@Test
	public void testEquals_isSymmetric() {
		assertTrue("Symmetric test fail: a != b", userA.equals(userB));
		assertTrue("Symmetric test fail: b != a", userB.equals(userA));
	}
	
	@Test
	public void testEquals_isTransitive() {
		assertTrue("Transitive test fail: a != b", userA.equals(userB));
		assertTrue("Transitive test fail: b != c", userB.equals(userC));
		assertTrue("Transitive test fail: a != c", userA.equals(userC));
	}
	
	@Test
	public void testEquals_isConsistent() {
		assertTrue("Consistent test fail: a != b", userA.equals(userB));
		assertTrue("Consistent test fail: a != b", userA.equals(userB));
		assertTrue("Consistent test fail: a != b", userA.equals(userB));
		assertFalse("Consistent test fail: a == !a", userA.equals(notUserA));
		assertFalse("Consistent test fail: a == !a", userA.equals(notUserA));
		assertFalse("Consistent test fail: a == !a", userA.equals(notUserA));
	}
	
	@Test
	public void testHashCode_sameObjectsHaveSameHashCodes() {
		assertTrue("Same object hash code fail: a != b", userA.equals(userB));
		
		int aHashCode = userA.hashCode();
		int bHashCode = userB.hashCode();
		
		assertEquals("Same object hash code fail: aHashCode != bHashCode", aHashCode, bHashCode);
	}
	
	@Test
	public void testHashCode_differentObjectsHaveDifferentHashCodes() {
		assertFalse("Different object hash code fail: a == b", userA.equals(notUserA));
		
		int aHashCode = userA.hashCode();
		int notAHashCode = notUserA.hashCode();
		
		assertTrue("Different object hash code fail: aHashCode == notAHashCode", (aHashCode != notAHashCode));
	}
	
	@Test
	public void testHashCode_isConsistent() {
		int initialHashCode = userA.hashCode();
		
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, userA.hashCode());
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, userA.hashCode());
	}
	
	private User createUser(Long id, String firstName, String lastName, String userName) {
		User user = new User();
		
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		
		Map<Long, Integer> cart = new HashMap();
		cart.put(new Long(14), 1);
		cart.put(new Long(3), 1);
		cart.put(new Long(10), 1);
		cart.put(new Long(6), 1);
		
		user.setCart(cart);
		
		return user;
	}
}
