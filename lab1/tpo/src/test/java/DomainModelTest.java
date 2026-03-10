import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vaskozlov.lab1.Arthur;
import org.vaskozlov.lab1.Fish;
import org.vaskozlov.lab1.Ford;
import org.vaskozlov.lab1.Vial;

import static org.junit.jupiter.api.Assertions.*;

class DomainModelTest {

    private Fish fish;
    private Vial vial;
    private Ford ford;
    private Arthur arthur;

    @BeforeEach
    void setUp() {
        fish = new Fish();
        vial = new Vial();
        ford = new Ford();
        arthur = new Arthur();
    }

    @Test
    void test1_CreateFish() {
        assertEquals("жёлтая", fish.getColor());
        assertEquals("маленькая", fish.getSize());
        assertEquals("плавает, переливается", fish.getState());
    }

    @Test
    void test2_CreateVial() {
        assertTrue(vial.isEmpty());
    }

    @Test
    void test3_PutFishIntoVial() {
        vial.putFish(fish);
        assertFalse(vial.isEmpty());
        assertSame(fish, vial.getContent());
    }

    @Test
    void test4_CreateFord() {
        assertNull(ford.getVialInHand());
    }

    @Test
    void test5_FordTakesVial() {
        ford.takeVial(vial);
        assertSame(vial, ford.getVialInHand());
    }

    @Test
    void test6_CreateArthur() {
        assertTrue(arthur.areEyesActive());
    }

    @Test
    void test7_FordOffersFishToEar() {
        vial.putFish(fish);
        ford.takeVial(vial);
        assertTrue(ford.offerFishToEar(arthur));
    }

    @Test
    void test8_ArthurLooksAtVial() {
        assertDoesNotThrow(() -> arthur.lookAt(vial));
    }

    @Test
    void test9_ArthurBlinks() {
        assertDoesNotThrow(() -> arthur.blink());
    }

    @Test
    void test10_OfferWithoutVial() {
        assertFalse(ford.offerFishToEar(arthur));
    }

    @Test
    void test11_FishInvariant() {
        assertEquals("плавает, переливается", fish.getState());
    }

    @Test
    void test12_VialInitialState() {
        assertTrue(vial.isEmpty());
    }

    @Test
    void test13_VialAfterPuttingFish() {
        vial.putFish(fish);
        assertFalse(vial.isEmpty());
    }

    @Test
    void test14_ArthurInitialState() {
        assertTrue(arthur.areEyesActive());
    }

    @Test
    void test15_PutNonFishIntoVial() {
        assertThrows(IllegalArgumentException.class, () -> vial.putFish(null));
    }
}