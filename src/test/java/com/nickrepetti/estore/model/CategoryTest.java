package com.nickrepetti.estore.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import java.util.Map;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	private Category categoryA;
	private Category categoryB;
	private Category categoryC;
	private Category notCategoryA;

	@Before
	public void setup() {
		categoryA = createCategory(new Long(0), "Televisions");
		categoryB = createCategory(new Long(0), "Televisions");
		categoryC = createCategory(new Long(0), "Televisions");
		notCategoryA = createCategory(new Long(4), "Audio");
	}
	
	@Test
	public void testEquals_isReflexive() {
		assertTrue("Class should be equal to itself.", categoryA.equals(categoryA));
	}
	
	@Test
	public void testEquals_incompatibleTypeComparison() {
		assertFalse("Passing incompatible object should return false", categoryA.equals("Test"));
	}
	
	@Test
	public void testEquals_nullComparison() {
		assertFalse("Passing null should return false", categoryA.equals(null));
	}
	
	@Test
	public void testEquals_isSymmetric() {
		assertTrue("Symmetric test fail: a != b", categoryA.equals(categoryB));
		assertTrue("Symmetric test fail: b != a", categoryB.equals(categoryA));
	}
	
	@Test
	public void testEquals_isTransitive() {
		assertTrue("Transitive test fail: a != b", categoryA.equals(categoryB));
		assertTrue("Transitive test fail: b != c", categoryB.equals(categoryC));
		assertTrue("Transitive test fail: a != c", categoryA.equals(categoryC));
	}
	
	@Test
	public void testEquals_isConsistent() {
		assertTrue("Consistent test fail: a != b", categoryA.equals(categoryB));
		assertTrue("Consistent test fail: a != b", categoryA.equals(categoryB));
		assertTrue("Consistent test fail: a != b", categoryA.equals(categoryB));
		assertFalse("Consistent test fail: a == !a", categoryA.equals(notCategoryA));
		assertFalse("Consistent test fail: a == !a", categoryA.equals(notCategoryA));
		assertFalse("Consistent test fail: a == !a", categoryA.equals(notCategoryA));
	}
	
	@Test
	public void testHashCode_sameObjectsHaveSameHashCodes() {
		assertTrue("Same object hash code fail: a != b", categoryA.equals(categoryB));
		
		int aHashCode = categoryA.hashCode();
		int bHashCode = categoryB.hashCode();
		
		assertEquals("Same object hash code fail: aHashCode != bHashCode", aHashCode, bHashCode);
	}
	
	@Test
	public void testHashCode_differentObjectsHaveDifferentHashCodes() {
		assertFalse("Different object hash code fail: a == b", categoryA.equals(notCategoryA));
		
		int aHashCode = categoryA.hashCode();
		int notAHashCode = notCategoryA.hashCode();
		
		assertTrue("Different object hash code fail: aHashCode == notAHashCode", (aHashCode != notAHashCode));
	}
	
	@Test
	public void testHashCode_isConsistent() {
		int initialHashCode = categoryA.hashCode();
		
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, categoryA.hashCode());
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, categoryA.hashCode());
	}
	
	private Category createCategory(Long id, String name) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		
		return category;
	}
}
