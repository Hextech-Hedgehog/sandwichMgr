package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.exception.SandwichNotFoundException;
import sandwich.model.SandwichType;
import sandwich.model.Shop;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SandwichTypeFileRepositoryTest {

    @Autowired
    SandwichTypeRepository sandwichTypeRepo;

    @Test
    void getSandwichSalamiFromPinkysTest() throws SandwichNotFoundException {
        SandwichType sandwichType = sandwichTypeRepo.getSandwich(Shop.PINKYS, "salami");
        assertEquals("salami", sandwichType.getSandwichName());
        assertEquals("Bread", sandwichType.getIngredients().get(0).getName());
        assertEquals("Salami", sandwichType.getIngredients().get(1).getName());
        assertEquals("Mayonnaise", sandwichType.getIngredients().get(2).getName());
    }

    @Test
    void getSandwichMartinoFromPinkysTest() throws SandwichNotFoundException {
        SandwichType sandwichType = sandwichTypeRepo.getSandwich(Shop.PINKYS, "martino");
        assertEquals("martino", sandwichType.getSandwichName());
        assertEquals("Bread", sandwichType.getIngredients().get(0).getName());
        assertEquals("Beef", sandwichType.getIngredients().get(1).getName());
        assertEquals("Eggs", sandwichType.getIngredients().get(2).getName());
        assertEquals("Onions", sandwichType.getIngredients().get(3).getName());
        assertEquals("Tomatoes", sandwichType.getIngredients().get(4).getName());
        assertEquals("Pickles", sandwichType.getIngredients().get(5).getName());
        assertEquals("Martino sauce", sandwichType.getIngredients().get(6).getName());
    }               // martino;Bread;Beef;Eggs;Onions;Tomatoes;Pickles;Martino sauce;

    @Test
    void getSandwichSpecialFromVleugelsTest() throws SandwichNotFoundException {
        SandwichType sandwichType = sandwichTypeRepo.getSandwich(Shop.VLEUGELS, "special");
        assertEquals("special", sandwichType.getSandwichName());
        assertEquals("bacon", sandwichType.getIngredients().get(0).getName());
        assertEquals("mayonnaise", sandwichType.getIngredients().get(1).getName());
        assertEquals("honey", sandwichType.getIngredients().get(2).getName());
    }               // special;bacon;mayonnaise;honey;

    @Test
    void getSandwichSpecialFromWrongShopTest() {
        assertThrows(SandwichNotFoundException.class, ()-> sandwichTypeRepo.getSandwich(Shop.PINKYS, "special"));
    }

    @Test
    void getSandwichWithNoNameTest() {
        assertThrows(SandwichNotFoundException.class, ()-> sandwichTypeRepo.getSandwich(Shop.PINKYS, null));
    }

    @Test
    void getSandwichWithFakeNameTest() {
        assertThrows(SandwichNotFoundException.class, ()-> sandwichTypeRepo.getSandwich(Shop.PINKYS, "Mastodon"));
    }

    @Test
    void getSandwichWithCapitalMistakesInNameStillTreatedCorrectlyTest() throws SandwichNotFoundException {
        SandwichType sandwichType = sandwichTypeRepo.getSandwich(Shop.PINKYS, "SaLAMi");
        assertEquals("salami", sandwichType.getSandwichName());
        assertEquals("Bread", sandwichType.getIngredients().get(0).getName());
        assertEquals("Salami", sandwichType.getIngredients().get(1).getName());
        assertEquals("Mayonnaise", sandwichType.getIngredients().get(2).getName());
    }

    @Test
    void getSandwichesFromShopVleugels() throws SandwichNotFoundException {
        Set<SandwichType> sandwichTypes = sandwichTypeRepo.getSandwichesFromShop(Shop.VLEUGELS);
        assertTrue(sandwichTypes.contains(sandwichTypeRepo.getSandwich(Shop.VLEUGELS, "club")));
        assertTrue(sandwichTypes.contains(sandwichTypeRepo.getSandwich(Shop.VLEUGELS, "special")));
        assertEquals("club", sandwichTypes.stream().findAny()
                .orElseThrow(()-> new SandwichNotFoundException("Sandwich not found in this shop")).getSandwichName());
        assertEquals("salad", sandwichTypes.stream().findAny()
                .orElseThrow(()-> new SandwichNotFoundException("Sandwich not found in this shop")).getIngredients().get(2).getName());
        assertEquals("bacon", sandwichTypes.stream().findAny()
                .orElseThrow(()-> new SandwichNotFoundException("Sandwich not found in this shop")).getIngredients().get(0).getName());



    }



}