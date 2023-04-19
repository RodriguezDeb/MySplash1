package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysplash.Service.DbUsuarios;
import com.example.mysplash.des.MyDesUtil;
import com.example.mysplash.json.MyInfo;

import java.util.List;

public class login2 extends AppCompatActivity {
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    private String testClaro = "Hola mundo";
    private String testDesCifrado;
    public String correo;
    public String mensaje;
    public static List<MyInfo> list;
    public static String TAG = "mensaje";
    public static String TOG = "error";
    public static String json = null;
    public static String usr,pswd,pswd2;
    private Button button1, button2, button3;
    private DbUsuarios dbUsuarios;
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        button2 = findViewById(R.id.registroid);
        button1 = findViewById(R.id.accesarid);
        EditText txtnewpass = findViewById(R.id.newPass);
        EditText usuario2 = findViewById(R.id.userNameid);
        EditText pswds = findViewById(R.id.editTextTextPassword);

        dbUsuarios = new DbUsuarios(login2.this);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login2.this, registro.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = usuario2.getText().toString();
                String password = pswds.getText().toString();
                String newPassword = txtnewpass.getText().toString();

                if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(password)) {
                    Toast.makeText(login2.this, "Por favor, ingresa usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verifica si la contraseña actual es correcta
                boolean authenticated = dbUsuarios.authenticateUser(usuario, password);

                if (!authenticated) {
                    Toast.makeText(login2.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si la contraseña actual es correcta, verifica si se proporcionó una nueva contraseña
                if (!TextUtils.isEmpty(newPassword)) {
                    // Actualiza la contraseña del usuario en la base de datos
                    boolean result = dbUsuarios.updatePassword(usuario, newPassword);

                    if (result) {
                        Toast.makeText(login2.this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(login2.this, "Error al actualizar contraseña", Toast.LENGTH_SHORT).show();
                    }
                }

                // Inicia la actividad de la aplicación
                Intent intent = new Intent(login2.this, menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbUsuarios.close();
        super.onDestroy();
    }
}