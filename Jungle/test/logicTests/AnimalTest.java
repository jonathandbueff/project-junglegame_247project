package logicTests;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import board.Animal;
import board.Enumerations;

class AnimalTest {

    Animal dog = new Animal(Enumerations.Rank.dog);
    Animal cat = new Animal(Enumerations.Rank.cat);
    Animal elephant = new Animal(Enumerations.Rank.elephant);
    Animal mouse = new Animal(Enumerations.Rank.mouse);
    Animal leo = new Animal(Enumerations.Rank.leopard);



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



}