package es.upm.grise.profunduzacion.cruiseController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import es.upm.grise.profundizacion.cruiseControl.CruiseControl;
import es.upm.grise.profundizacion.cruiseControl.IncorrectSpeedSetException;
import es.upm.grise.profundizacion.cruiseControl.Speedometer;
import es.upm.grise.profundizacion.cruiseControl.SpeedSetAboveSpeedLimitException;

class CruiseControlTest {
	
	private CruiseControl cruiseControl;
	private Speedometer speedometer;
	
	@BeforeEach
	public void setUp() {
		speedometer = new Speedometer();
		cruiseControl = new CruiseControl(speedometer);
	}
	
	// Test for initial state
	@Test
	public void testInitialStateSpeedSetIsNull() {
		assertNull(cruiseControl.getSpeedSet());
	}
	
	@Test
	public void testInitialStateSpeedLimitIsNull() {
		assertNull(cruiseControl.getSpeedLimit());
	}
	
	// Test for setSpeedSet with valid positive values
	@Test
	public void testSetSpeedSetWithPositiveValue() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(100);
		assertEquals(100, cruiseControl.getSpeedSet());
	}
	
	@Test
	public void testSetSpeedSetWithSmallPositiveValue() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(1);
		assertEquals(1, cruiseControl.getSpeedSet());
	}
	
	@Test
	public void testSetSpeedSetWithLargePositiveValue() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(250);
		assertEquals(250, cruiseControl.getSpeedSet());
	}
	
	// Test for setSpeedSet with zero (should throw IncorrectSpeedSetException)
	@Test
	public void testSetSpeedSetWithZeroThrowsException() {
		assertThrows(IncorrectSpeedSetException.class, () -> {
			cruiseControl.setSpeedSet(0);
		});
	}
	
	// Test for setSpeedSet with negative values (should throw IncorrectSpeedSetException)
	@Test
	public void testSetSpeedSetWithNegativeValueThrowsException() {
		assertThrows(IncorrectSpeedSetException.class, () -> {
			cruiseControl.setSpeedSet(-50);
		});
	}
	
	@Test
	public void testSetSpeedSetWithSmallNegativeValueThrowsException() {
		assertThrows(IncorrectSpeedSetException.class, () -> {
			cruiseControl.setSpeedSet(-1);
		});
	}
	
	// Test for setSpeedSet with speedLimit already set
	@Test
	public void testSetSpeedSetWithinSpeedLimit() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedLimit(120);
		cruiseControl.setSpeedSet(100);
		assertEquals(100, cruiseControl.getSpeedSet());
	}
	
	@Test
	public void testSetSpeedSetEqualToSpeedLimit() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedLimit(120);
		cruiseControl.setSpeedSet(120);
		assertEquals(120, cruiseControl.getSpeedSet());
	}
	
	// Test for setSpeedSet exceeding speedLimit (should throw SpeedSetAboveSpeedLimitException)
	@Test
	public void testSetSpeedSetAboveSpeedLimitThrowsException() {
		cruiseControl.setSpeedLimit(120);
		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> {
			cruiseControl.setSpeedSet(150);
		});
	}
	
	@Test
	public void testSetSpeedSetJustAboveSpeedLimitThrowsException() {
		cruiseControl.setSpeedLimit(120);
		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> {
			cruiseControl.setSpeedSet(121);
		});
	}
	
	// Test for setting speedSet when speedLimit is not set (no exception should be thrown)
	@Test
	public void testSetSpeedSetWithoutSpeedLimitConstraint() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(200);
		assertEquals(200, cruiseControl.getSpeedSet());
		assertNull(cruiseControl.getSpeedLimit());
	}
	
	// Test for updating speedSet to different values
	@Test
	public void testUpdateSpeedSet() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(80);
		assertEquals(80, cruiseControl.getSpeedSet());
		cruiseControl.setSpeedSet(120);
		assertEquals(120, cruiseControl.getSpeedSet());
	}
	
	// Test for smoke test
	@Test
	public void smokeTest() {
		assertNotNull(cruiseControl);
		assertNotNull(speedometer);
	}
	
}
