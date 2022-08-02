package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NerfBlasterTest {

    @Test
    public void setNameTest () {
        // given (1)
        String expected = "Strongarm";
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setName(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getName());
    }

    @Test
    public void setIdTest () {
        // given (1)
        Integer expected = 111;
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setId(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getId());
    }

    @Test
    public void setTypeTest () {
        // given (1)
        String expected = "Springer";
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setType(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getType());
    }

    @Test
    public void setSeriesTest () {
        // given (1)
        String expected = "Elite";
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setSeries(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getSeries());
    }

    @Test
    public void setSizeTest () {
        // given (1)
        Double expected = 10.0;
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setSize(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getSize());
    }

    @Test
    public void setQtyTest () {
        // given (1)
        Integer expected = 1;
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setQty(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getQty());
    }

    @Test
    public void setPriceTest () {
        // given (1)
        Double expected = 7.99;
        // when (2)
        NerfBlaster blaster = new NerfBlaster();
        blaster.setPrice(expected);
        // then (3)
        Assertions.assertEquals(expected, blaster.getPrice());
    }
}
