package metodos;


import java.net.InetAddress;
//Importamos las librerias de Apache Commons


import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class ObtenerFecha {
 
   //Declaramos el servidor de donde obtendremos la fecha

   String servidor = "south-america.pool.ntp.org";
   public Date getNTPDate() {
        //Se le da un valor nulo por defecto a la variable

       Date fechaRecibida = null;
       //Se crea un objeto de tipo NTPUDPClient Clase de la libreria Commons

       NTPUDPClient cliente = new NTPUDPClient();
      //Tiempo de Espera Antes De Mandar Error.

       cliente.setDefaultTimeout(5000);
       try {
           //Obtenemos la direccion IP por medio de el host previamente Asignado

           InetAddress hostAddr = InetAddress.getByName(servidor);
           //Solicitamos la fecha al servidor

           TimeInfo fecha = cliente.getTime(hostAddr);
           //Recibimos y convertimos la fecha a formato DATE

           fechaRecibida = new Date(fecha.getMessage().getTransmitTimeStamp().getTime());
       } catch (Exception e) {
           return null;
       }
       //Cerramos la comunicación con el cliente

       cliente.close();
       //Retornamos la fecha ya convertida si no es nula , de ser nula regresa la fecha Local

       return fechaRecibida == null ? new Date() : fechaRecibida ;
     
   }
   
   

}