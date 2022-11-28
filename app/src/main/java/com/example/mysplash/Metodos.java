package com.example.mysplash;

import androidx.core.util.PatternsCompat;

import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Metodos {
    public static final String TAG = "Hola";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();


    public static byte[] createSha1( String text )
    {
        MessageDigest messageDigest = null;
        byte[] bytes = null;
        byte[] bytesResult = null;
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-1");
            bytes = text.getBytes("iso-8859-1");
            messageDigest.update(bytes, 0, bytes.length);
            bytesResult = messageDigest.digest();
            return bytesResult;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static boolean validarEmail(String email){
        boolean fl;
        if(email.isEmpty()){
            fl=false;
        }else{
            if(PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
                fl=true;
            }else{
                fl=false;
            }
        }
        return fl;
    }
    public static boolean usuarios(List<MyInfo> list, String usu,String correo){
        boolean fl = false;
        for(MyInfo informacion : list){
            if(informacion.getUsuario().equals(usu)|| informacion.getCorreo().equals(correo)){
                fl=true;
            }
        }
        return fl;
    }

    public static void fillInfo(MyInfo info){
        info.setUsuario(registro.usr);
        String pass = registro.password;
        info.setPassword(pass);
        info.setCel(registro.numero);
        info.setDate(registro.fecha);
        info.setConocer(registro.box);
        info.setCorreo(registro.email);
        info.setRegion(registro.region);
        info.setSexo(registro.activado);
        info.setActivado(registro.sw);
        info.setNombre(registro.nom);
    }
    public static void vaciaJson(String json){
        json = null;
    }


    public static void encuentra(String cadena){
        for(MyInfo info: forgot_pass.list){
            if(login.usr.equals(info.getUsuario())){
                cadena = "El usuario existe, recuerde la contraseña";
            }else{
                cadena = "El usuario no existe, recuerde todo";
            }
        }
    }

}