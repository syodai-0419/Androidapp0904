package jp.co.meijou.android.s221205128;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.co.meijou.android.s221205128.databinding.ActivityMain3Binding;
import jp.co.meijou.android.s221205128.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()){
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result:" + text)
                                .ifPresent(text -> binding.textViewResult.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.textViewResult.setText("Result: Canceled");
                        break;
                    default:
                        binding.textViewResult.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonMei.setOnClickListener(view ->{
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });

        binding.buttonAn.setOnClickListener(view ->{
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.buttonSend.setOnClickListener(view ->{
            var intent =  new Intent(this, SubActivity.class);
            intent.putExtra("text", binding.editTextData.getText().toString());
            startActivity(intent);
        });

        binding.buttonLaunch.setOnClickListener(view ->{
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });
    }
}