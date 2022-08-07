package Services;

import Models.ActionFigure;
import Models.NerfBlaster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NerfBlasterServiceTest {

    @Test
    public void createTest(){

        // (1)
        String expectedName = "Batman";
        String expectedType = "Black";
        String expectedSeries = "Mattel";
        Double expectedSize = 12.0;
        Integer expectedQty = 10;
        Double expectedPrice = 9.99;

        // (2)
        NerfBlasterService figureService = new NerfBlasterService();
        NerfBlaster testBlaster = NerfBlasterService.create(expectedName, expectedType, expectedSeries, expectedSize, expectedQty, expectedPrice);

        // (3)
        Integer actualId = testBlaster.getId();
        String actualName = testBlaster.getName();
        String actualType = testBlaster.getType();
        String actualSeries = testBlaster.getSeries();
        Double actualSize = testBlaster.getSize();
        Integer actualQty = testBlaster.getQty();
        Double actualPrice = testBlaster.getPrice();

        // (4)
        Assertions.assertEquals(Integer.class.getName(), new Integer(actualId).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedType, actualType);
        Assertions.assertEquals(expectedSeries, actualSeries);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals(expectedQty, actualQty);
        Assertions.assertEquals(expectedPrice, actualPrice);

    }
}
