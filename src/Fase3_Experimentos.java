public class Fase3_Experimentos {

    public static void main(String[] args) {

        System.out.println("EXPERIMENTO A");
        experimentoA();

        System.out.println("\nEXPERIMENTO B");
        experimentoB();

        System.out.println("\nEXPERIMENTO C");
        experimentoC();
    }

    public static void experimentoA() {
        sensor temp = new temperatura();
        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");

        hiloTemp.start();

        System.out.println("Informe sin esperar: " + temp.informe());

        try {
            hiloTemp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Informe esperando: " + temp.informe());
    }

    public static void experimentoB() {
        programadorRiego riego = new programadorRiego(3);

        long inicio = System.currentTimeMillis();

        riego.run();

        long fin = System.currentTimeMillis();
        long tiempo = fin - inicio;

        System.out.println("Tiempo transcurrido en B: " + tiempo + " ms");
        System.out.println("Estado final: " + riego.getState());
    }

    public static void experimentoC() {
        programadorRiego riego = new programadorRiego();

        riego.start();

        try {
            riego.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("riego por segunda vez");
            riego.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e.getClass().getName());
        }
    }


}

