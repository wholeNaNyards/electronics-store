package com.nickrepetti.estore.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import java.util.Map;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private Product productA;
	private Product productB;
	private Product productC;
	private Product notProductA;

	@Before
	public void setup() {
		productA = createProduct(new Long(0), "Awesome Television", 
			"A really cool television.", new BigDecimal(400), 4, 10, 20);
		productB = createProduct(new Long(0), "Awesome Television", 
			"A really cool television.", new BigDecimal(400), 4, 10, 20);
		productC = createProduct(new Long(0), "Awesome Television", 
			"A really cool television.", new BigDecimal(400), 4, 10, 20);
		notProductA = createProduct(new Long(4), "Decent Television", 
			"A decent television.", new BigDecimal(100), 2, 14, 25);
	}
	
	@Test
	public void testEquals_isReflexive() {
		assertTrue("Class should be equal to itself.", productA.equals(productA));
	}
	
	@Test
	public void testEquals_incompatibleTypeComparison() {
		assertFalse("Passing incompatible object should return false", productA.equals("Test"));
	}
	
	@Test
	public void testEquals_nullComparison() {
		assertFalse("Passing null should return false", productA.equals(null));
	}
	
	@Test
	public void testEquals_isSymmetric() {
		assertTrue("Symmetric test fail: a != b", productA.equals(productB));
		assertTrue("Symmetric test fail: b != a", productB.equals(productA));
	}
	
	@Test
	public void testEquals_isTransitive() {
		assertTrue("Transitive test fail: a != b", productA.equals(productB));
		assertTrue("Transitive test fail: b != c", productB.equals(productC));
		assertTrue("Transitive test fail: a != c", productA.equals(productC));
	}
	
	@Test
	public void testEquals_isConsistent() {
		assertTrue("Consistent test fail: a != b", productA.equals(productB));
		assertTrue("Consistent test fail: a != b", productA.equals(productB));
		assertTrue("Consistent test fail: a != b", productA.equals(productB));
		assertFalse("Consistent test fail: a == !a", productA.equals(notProductA));
		assertFalse("Consistent test fail: a == !a", productA.equals(notProductA));
		assertFalse("Consistent test fail: a == !a", productA.equals(notProductA));
	}
	
	@Test
	public void testHashCode_sameObjectsHaveSameHashCodes() {
		assertTrue("Same object hash code fail: a != b", productA.equals(productB));
		
		int aHashCode = productA.hashCode();
		int bHashCode = productB.hashCode();
		
		assertEquals("Same object hash code fail: aHashCode != bHashCode", aHashCode, bHashCode);
	}
	
	@Test
	public void testHashCode_differentObjectsHaveDifferentHashCodes() {
		assertFalse("Different object hash code fail: a == !a", productA.equals(notProductA));
		
		int aHashCode = productA.hashCode();
		int notAHashCode = notProductA.hashCode();
		
		assertTrue("Different object hash code fail: aHashCode == notAHashCode", (aHashCode != notAHashCode));
	}
	
	@Test
	public void testHashCode_isConsistent() {
		int initialHashCode = productA.hashCode();
		
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, productA.hashCode());
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, productA.hashCode());
	}
	
	private Product createProduct(Long id, String name, String description,
		BigDecimal price, int rating, int totalProductCount, int subtotal) {
		
		Product product = new Product();
		
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setRating(rating);
		product.setTotalProductCount(totalProductCount);
		product.setSubtotal(subtotal);
	
		Image image = new Image();
		image.setId(new Long(4));
		image.setName("tv-sm.jpg");
		
		product.setImage(image);
	
		Map<Long, Boolean> categories = new HashMap();
		categories.put(new Long(2), true);
		categories.put(new Long(1), true);
		
		product.setCategories(categories);
		
		return product;
	}
}
