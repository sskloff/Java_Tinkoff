package edu.hw2.Task3;

public class PopularCommandExecutor {

    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int count = 0;
        ConnectionException exception = null;
        try (Connection connection = manager.getConnection()) {
            while (count < maxAttempts) {
                try {
                    connection.execute(command);
                    break;
                } catch (ConnectionException e) {
                    exception = e;
                    count++;
                }
            }
            if (count == maxAttempts) {
                throw new ConnectionException(exception);
            }
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
