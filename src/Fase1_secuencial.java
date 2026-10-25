public class Fase1_secuencial {
    public static void main(String[] args) {

        sensor temp = new temperatura();
        sensor humedad = new humedad();
        sensor luz = new luminosidad();
        programadorRiego riego = new programadorRiego();

        long inicio = System.currentTimeMillis();

        temp.run();
        humedad.run();
        luz.run();
        riego.run();

        long fin = System.currentTimeMillis();
        long tiempo = fin - inicio;

        System.out.println(temp.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());

        System.out.println("\nTiempo total transcurrido: " + tiempo + " ms");
    }
}

