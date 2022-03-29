# PRACTICA-6
PAT PRACTICA 6

## ¿Como probar en el cloud?

[![](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/ailwix79/PRACTICA-5)

# PAT-PRACTICA-6
Practica 6 PAT Alejandro López

# DESCRIPCIÓN
En esta práctica se empleará el API de la NASA. Se realizan los Test del código desarrollado en la práctica 5.

# INDEX
Página de inicio, desde aquí se pueden seleccionar el API deseado.

# APOD NASA API
Este API obtiene la fotografía del día, junto con una descripción. Realmente esto es un manejo de APIs básico, donde simplemente se
realiza un fetch asíncrono.

# NEO NASA API
Objetos más cercanos a la Tierra. Se han formateado los datos para presentarlos en un scatter plot. En el propio scatter plot se pueden
realizar diversas acciones.

- Manipulación del gráfico mediante zoom. recortes...
- Descargar una captura de los datos.
- Introducir fechas de búsqueda que no sean las actuales.

Este API incluye manejo de errores, lo bueno de este API es que devuelve errores muy verbales, que permiten saber al usuario que está ocurriendo.

## Elementos extra

- Logging. Estado de la carga de datos. Permite saber si todo va bien.
- Actuator. Checkear el estado de la página, métricas, logs... Entrando en la URL localhost:8080/actuator se pueden observar todos los componentes habilitados de Actuator y acceder a los mismos. Se adjunta una imagen con la evidencia.
- Empleo de MockBeans.
