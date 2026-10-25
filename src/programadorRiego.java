public class programadorRiego extends Thread {

    private int ciclos=5;
    private double litros=0.0;

public programadorRiego(){
    super("riego");
}

@Override
    public void run(){
    for (int i=1; i <=ciclos; i++)
    {
        boolean abierta;

        if (i % 2 != 0){
            abierta=true;
        }
        else {
            abierta=false;
        }

        if (abierta) {
            litros += 2.5;
            System.out.println(getName() + " Ciclo " + i + "º : Válvula ABIERTA. Total acumulado: " + litros + "L");
        } else {
            System.out.println(getName() + " Ciclo " + i + "º : Válvula CERRADA");
        }

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpido.");
            break;
        }

    }

}

    public String informe() {
        return "Riego Ciclos realizados: " + ciclos + " | Litros totales regados: " + litros + "L";
    }
}
