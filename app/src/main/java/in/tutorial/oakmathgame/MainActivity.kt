package `in`.tutorial.oakmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import `in`.tutorial.oakmathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
    }
}