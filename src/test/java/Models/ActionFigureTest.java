package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionFigureTest {


    @Test
    public void setNameTest () {
        // given (1)
        String expected = "Batman";
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setName(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getName());
    }

    @Test
    public void setIdTest () {
        // given (1)
        Integer expected = 0;
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setId(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getId());
    }

    @Test
    public void setColorTest () {
        // given (1)
        String expected = "Black";
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setColor(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getColor());
    }

    @Test
    public void setBrandTest () {
        // given (1)
        String expected = "Mattel";
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setBrand(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getBrand());
    }

    @Test
    public void setSizeTest () {
        // given (1)
        Double expected = 12.0;
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setSize(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getSize());
    }

    @Test
    public void setQtyTest () {
        // given (1)
        Integer expected = 1;
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setQty(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getQty());
    }

    @Test
    public void setPriceTest () {
        // given (1)
        Double expected = 9.99;
        // when (2)
        ActionFigure figure = new ActionFigure();
        figure.setPrice(expected);
        // then (3)
        Assertions.assertEquals(expected, figure.getPrice());
    }
}


