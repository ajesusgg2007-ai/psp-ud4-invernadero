Fase 3: Experimentos

Experimento A:

Resultado primera salida:

Hilo: Temperatura | Valor: 18.05923207811974
Sensor Temperatura: Lecturas: 0Media: 0,00 ºCMín: 18.0Máx: 30.0 Alertas: 0
Hilo: Temperatura | Valor: 17.578620947250734
ALERTA: Temperatura fuera de Rango (17.578620947250734)
Hilo: Temperatura | Valor: 15.08580328866341
ALERTA: Temperatura fuera de Rango (15.08580328866341)
Hilo: Temperatura | Valor: 23.195633911106846
Hilo: Temperatura | Valor: 24.038680747425747

Process finished with exit code 0

¿Qué comprendemos?

La primera vez sale el informe con 0 lecturas, 0 alertas y media 0.00
Porque El hilo principal (main) no espera a que el hilo del sensor realice sus lecturas e imprime el informe
inmediatamente después de hacer .start()

después esperando:

Informe tras esperar: Sensor Temperatura: Lecturas: 5Media: 22,85 ºCMín: 18.0Máx: 30.0 Alertas: 1

Process finished with exit code 0

Experimento B:

lo que muestra por consola:

EXPERIMENTO B
riego Ciclo 1º : Válvula ABIERTA. Total acumulado: 2.5L
riego Ciclo 2º : Válvula CERRADA
riego Ciclo 3º : Válvula ABIERTA. Total acumulado: 5.0L
Tiempo transcurrido en B: 2132 ms
Estado final del objeto riego: NEW

El nombre del hilo es "riego"
El estado final es "NEW", al usar .run(), el thread nunca se llego a ejecutar en el SO.

Experimento C

Lo que sale por terminal:

EXPERIMENTO C
riego Ciclo 1º : Válvula ABIERTA. Total acumulado: 2.5L
riego Ciclo 2º : Válvula CERRADA
riego Ciclo 3º : Válvula ABIERTA. Total acumulado: 5.0L
riego Ciclo 4º : Válvula CERRADA
riego Ciclo 5º : Válvula ABIERTA. Total acumulado: 7.5L
riego por segunda vez
Excepción: java.lang.IllegalThreadStateException

Sale java.lang.IllegalThreadStateException

El ciclo de vida de un hilo es de un solo uso. Una vez que un hilo alcanza el estado TERMINATED, no se puede reanimar
ni reiniciar llamando de nuevo a .start().