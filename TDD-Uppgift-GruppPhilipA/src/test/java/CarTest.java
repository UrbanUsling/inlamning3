import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private Car car;
    private Car car2;

    @BeforeEach
    void setUp() {
        car = new Car();
        car2 = new Car("Ford", "Mustang", 1993, 210, 0.3);
    }

    @Test
    void testTurnOnFullLights() {
        car.turnOnLights("full");
        assertTrue(car.isFullLightsOn());
        assertFalse(car.isHalfLightsOn());
        assertEquals(99.5, car.getBatteryLevel());
    }

    @Test
    void testGetInfo(){
        assertEquals("Mustang", car2.getCarName());
        assertEquals("Ford", car2.getCarBrand());
        assertEquals(1993, car2.getYear());
        assertEquals(210, car2.getTopSpeed());
        assertEquals(0.3, car2.getPowerConsumptionIndex());
    }
    @Test
    void testTurnOnHalfLights() {
        car.turnOnLights("half");
        assertTrue(car.isHalfLightsOn());
        assertFalse(car.isFullLightsOn());
        assertEquals(99.7, car.getBatteryLevel());
    }

    @Test
    void testTurnBreakLightsOn() {
        car.turnBreakLightsOn();
        assertTrue(car.isBrakeLightsOn());
        assertEquals(99.5, car.getBatteryLevel());
    }

    @Test
    void testTurnOffLights() {
        car.turnOnLights("full");
        car.turnOffLights();
        assertFalse(car.isFullLightsOn());
        assertFalse(car.isHalfLightsOn());
    }

    @Test
    void testClutchDrive() {
        car.clutch("drive");
        assertTrue(car.isDriveApplied());
        assertFalse(car.isReverseApplied());
    }

    @Test
    void testClutchReverse() {
        car.clutch("reverse");
        assertTrue(car.isReverseApplied());
        assertFalse(car.isDriveApplied());
    }

    @Test
    void testTurnOnEmergencyLights() {
        car.turnOnEmergencyLights();
        assertTrue(car.isEmergencyLightsOn());
    }

    @Test
    void testStartEngineWithSufficientBattery() {
        car.setBatteryLevel(0.6);
        car.startEngine();
        assertTrue(car.isEngineRunning());
        assertTrue(car.isHalfLightsOn());
    }

    @Test
    void testStartEngineWithLowBattery() {
        car.setBatteryLevel(0.4);
        car.startEngine();
        assertFalse(car.isEngineRunning());
        assertFalse(car.isHalfLightsOn());
    }

    @Test
    void testStopEngine() {
        car.startEngine();
        car.turnOnEmergencyLights();
        car.stopEngine();

        assertFalse(car.isEngineRunning());
        assertFalse(car.isFullLightsOn());
        assertFalse(car.isHalfLightsOn());
        assertFalse(car.isBrakeApplied());
        assertFalse(car.isReverseApplied());
        assertFalse(car.isDriveApplied());
        assertFalse(car.isEmergencyLightsOn());
    }

    @Test
    void testAccelerateWithDriveApplied() {
        car.clutch("drive");
        car.startEngine();
        car.accelerate(50);

        assertEquals(50, car.getSpeed());
        assertEquals(94.7, car.getBatteryLevel());//-0.3 för halvljus
    }

    @Test
    void testAccelerateWithReverseApplied() {
        car.clutch("reverse");
        car.startEngine();
        car.accelerate(30);

        assertEquals(30, car.getSpeed());
        assertEquals(96.7, car.getBatteryLevel());
    }

    @Test
    void testBrake() {
        car.startEngine();
        car.accelerate(60);
        car.brake(30);

        assertTrue(car.isBrakeApplied());
        assertEquals(30, car.getSpeed());
    }

    @Test
    void testChargeBattery() {
        car.setBatteryLevel(50.0);
        car.chargeBattery();

        assertEquals(100, car.getBatteryLevel());
    }
}
