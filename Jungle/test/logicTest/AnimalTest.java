package logicTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import board.Animal;
import board.Enumerations;

public class AnimalTest {

    Animal dog = new Animal(Enumerations.Rank.dog, 0);
    Animal cat = new Animal(Enumerations.Rank.cat, 1);
    Animal elephant = new Animal(Enumerations.Rank.elephant, 0);
    Animal mouse = new Animal(Enumerations.Rank.mouse, 1);
    Animal leo = new Animal(Enumerations.Rank.leopard, 0);



    @Test
    void testIsSuperiorTo() {
        Assert.assertEquals(dog.isSuperiorTo(cat), 1);
        Assert.assertEquals(elephant.isSuperiorTo(mouse), -1);
        Assert.assertEquals(cat.isSuperiorTo(elephant), -1);
        Assert.assertEquals(elephant.isSuperiorTo(leo), 1);
        Assert.assertEquals(elephant.isSuperiorTo(elephant), 0);
    }

    @Test
    void testGetRank() {
        Assert.assertEquals(dog.getRank(), Enumerations.Rank.dog);
        Assert.assertEquals(cat.getRank(), Enumerations.Rank.cat);
        Assert.assertEquals(elephant.getRank(), Enumerations.Rank.elephant);
        Assert.assertEquals(leo.getRank(), Enumerations.Rank.leopard);
        Assert.assertEquals(mouse.getRank(), Enumerations.Rank.mouse);
    }

    void testSetRank() {
        dog.setRank(Enumerations.Rank.cat);
        Assert.assertEquals(dog.getRank(), Enumerations.Rank.cat);
    }

    @Test
    void testGetSide(){
        Assert.assertEquals(dog.getSide(), 0);
        Assert.assertEquals(cat.getSide(), 1);
    }



}
