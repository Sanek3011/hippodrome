import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

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
    @ParameterizedTest
    @ValueSource(doubles = {-1, -5, -100})
    void testNegativeSpeed(double speed){
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            new Horse("name", -10);
        });
        assertEquals("Speed cannot be negative.", illegal.getMessage());

    }
    @ParameterizedTest
    @ValueSource(doubles =  {-1, -5, -100})
    void testNegativeDist(double distance){
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 1, distance);
        });
        assertEquals("Distance cannot be negative.", illegal.getMessage());
    }

    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }
}