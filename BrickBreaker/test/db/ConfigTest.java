package db;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigTest {
	private Config c;
	@Before
	public void setUp() throws Exception {
		c = new Config();
	}

	@After
	public void tearDown() throws Exception {
		c.delKey("TEST");
		c.Save();
		c = null;
	}
	@Test
	public void test_SetConfig_met_lege_value() {
		c.setConfig("TEST", "");
		assertEquals("0",c.getConfig("TEST"));
	}
	@Test
	public void test_GetConfig_Onbestaande_Key() {
		assertEquals("0",c.getConfig("ONBESTAAND"));
	}
	@Test
	public void test_GetConfig_Bestaande_Key() {
		c.setConfig("TEST", "TESTKEY");
		assertEquals("TESTKEY",c.getConfig("TEST"));
		c.delKey("TEST");
	}
	@Test
	public void test_setConfig_met_key_niet_in_toegestane_lijst() {
		c.setConfig("illegal", "illegalkey");
		assertEquals("0",c.getConfig("illegalkey"));
	}
	@Test
	public void test_SetConfig() {
		c.setConfig("TEST", "TESTKEY");
		assertEquals("TESTKEY",c.getConfig("TEST"));
		c.delKey("TEST");
	}
	
	@Test
	public void test_delKey() {
		c.setConfig("TEST", "TESTKEY");
		assertEquals("TESTKEY",c.getConfig("TEST"));
		c.delKey("TEST");
		assertEquals("0",c.getConfig("TEST"));
	}
	@Test
	public void test_delKey_empty_param()
	{
		c.delKey("");
	}
	
	@Test
	public void test_Save() throws IOException
	{
		c.setConfig("TEST", "TESTKEY");
		assertEquals("TESTKEY",c.getConfig("TEST"));
		c.Save();
	}
	public void test_Save_Load() throws IOException
	{
		c.setConfig("TEST", "TESTKEY");
		assertEquals("TESTKEY",c.getConfig("TEST"));
		c.Save();
		c.delKey("TEST");
		assertEquals("0",c.getConfig("TEST"));
		c.Load();
		assertEquals("TESTKEY",c.getConfig("TEST"));
	}
}
