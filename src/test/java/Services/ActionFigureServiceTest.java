package Services;

import Models.ActionFigure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ActionFigureServiceTest {

    @Test
    public void createTest() throws IOException {

        // (1)
        String expectedName = "Batman";
        String expectedColor = "Black";
        String expectedBrand = "Mattel";
        Double expectedSize = 12.0;
        Integer expectedQty = 10;
        Double expectedPrice = 9.99;

        // (2)
        ActionFigureService figureService = new ActionFigureService();
        ActionFigure testFigure = ActionFigureService.create(expectedName, expectedColor,expectedBrand, expectedSize, expectedQty, expectedPrice);

        // (3)
        Integer actualId = testFigure.getId();
        String actualName = testFigure.getName();
        String actualColor = testFigure.getColor();
        String actualBrand = testFigure.getBrand();
        Double actualSize = testFigure.getSize();
        Integer actualQty = testFigure.getQty();
        Double actualPrice = testFigure.getPrice();

        // (4)
        Assertions.assertEquals(Integer.class.getName(), new Integer(actualId).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedColor, actualColor);
        Assertions.assertEquals(expectedBrand, actualBrand);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals(expectedQty, actualQty);
        Assertions.assertEquals(expectedPrice, actualPrice);

    }
}
