package Services;

import Models.ActionFigure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionFigureServiceTest {

    @Test
    public void createTest(){

        // (1)
        String expectedName = "Batman";
        String expectedBrand = "Mattel";
        String expectedColor = "Black";
        Double expectedSize = 12.0;
        Integer expectedQty = 10;
        Double expectedPrice = 9.99;

        // (2)
        ActionFigureService figureService = new ActionFigureService();
        ActionFigure testFigure = ActionFigureService.create(expectedName, expectedBrand,
                expectedColor, expectedSize, expectedQty, expectedPrice);

        // (3)
        Integer actualId = testFigure.getId();
        String actualName = testFigure.getName();
        String actualBrand = testFigure.getBrand();
        String actualColor = testFigure.getColor();
        Double actualSize = testFigure.getSize();
        Integer actualQty = testFigure.getQty();
        Double actualPrice = testFigure.getPrice();

        // (4)
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedBrand, actualBrand);
        Assertions.assertEquals(expectedColor, actualColor);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals(expectedQty, actualQty);
        Assertions.assertEquals(expectedPrice, actualPrice);

    }
}
