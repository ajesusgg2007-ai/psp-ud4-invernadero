## Qué hace
Este proyecto simula un sistema de control automatizado para un invernadero utilizando programación multihilo en Java. El sistema gestiona de forma concurrente tres tipos de sensores Temperatura, Humedad y Luminosidad que realizan lecturas periódicas, detectan valores fuera de rango (alertas) y un sistema de control de riego programadorRiego que abre y cierra válvulas acumulando los litros regados. El proyecto evoluciona desde un modelo secuencial hasta
un control avanzado de hilos, monitorización y estados.

## Cómo ejecutarlo
El proyecto está dividido en clases ejecutables independientes para cada fase:
* **Fase 1**: Ejecutar Fase1_Secuencial para ver el modelo bloqueante en el hilo principal.
* **Fase 2**: Ejecutar Fase2_Concurrente para ver la ejecución en paralelo usando Thread, .start() y .join().
* **Fase 3**: Ejecutar Fase3_Experimentos para comprobar el comportamiento práctico de los hilos.
* **Fase 4**: Ejecutar Fase4_Vigilante para monitorizar en tiempo real el ciclo de vida de los hilos con un hilo de supervisión Daemon.

FASE 2:

**Captura:**

![img_1.png](img_1.png)

| Fase | Modelo de Ejecución              | Tiempo Total Aprox. |
| :--- |:---------------------------------|:--------------------|
| **Fase 1** | Secuencial    | 11188 ms            |
| **Fase 2** | Concurrente  | 3500 ms             |

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

* ¿Por qué los sensores solo pueden ser Runnable en tu diseño?

Porque la clase base sensor define una estructura de negocio y comparte atributos comunes. Al usar Runnable, se mantiene una arquitectura limpia y orientada a objetos, permitiendo que cualquier tipo de sensor se ejecute en un hilo sin tener que forzar una jerarquía de herencia compleja con `Thread`.

* ¿Qué forma elegirías para el riego y por qué?

Para el programadorRiego, se decidió heredar directamente de Thread porque actúa como un componente autónomo y muy específico del sistema. Tiene un ciclo de vida claro y limitado, lo que facilita su creación directa tal como exige el diseño de la práctica.