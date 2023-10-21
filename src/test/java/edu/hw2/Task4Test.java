package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.GetStackTrace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    CallingInfo func123() {
        return GetStackTrace.callingInfo();
    }

    @Test
    @DisplayName("Отработка метода")
    void callingInfoTest() {
        //when
        CallingInfo test = func123();

        //then
        assertEquals(this.getClass().getName(), test.className());
        assertEquals("func123", test.methodName());
    }
}
