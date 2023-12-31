package jp.co.meijou.android.s221205128;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.co.meijou.android.s221205128.databinding.ActivityNetworkBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityNetworkBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button5.setOnClickListener(view ->{
            var text = binding.editTextText3.getText().toString();
            var uri = Uri.parse("https://placehold.jp/400x400.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();
            getImage(uri);
        });
        // リクエスト先にgistを指定

    }

    private void getImage(String url){

        var request = new Request.Builder()
                .url(url)
                .build();

        //非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                // InputStreamをBitmapに変換
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                runOnUiThread(() -> binding.imageViewBitmap.setImageBitmap(bitmap));
                /*
                //通信に成功したら呼ばれる
                //gitJsonAdapterを使ってレスポンスボディをGist型に変換
                var gist = gistJsonAdapter.fromJson(response.body().source());
                //中身の取り出し
                Optional.ofNullable(gist)
                        //filesの中からOkHttp.txtを取り出し
                        .map(g ->g.files.get("OkHttp.txt"))
                        .ifPresent(gistFile ->{

                            UIスレッド以外で更新するとクラッシュするので，UIスレッド上で実行させる
                            onRespnse()はまだサブスレッドで動いているので
                            runOnUiThread()でTextViewの更新をメインスレッドで動かす

                            runOnUiThread(() -> binding.textContent.setText(gistFile.content));
                        });
                */
            }
        });

    }
}