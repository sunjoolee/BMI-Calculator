package com.sunjoolee.sparta_week1_bmi

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.*

class ResultActivity : AppCompatActivity() {
    val TAG:String = "ResultActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultValueTV = findViewById<TextView>(R.id.tv_result_value)
        val resultInfoTV = findViewById<TextView>(R.id.tv_result_info)
        val resultEmojiIV = findViewById<ImageView>(R.id.iv_result_emoji)
        val returnButton = findViewById<Button>(R.id.btn_return)

        returnButton.setOnClickListener {
            finish()
        }

        val height = intent.getIntExtra("height", 0).toDouble() / 100.0
        val weight = intent.getIntExtra("weight", 0).toDouble()
        Log.d(TAG, "height: $height, weight: $weight")

        var bmiValue = weight / (height * height)
        //소수점 아래 2번째 자리까지 남기기
        bmiValue = round(bmiValue * 100.0)/100.0

        val bmiLevel =
            if(bmiValue < 18.5) 1 //저체중
            else if (18.5 <= bmiValue && bmiValue <= 24.9) 2 //정상
            else if (24.9 < bmiValue && bmiValue <= 29.9) 3 //과체중
            else if (29.9 < bmiValue && bmiValue <= 39.9) 4 //비만
            else 5 //고도비만
        Log.d(TAG, "bmi value: $bmiValue, bmi level: $bmiLevel")

        var bmiInfo: String = ""
        var bmiInfoTextColorId:Int = 0
        var bmiEmojiId:Int = 0

        when(bmiLevel){
            1->{
                bmiInfo = getString(R.string.level1_info)
                bmiInfoTextColorId = R.color.level1_text_color
                bmiEmojiId = R.drawable.bmi1
            }
            2->{
                bmiInfo = getString(R.string.level2_info)
                bmiInfoTextColorId = R.color.level2_text_color
                bmiEmojiId = R.drawable.bmi2
            }
            3->{
                bmiInfo = getString(R.string.level3_info)
                bmiInfoTextColorId = R.color.level3_text_color
                bmiEmojiId = R.drawable.bmi3
            }
            4->{
                bmiInfo = getString(R.string.level4_info)
                bmiInfoTextColorId = R.color.level4_text_color
                bmiEmojiId = R.drawable.bmi4
            }
            5->{
                bmiInfo = getString(R.string.level5_info)
                bmiInfoTextColorId = R.color.level5_text_color
                bmiEmojiId = R.drawable.bmi5
            }
            else ->{}
        }

        resultValueTV.text = bmiValue.toString()
        resultInfoTV.text = bmiInfo
        resultInfoTV.setTextColor(resources.getColor(bmiInfoTextColorId))
        resultEmojiIV.setImageDrawable(resources.getDrawable(bmiEmojiId))
    }


}