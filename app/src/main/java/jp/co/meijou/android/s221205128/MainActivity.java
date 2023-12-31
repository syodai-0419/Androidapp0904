package jp.co.meijou.android.s221205128;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import jp.co.meijou.android.s221205128.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);
        //binding.text.setText(R.string.text);
        prefDataStore = PrefDataStore.getInstance(this);

        //.ifPresent() は 「getString(“name”)の結果がnullでないときに、引数の処理を実行する」というOptionalのメソッド
        /*
        prefDataStore.getString("name").ifPresent(name ->binding.text.setText(name));
         */
        binding.button.setOnClickListener(view -> {
            binding.text.setText(R.string.meijo_u);
        });

        binding.button2.setOnClickListener(view -> {
            binding.imageView2.setImageResource(R.drawable.seat_flat_angled);
        });

        binding.button3.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            binding.text.setText(text);
        });

        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });
        Log.d("basketball!", "onStart text: "+binding.text.getText());
        /*
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.text.setText(editable.toString());
            }
        });
         */
    }


    protected void onStart() {
        super.onStart();
        //onCreate() から処理を消し、onStart() に移動する
        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));
    }

}