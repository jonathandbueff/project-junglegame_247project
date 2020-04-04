package logicTest;

import board.Animal;
import board.Box;
import board.Enumerations;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BoxTest {
    Box trap1= new Box(Enumerations.Landscape.trap1);
    Box trap2= new Box(Enumerations.Landscape.trap2);

    @Test
    void testGetKind() {
        Assert.assertEquals(trap1.getKind(),Enumerations.Landscape.trap1 );
    }

    @Test
    void testGetAnimal() {
        trap1.setAnimal(new Animal(Enumerations.Rank.elephant,  0));
        Assert.assertEquals(trap1.getAnimal(),Enumerations.Rank.elephant );
        trap2.setAnimal(new Animal(Enumerations.Rank.cat,  0));
        Assert.assertEquals(trap2.getAnimal(),Enumerations.Rank.cat );
    }


}