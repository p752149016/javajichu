package twenty;

import java.util.HashSet;

import org.junit.Test;

public class Exercise5 extends HashSet<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Test
	public void initialization(){
		assert isEmpty();
	}
	@Test
	public void _contains(){
		add("one");
		assert contains("one");
	}
	@Test
	public void _remove(){
		add("one");
		remove("one");
		assert isEmpty();
	}
}
