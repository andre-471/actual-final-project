package math;

import java.util.Objects;

public record Edge(Vertex a, Vertex b) {
    public Edge {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
    }
}
