/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.caesar.protocol;


import java.util.Random;
import com.google.gson.*;
/**
 *
 * @author Romain Albasini, Guillaume Serneels
 */
public class Message {
    //Json serialization engine
    private static Gson moteurJson = new Gson();
    
    private int delta; //Delta pour le Ceasar cipher
    private String payload;
    
    public Message(String clearPayload){
        moteurJson = new Gson();
        //Generate random delta
        Random random = new Random();
        this.delta = random.nextInt(Protocol.MAX_DELTA);
        encryptPayload(clearPayload);
        
    }
    
    public Message(String clearPayload, int delta){

        this.delta = delta;
        encryptPayload(clearPayload);
        
    }
    
    //Constructeur de recopie
    public Message(Message m){
        this.payload = m.payload;
        this.delta = m.delta;
    }
    
    public String getPayload(){
        return this.payload;
    }
    
    private void encryptPayload(String clearPayload){
        payload = "";
        for(int i = 0; i < clearPayload.length(); ++i){
            char charCode = clearPayload.charAt(i);
            char c = (char)(charCode + delta);
            //System.out.println(c);
            //System.out.println();
            payload += c;
    
        }
    }    
    
    public String desencryptPayload(){
        String encryptedPayload = "";
        for(int i = 0; i < payload.length(); ++i){
            char charCode = payload.charAt(i);
            char c = (char)(charCode - delta);
            encryptedPayload += c;            
        }    
        return encryptedPayload;
    }
    
    public String serialize(){
        return moteurJson.toJson(this);
    }
    public static Message unSerialize(String s){
        Message unSer = moteurJson.fromJson(s, Message.class);
        return unSer;
    }
}
