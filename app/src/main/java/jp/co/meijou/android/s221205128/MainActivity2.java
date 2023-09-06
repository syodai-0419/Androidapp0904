package jp.co.meijou.android.s221205128;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import jp.co.meijou.android.s221205128.databinding.ActivityMain2Binding;
import jp.co.meijou.android.s221205128.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    private int[] saveNumber = new int[5];
    private int actionNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button0.setOnClickListener(view ->{
            binding.textViewNumber.append("0");
        });
        binding.button1.setOnClickListener(view ->{
            binding.textViewNumber.append("1");
        });
        binding.button2.setOnClickListener(view ->{
            binding.textViewNumber.append("2");
        });
        binding.button3.setOnClickListener(view ->{
            binding.textViewNumber.append("3");
        });
        binding.button4.setOnClickListener(view ->{
            binding.textViewNumber.append("4");
        });
        binding.button5.setOnClickListener(view ->{
            binding.textViewNumber.append("5");
        });
        binding.button6.setOnClickListener(view ->{
            binding.textViewNumber.append("6");
        });
        binding.button7.setOnClickListener(view ->{
            binding.textViewNumber.append("7");
        });
        binding.button8.setOnClickListener(view ->{
            binding.textViewNumber.append("8");
        });
        binding.button9.setOnClickListener(view ->{
            binding.textViewNumber.append("9");
        });
        binding.buttonSplit.setOnClickListener(view ->{

        });



    }
}