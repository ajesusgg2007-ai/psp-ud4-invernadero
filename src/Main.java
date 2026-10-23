
public class Main {
    public static void main(String[] args) {

        sensor temp=new temperatura();
        Thread hiloTemp= new Thread(temp,"Temperatura");

        sensor humedad= new humedad();
        Thread hiloHumedad= new Thread(humedad,"Humedad");

        sensor luz =new luminosidad();
        Thread hiloLuz= new Thread(luz,"Luminosidad");

        programadorRiego riego= new programadorRiego();

        long inicio = System.currentTimeMillis();

        hiloTemp.start();
        hiloHumedad.start();
        hiloLuz.start();

        try {
            hiloTemp.join();
            hiloHumedad.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
        }

        long fin = System.currentTimeMillis();
        long tiempo = fin - inicio;


        System.out.println("\n=== INFORME FINAL CONCURRENTE ===");
        System.out.println(temp.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());

        System.out.println("\nTiempo total transcurrido: " + tiempo + " ms");
        }
    }
