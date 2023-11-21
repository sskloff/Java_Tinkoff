package edu.hw6;

import java.util.Objects;

public class Port {
    public enum ServerProtocol {
        TCP,
        UDP
    }

    public int port;
    public ServerProtocol protocol;

    public Port(int port, ServerProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Port port1 = (Port) o;
        return port == port1.port && protocol == port1.protocol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, protocol);
    }
}
