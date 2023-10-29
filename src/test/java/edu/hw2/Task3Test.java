package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Проверка DefaultConnection со 100% StableConnection в нем")
    void connectionTest() {
        //given
        ConnectionManager manager = new DefaultConnectionManager();
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, 5);

        //then
        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Проверка FaultyConnection со 100% исключением в нем")
    void faultyConnectionTest() {
        //given
        ConnectionManager manager = new FaultyConnectionManager();
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, 2);
        String info = null;

        //when
        try {
            executor.updatePackages();
        } catch (ConnectionException e) {
            info = "Exception caught";
        }

        //then
        assertEquals("Exception caught", info);
    }
}
