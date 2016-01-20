package com.nickrepetti.estore.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import java.util.Map;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ImageTest {

	private Image imageA;
	private Image imageB;
	private Image imageC;
	private Image notImageA;

	@Before
	public void setup() {
		imageA = createImage(new Long(0), "tv-lg.jpg");
		imageB = createImage(new Long(0), "tv-lg.jpg");
		imageC = createImage(new Long(0), "tv-lg.jpg");
		notImageA = createImage(new Long(4), "tv-sm.jpg");
	}
	
	@Test
	public void testEquals_isReflexive() {
		assertTrue("Class should be equal to itself.", imageA.equals(imageA));
	}
	
	@Test
	public void testEquals_incompatibleTypeComparison() {
		assertFalse("Passing incompatible object should return false", imageA.equals("Test"));
	}
	
	@Test
	public void testEquals_nullComparison() {
		assertFalse("Passing null should return false", imageA.equals(null));
	}
	
	@Test
	public void testEquals_isSymmetric() {
		assertTrue("Symmetric test fail: a != b", imageA.equals(imageB));
		assertTrue("Symmetric test fail: b != a", imageB.equals(imageA));
	}
	
	@Test
	public void testEquals_isTransitive() {
		assertTrue("Transitive test fail: a != b", imageA.equals(imageB));
		assertTrue("Transitive test fail: b != c", imageB.equals(imageC));
		assertTrue("Transitive test fail: a != c", imageA.equals(imageC));
	}
	
	@Test
	public void testEquals_isConsistent() {
		assertTrue("Consistent test fail: a != b", imageA.equals(imageB));
		assertTrue("Consistent test fail: a != b", imageA.equals(imageB));
		assertTrue("Consistent test fail: a != b", imageA.equals(imageB));
		assertFalse("Consistent test fail: a == !a", imageA.equals(notImageA));
		assertFalse("Consistent test fail: a == !a", imageA.equals(notImageA));
		assertFalse("Consistent test fail: a == !a", imageA.equals(notImageA));
	}
	
	@Test
	public void testHashCode_sameObjectsHaveSameHashCodes() {
		assertTrue("Same object hash code fail: a != b", imageA.equals(imageB));
		
		int aHashCode = imageA.hashCode();
		int bHashCode = imageB.hashCode();
		
		assertEquals("Same object hash code fail: aHashCode != bHashCode", aHashCode, bHashCode);
	}
	
	@Test
	public void testHashCode_differentObjectsHaveDifferentHashCodes() {
		assertFalse("Different object hash code fail: a == b", imageA.equals(notImageA));
		
		int aHashCode = imageA.hashCode();
		int notAHashCode = notImageA.hashCode();
		
		assertTrue("Different object hash code fail: aHashCode == notAHashCode", (aHashCode != notAHashCode));
	}
	
	@Test
	public void testHashCode_isConsistent() {
		int initialHashCode = imageA.hashCode();
		
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, imageA.hashCode());
		assertEquals("Consistent test fail: initialHashCode != hashCode", initialHashCode, imageA.hashCode());
	}
	
	private Image createImage(Long id, String name) {
		Image image = new Image();
		image.setId(id);
		image.setName(name);
		
		return image;
	}
}
