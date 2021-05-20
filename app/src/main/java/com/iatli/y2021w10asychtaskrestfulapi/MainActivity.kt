package com.iatli.y2021w10asychtaskrestfulapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONObject


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    val URL_JOKE:String = "http://api.icndb.com/jokes/random"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun fake_downloder_click(view: View) {
        val f = FakeDownloader(this)
        f.execute("Parameter or URL that you can pass to Async Downloader")

    }

    fun cat_api_click(view: View) {
        //cat cat url
        Ion.with(this)
                .load("https://api.thecatapi.com/v1/images/search")
                .asString()
                .setCallback({ ex, result ->
                    //
                    Log.d("CATCAT", result)
                    process_cat("{\"randimg\":" + result + "}");

                })


    }



    /*
    *
    {
        "randimg":[
            {
            "breeds":{},
            "id":"bmh",
            "url":"https://cdn2.thecatapi.com/images/bmh.jpg",
            "width":540,
            "height":360
            }
      ]
    }
    */
    fun process_cat(s: String){
        Log.d("CATCAT", s);
        val jsonObject = JSONObject(s)
        val url: String = jsonObject.getJSONArray("randimg")
                .getJSONObject(0)
                .getString("url")

        val img = findViewById<ImageView>(R.id.img_catview)
        Picasso.get()
                .load(url)
                .into(img)

    }


    fun chuck_api_click(view: View) {


        Ion.with(this)
                .load(URL_JOKE)
                .asString()
                .setCallback({ ex, result ->
                    //convert string to JSON obj
                    val json = JSONObject(result)
                    /*
                     *
                     {
                         "randimg":[
                             {
                             "breeds":{},
                             "id":"bmh",
                             "url":"https://cdn2.thecatapi.com/images/bmh.jpg",
                             "width":540,
                             "height":360
                             }
                       ]
                     }
                    */
                    val value_obj = json.getJSONObject("value")
                    val joke = value_obj.getString("joke")
                    val textviewChuck = findViewById<TextView>(R.id.txt_chuck)
                    textviewChuck.text = joke
                })
    }



}
