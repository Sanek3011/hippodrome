import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HorseTest {
    static Horse twoParamHorse;
    static Horse threeParamHorse;
    @BeforeAll
    static void init() {
        twoParamHorse = new Horse("Horse", 10);
        threeParamHorse = new Horse("Horse", 10, 100);
    }

    @ParameterizedTest
    @NullSource
    void testConstructorWithNullName(String name){
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            new Horse(name, 1);
        });
        assertEquals( "Name cannot be null.", illegal.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"\t", "\s"})
    void testConstructorWithInvalidSymbols(String name){
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            new Horse(name, 1);
        });
        assertEquals("Name cannot be blank.", illegal.getMessage());

    }
    @Test
    void testNegativeSpeed(){
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            new Horse("name", -1);
        });
        assertEquals("Speed cannot be negative.", illegal.getMessage());

    }
    @Test
    void testNegativeDistance(){
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 1, -1);
        });
        assertEquals("Distance cannot be negative.", illegal.getMessage());
    }

    @Test
    void getNameTest() {
        assertEquals("Horse", twoParamHorse.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(10, twoParamHorse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        assertEquals(100, threeParamHorse.getDistance());
    }
    @Test
    void getDistanceWithoutThirdParamTest() {
        assertEquals(0, twoParamHorse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.3, 0.7})
    void moveTest(double rand) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(rand);
            Horse horse = new Horse("Horse", 5.0, 10.0);
            horse.move();
            assertEquals( 10.0 + (5.0  * rand), horse.getDistance());
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9), times(1));

        }
    }
    @AfterAll
    static void deInit() {
        twoParamHorse = null;
        threeParamHorse = null;
    }
}