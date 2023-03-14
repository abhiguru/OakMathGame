package `in`.tutorial.oakmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.tutorial.oakmathgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    var binding : ActivityResultBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val score = intent.getIntExtra("score", 0);
        binding!!.tvFinalScore.text = "Score: $score"
        binding!!.btnPlayAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding!!.btnExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}