import java.util.concurrent.ThreadLocalRandom;

public class temperatura extends sensor {
    public temperatura(){
        super("Temperatura", "ºC",400,5,18.0,30.0);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(15.0,35.0);
    }
}
