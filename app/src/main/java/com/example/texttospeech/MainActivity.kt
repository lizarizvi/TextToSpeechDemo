package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.texttospeech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts : TextToSpeech? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        tts = TextToSpeech(this, this)
        binding?.speakBtn?.setOnClickListener {
            if (binding?.textWritten?.text!!.isEmpty()) {
                Toast.makeText(this, "Enter the text to speak", Toast.LENGTH_SHORT).show()
            }else{
                //SPEAK THE TEXT PLEASE
                speakOut(binding?.textWritten?.text.toString())
            }
        }
    }

    override fun onInit(p0: Int) {

    }

    private fun speakOut(text : String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts!=null){
            tts?.stop()
            tts?.shutdown()
        }
        binding = null
    }
}