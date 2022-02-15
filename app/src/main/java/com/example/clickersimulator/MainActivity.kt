package com.example.clickersimulator

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //Инициализация переменных под звуки
    private var soundPool: SoundPool? = null
    private var soundId1 = 0
    private var soundId2 = 0
    private var soundId3 = 0
    private var soundId4 = 0
    private var soundId5 = 0
    private var soundId6 = 0
    private var soundId7 = 0
    private var soundPriorityGlobal = 1
    private var leftVolume = 1F
    private var rightVolume = 1F
    private var zvukovZaRaz = 1
    //Инициализация переменных под анимацию и перемещение
    var position_X = 0
    var position_Y = 0
    var mainScore = 0
    var delay_Time = 2L
    var randomMeme = 0

    fun playSound(sound: Int) {
        var soundIdBuffer = 0
        when(sound)
        {
            1 -> soundIdBuffer = soundId1
            2 -> soundIdBuffer = soundId2
            3 -> soundIdBuffer = soundId3
            4 -> soundIdBuffer = soundId4
            5 -> soundIdBuffer = soundId5
            6 -> soundIdBuffer = soundId6
            7 -> soundIdBuffer = soundId7
            else -> soundIdBuffer = 0
        }
        soundPool?.play(soundIdBuffer,leftVolume,rightVolume,0,0,1F)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool(zvukovZaRaz, AudioManager.STREAM_MUSIC, 0)
        soundId1 = soundPool!!.load(baseContext, R.raw.cygan, soundPriorityGlobal)
        soundId2 = soundPool!!.load(baseContext, R.raw.nakls, soundPriorityGlobal)
        soundId3 = soundPool!!.load(baseContext, R.raw.nigers, soundPriorityGlobal)
        soundId4 = soundPool!!.load(baseContext, R.raw.putin, soundPriorityGlobal)
        soundId5 = soundPool!!.load(baseContext, R.raw.shrek, soundPriorityGlobal)
        soundId6 = soundPool!!.load(baseContext, R.raw.nigers, soundPriorityGlobal)
        soundId7 = soundPool!!.load(baseContext, R.raw.nigers, soundPriorityGlobal)


        clicable_roundCell.setOnClickListener {
            MainScope().launch {
                randomMeme = (1..5).random()
                for (i in 1..30) {
                    clicable_roundCell.rotationX += 3
                    delay(delay_Time)
                }
                mainScore++
                findViewById<LinearLayout>(R.id.clicable_roundCell).setBackgroundResource(
                    when (randomMeme) {
                        1 -> R.drawable.cygan
                        2 -> R.drawable.nakls
                        3 -> R.drawable.nigers
                        4 -> R.drawable.putin
                        5 -> R.drawable.shrek
                        else -> R.drawable.ic_launcher_foreground
                    }
                )
                playSound(randomMeme)
                display_main_score.text = mainScore.toString()
                for (i in 1..90) {
                    clicable_roundCell.rotationX += 3
                    delay(delay_Time)
                }
                delay(delay_Time)
                next_frame()
            }
        }
    }

    fun next_frame() {
        position_X = (0..580).random()
        position_Y = (0..1000).random()
        (clicable_roundCell.layoutParams as FrameLayout.LayoutParams).topMargin = position_Y
        (clicable_roundCell.layoutParams as FrameLayout.LayoutParams).leftMargin = position_X
        main_container.removeView(clicable_roundCell)
        main_container.addView(clicable_roundCell)
    }
}
