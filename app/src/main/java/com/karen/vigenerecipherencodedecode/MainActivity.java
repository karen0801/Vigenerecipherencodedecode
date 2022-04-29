package com.karen.vigenerecipherencodedecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Editable key;
    Editable message;
    String encryptedMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ok = (Button) findViewById(R.id.button);
        RadioButton btnencode = (RadioButton) findViewById(R.id.radioencode);
        RadioButton btndecode = (RadioButton) findViewById(R.id.radiodecode);
        EditText editmessage = (EditText) findViewById(R.id.editTextMessage);
        EditText editkey = (EditText) findViewById(R.id.editTextTextKey);
        TextView tvmessage = (TextView) findViewById(R.id.textView);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (btnencode.isChecked()){
                    message = editmessage.getText();
                    key = editkey.getText();
                    encryptedMsg = encrypt(message.toString(),key.toString());
                    tvmessage.setText(encryptedMsg);
                }
                else if (btndecode.isChecked()){
                    message = editmessage.getText();
                    key = editkey.getText();
                    encryptedMsg = decrypt(message.toString(),key.toString());
                    tvmessage.setText(encryptedMsg);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please select Encode or Decode message", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public static String encrypt(String text, String key)
    {
        String res = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    public static String decrypt(String text, String key)
    {
        String res = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
}