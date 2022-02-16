package no.ntnu.olekel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
    CavalryUnit cavalryUnit;

    @BeforeEach
    void createCavalryUnit(){
        cavalryUnit = new CavalryUnit("cavalry", 100);
    }

    @Test
    void getAttackBonusTest(){
        assertEquals(cavalryUnit.getAttackBonus(), 6);
        assertEquals(cavalryUnit.getAttackBonus(), 2);
    }

    @Test
    void getResistBonusTest(){
        assertEquals(cavalryUnit.getResistBonus(), 1);
    }
}
