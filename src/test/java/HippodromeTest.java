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
    void getHorsesForTest() {
        List<Horse> horses = getListOfHorses(30, false);
        Hippodrome hippo = new Hippodrome(horses);
        assertEquals(horses, hippo.getHorses());
    }

    List<Horse> getListOfHorses(int capacity, boolean isMock) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (isMock) {
                horses.add(Mockito.mock(Horse.class));
            }else{
                horses.add(new Horse("Horse"+i, i, i));
            }
        }
        return horses;
    }

    @Test
    void moveHippodromeTest() {
        List<Horse> mockHorses = getListOfHorses(50, true);
        Hippodrome hippo = new Hippodrome(mockHorses);
        hippo.move();
        for (Horse horse : mockHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Horsyashak", 10, 10);
        horses.add(horse1);
        Horse horse2 = new Horse("Bingo", 10, 25);
        horses.add(horse2);
        Horse horse3 = new Horse("Wind", 10, 26);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horse3, hippodrome.getWinner());

    }
}