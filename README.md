
Este proyecto esta hecho con intenciones demostrativas de automatización con Selenium.


#18/07/2017 ----- inclusión de la funcionalidad Selenium grid   ------------------------
Instrucciónes:
1.  configurar el hub:
1.a iniciar el servidor de selenium en la cosnsola del SO.
sintaxis: java -jar selenium-server-standalone-ver.jar  -port <numero de puerto> -role hub

2. configurar y arrancar el nodo:
2.a copiar o descargar el servidor de selenium en el dispositivo que sera el nodo.
2.b copiar o descargar el driver adecuado para el navegador (ej chormedriver para chrome)
2.c arrancar el nodo con la siguiente sintaxis.
Ejemplo para el navegador chorme en la consola:
java -Dwebdriver.chrome.driver=<ubicación del driver en el nodo> -jar <ubicación del servidor de selenium> -role node -hub <dirección ip del hub>/<puerto del hub>/grid/register/ -browser browserName=chrome -port <numero de puerto>

3. configurar e iniciar el navegador firefox
3.1 descargar  el driver "gecko" para el navegador
3.2 Iniciar el navegador en la consola:
java -Dwebdriver.gecko.driver=<ubicación del driver> -jar <ubicación del servidor de selenium>  -role webdriver -hub <dirección ip del hub>/<puerto del hub>/grid/register/ -browser browserName=firefox  -port <Número de puerto>

4. configurar e iniciar el navegador internet explorer
4.1 Descargar el driver IEDriverServer adecuado para el navegador
4.2 iniciar el navegador en la consola:
java -Dwebdriver.ie.driver=<ubicación del driver>  -jar <ubicación del servidor de selenium> -role webdriver -hub  <dirección ip del hub>/<puerto del hub>/grid/register/ -browser browserName=ie,platform=WINDOWS -port <Número de puerto>

5. ejecutar las pruebas con testNG
5.a finalmente se ejecutara el archivo "testNGGrid" ubicado en la carpeta src/main/resoruces con el framework testNG 

Resolución de posibles problemas:
En el caso del navegador internet explorer puede ser necesario que se deshabilite el modo protegido:
Esto se logra seleccionado "Opciones de internet" -> "Seguridad" y deseleccionar la casilla de "Habilitar modo protegido"