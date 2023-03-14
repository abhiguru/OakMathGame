package `in`.tutorial.oakmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import `in`.tutorial.oakmathgame.databinding.ActivityGameBinding
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    var binding: ActivityGameBinding? = null
    var correctAns:Int = 0
    var userScore:Int = 0
    var userLife:Int = 3
    lateinit var timer:CountDownTimer
    private val startTimerInMillis:Long = 10000
    private var timeLeftInMillis : Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar!!.title = "Addition"
        gameContinue()
        binding!!.btnOk.setOnClickListener {
            var input = binding!!.etAnswer.text.toString()
            if(input.isEmpty()){
                Toast.makeText(this@GameActivity, "Enter Ans ", Toast.LENGTH_LONG).show()
            }else{
                pauseTimer()
                val userAns = input.toInt()
                if(userAns == correctAns){
                    userScore = userScore + 10
                    binding!!.tvQuestion.text = "Congrats, Correct answer"
                    binding!!.tvScore.text = userScore.toString()
                }else{
                    userLife--
                    binding!!.tvQuestion.text = "Wrong answer"
                    binding!!.tvLife.text = userLife.toString()
                }
            }
        }
        binding!!.btnNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            binding!!.etAnswer.setText("")

            if(userLife==0){
                Toast.makeText(this@GameActivity, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            }else{
                gameContinue()
            }
        }
    }

    fun gameContinue(){
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)
        binding!!.tvQuestion.text = "$number1 + $number2"
        correctAns = number1 + number2
        startTimer()
    }

    fun startTimer(){
        timer = object: CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
                //val timeText = binding!!.tvTime.text.toString()
            }
            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                binding!!.tvLife.text = userLife.toString()
                binding!!.tvQuestion.text = "Sorry, Your time is up"
            }
        }.start()
    }

    fun updateText(){
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        binding!!.tvTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer(){
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
}