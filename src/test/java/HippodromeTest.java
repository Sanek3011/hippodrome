import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @ParameterizedTest
    @NullSource
    void hippodromeConstructorWithNullTest(List<Horse> horses) {
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be null.", illegal.getMessage());

    }
    @Test
    void hippodromeConstructorEmptyListTest() {
        IllegalArgumentException illegal = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", illegal.getMessage());



    }


    @Test
    void getHorsesTest() {
        List<Horse> horses = getListOfHorses(30);
        Hippodrome hippo = new Hippodrome(horses);
        assertEquals(horses, hippo.getHorses());
    }

    List<Horse> getListOfHorses(int capacity) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            horses.add(new Horse("Horse"+i, i));
        }
        return horses;
    }

    @Test
    void moveHippodromeTest() {
    }

    @Test
    void getWinner() {
    }
}