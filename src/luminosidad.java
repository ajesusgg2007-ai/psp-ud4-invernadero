import java.util.concurrent.ThreadLocalRandom;

public class luminosidad extends sensor {

    public luminosidad() {
        super("Luminosidad", "lux", 500, 5, 200.0, 900.0);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(0.0, 1000.0);
    }
}