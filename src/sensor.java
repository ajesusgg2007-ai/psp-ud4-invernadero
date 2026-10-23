public abstract class sensor implements Runnable {
    private String nombre;
    private String unidad;
    private long periodo;
    private int numLecturas;
    private double umbralMin;
    private double umbralMax;

    protected double sumaValores = 0.0;
    protected int totalLecturas = 0;
    protected int numAlertas = 0;

    public sensor(String nombre, String unidad, long periodo,int numLecturas,double umbralMin, double umbralMax) {
        this.nombre=nombre;
        this.unidad=unidad;
        this.periodo=periodo;
        this.numLecturas=numLecturas;
        this.umbralMin=umbralMin;
        this.umbralMax=umbralMax;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(long periodo) {
        this.periodo = periodo;
    }

    public int getNumLecturas() {
        return numLecturas;
    }

    public void setNumLecturas(int numLecturas) {
        this.numLecturas = numLecturas;
    }

    public double getUmbralMin() {
        return umbralMin;
    }

    public void setUmbralMin(int umbralMin) {
        this.umbralMin = umbralMin;
    }

    public double getUmbralMax() {
        return umbralMax;
    }

    public void setUmbralMax(int umbralMax) {
        this.umbralMax = umbralMax;
    }

    public abstract double medir();

    @Override
    public void run(){
        for(int i = 0; i < numLecturas; i++){
            double valor = medir();

            totalLecturas++;   // Incrementamos las lecturas hechas correctamente
            sumaValores += valor;

            System.out.println("Hilo: " + Thread.currentThread().getName() + " | Valor: " + valor);

            if (valor < umbralMin || valor > umbralMax) {
                System.out.println("ALERTA: " + getNombre() + " fuera de Rango (" + valor + ")");
                numAlertas++;  // Incrementamos correctamente el contador de alertas
            }

            try {
                Thread.sleep(getPeriodo());
            } catch (InterruptedException e) {
                System.out.println(getNombre() + " interrumpido.");
                return;
            }
        }
    }
    public String informe() {
        double media = totalLecturas > 0 ? sumaValores / totalLecturas : 0;
        return "Sensor " + nombre + ": Lecturas: " + totalLecturas +
                "Media: " + String.format("%.2f", media) + " " + unidad +
                "Mín: " + umbralMin + "Máx: " + umbralMax +
                "Alertas: " + numAlertas;
    }
        }