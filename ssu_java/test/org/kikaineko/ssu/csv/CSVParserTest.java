package org.kikaineko.ssu.csv;

import java.util.List;

import org.kikaineko.ssu.csv.CSVParser;

import junit.framework.TestCase;

public class CSVParserTest extends TestCase {
	public void test単純(){
		List l=CSVParser.lineParse("1,2,3");
		assertEquals(3, l.size());
		assertEquals("1", l.get(0));
		assertEquals("2", l.get(1));
		assertEquals("3", l.get(2));
	}
	public void test一つくくってみる(){
		List l=CSVParser.lineParse("1,\"2\",3");
		assertEquals(3, l.size());
		assertEquals("1", l.get(0));
		assertEquals("2", l.get(1));
		assertEquals("3", l.get(2));
		
		l=CSVParser.lineParse("1,\"2\",\"3\"");
		assertEquals(3, l.size());
		assertEquals("1", l.get(0));
		assertEquals("2", l.get(1));
		assertEquals("3", l.get(2));
		
		l=CSVParser.lineParse("1.0,\"pwd_lock_list\"");
		assertEquals(2, l.size());
		assertEquals("1.0", l.get(0));
		assertEquals("pwd_lock_list", l.get(1));
	}
	public void testくくった中にカンマ(){
		List l=CSVParser.lineParse("1,\"2,2\",3");
		assertEquals(3, l.size());
		assertEquals("1", l.get(0));
		assertEquals("2,2", l.get(1));
		assertEquals("3", l.get(2));
	}
	public void test空白もひろう(){
		List l=CSVParser.lineParse(",1,\"2,2\",,3,");
		assertEquals(6, l.size());
		assertEquals("", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2,2", l.get(2));
		assertEquals("", l.get(3));
		assertEquals("3", l.get(4));
		assertEquals("", l.get(5));
	}
	
	public void test途中にDQがある(){
		List l=CSVParser.lineParse("a,\"b\"\"c");
		assertEquals("a", l.get(0));
		assertEquals("b\"c", l.get(1));
		
		l=CSVParser.lineParse("a,\"b\"\"\"\"c");
		assertEquals("a", l.get(0));
		assertEquals("b\"\"c", l.get(1));
	}
}
