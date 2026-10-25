import java.awt.image.Kernel;

public class Fase4_Vigilante {

    public static void main(String[] args) {

        sensor temp =new temperatura();
        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");

        sensor humedad = new humedad();
        Thread hiloHumedad = new Thread(humedad, "sensor-Humedad");

        sensor luz = new luminosidad();
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        programadorRiego riego = new programadorRiego();

        System.out.println(hiloTemp.getName() + " = " + hiloTemp.getState());
        System.out.println(hiloHumedad.getName() + " = " + hiloHumedad.getState());
        System.out.println(hiloLuz.getName() + " = " + hiloLuz.getState());
        System.out.println(riego.getName() + " = " + riego.getState());

        Thread vigilante= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("Estados: "+temp+ " hiloTemp.getState() " +
                            "Humedad: " + hiloHumedad.getState() +
                            "Luz: " + hiloLuz.getState() +
                            "Riego: " + riego.getState());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        },"hilo-vigilante");

        vigilante.setDaemon(true);

        vigilante.start();
        hiloTemp.start();
        hiloHumedad.start();
        hiloLuz.start();
        riego.start();

        try {
            hiloTemp.join();
            hiloHumedad.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e) {
            System.out.println("El hilofue interrumpido.");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}

        System.out.println("Después de terminar:");
        System.out.println(hiloTemp.getName() + " = " + hiloTemp.getState());
        System.out.println(hiloHumedad.getName() + " = " + hiloHumedad.getState());
        System.out.println(hiloLuz.getName() + " = " + hiloLuz.getState());
        System.out.println(riego.getName() + " = " + riego.getState());
    }
}
