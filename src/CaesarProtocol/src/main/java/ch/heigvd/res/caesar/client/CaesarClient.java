package ch.heigvd.res.caesar.client;

import ch.heigvd.res.caesar.protocol.Protocol;
import ch.heigvd.res.caesar.protocol.Message;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import com.google.gson.*;
import java.util.Objects;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch), Romain Albasini, Guillaume Serneels
 */
public class CaesarClient {

   private static final Logger LOG = Logger.getLogger(CaesarClient.class.getName());

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tH:%1$tM:%1$tS::%1$tL] Client > %5$s%n");
      LOG.info("Caesar client starting...");
      LOG.info("Protocol constant: " + Protocol.A_CONSTANT_SHARED_BY_CLIENT_AND_SERVER);

      Socket clientSocket = null;
      PrintWriter outToServer = null;
      BufferedReader inFromServer = null;
      String msgSend = "";
      String msgReceived = "";

      try {
         // Se connecte au serveur
         clientSocket = new Socket("localhost", Protocol.PORT);

         // Initialise les streams/buffers d'entrée/sortie
         outToServer = new PrintWriter(clientSocket.getOutputStream());
         inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

         // Read input
         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         String messageSend = "";

         do {
            LOG.info("Taper le message");
            messageSend = in.readLine();

            // Crée la structure contenant le message et le delta généré
            Message msg = new Message(messageSend);

            String mess = msg.serialize();

            //LOG.info("CLIENT : [[" + mess + "]], long : " + mess.length());

            String s = (Message.unSerialize(mess)).desencryptPayload();

            LOG.info("Message : " + s);

            // Envoie le message au serveur
            outToServer.println(mess);
            outToServer.flush();

            // Ecoute les messages envoyés du serveur
            while ((msgReceived = inFromServer.readLine()) != null) {
               // Affiche le message reçu
               LOG.info("Reception : " + msgReceived);

               Message m2 = Message.unSerialize(msgReceived);
               String messDecrypt = m2.desencryptPayload();

               // Affiche le message
               LOG.info("Affichage : " + messDecrypt);

               break;
            }
         } while (!messageSend.equalsIgnoreCase("BYE"));

         LOG.info("Sortie");

         clientSocket.close();
      } catch (IOException ex) {

      }
   }

}
