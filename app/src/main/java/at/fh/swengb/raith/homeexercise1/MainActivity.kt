package at.fh.swengb.raith.homeexercise1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import beans.*
import enums.EnemyType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    val player = Player("Guybrush Ulysses Threepwood", 1000, 150, true, 1, 0, 10)
    val enemies = ArrayList<LivingThing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create one of each enemey type
        enemies.add(createEnemy(EnemyType.Lion))
        enemies.add(createEnemy(EnemyType.Dragon))
        enemies.add(createEnemy(EnemyType.Bear))

        // Add some random enemeies
        for (i in 1..5) enemies.add(generateRandomEnemy())

        updatePlayerInformation()
        updateEnemyInformation()
        updatePotionCount()
    }

    fun generateRandomEnemy(): LivingThing {
        val rr = Random().nextInt().absoluteValue % EnemyType.values().size
        return createEnemy(EnemyType.values()[rr])
    }

    fun createEnemy(type: EnemyType): LivingThing {
        when (type) {
            EnemyType.Bear -> return Bear("Tibbers", 1200, 50, true)
            EnemyType.Dragon -> return Dragon("Smaug", 500, 100, true)
            EnemyType.Lion -> return Lion("Mufasa", 20, 10, true)
            else -> throw Exception()
        }
    }

    fun actionPlayerHeal(v: View) {
        player.heal()
        updatePlayerInformation()
        updatePotionCount()
    }

    fun actionPlayerAttack(v: View) {
        if (enemies.size > 0) {
            player.attack(enemies[0])

            val gameover = checkIfWonOrLost()
            if(!gameover) {
                updatePlayerInformation()
                updateEnemyInformation()
            }
        }
    }

    fun actionEnemyAttack(v: View) {
        if (enemies.size > 0) {
            enemies[0].attack(player)

            val gameover = checkIfWonOrLost()

            if(!gameover) {
                updatePlayerInformation()
                updateEnemyInformation()
            }
        }
    }

    // return true if the game is over (won or lose)
    fun checkIfWonOrLost() : Boolean
    {
        if (player.health <= 0)
        {
            showToast("Game Over!")
            returnToWelcomePage()
            return true
        }
        else
        {
            if(enemies.size > 0 && enemies[0].health <= 0)
            {
                enemies.removeAt(0)
            }

            if(enemies.size <= 0)
            {
                showToast("Won!!")
                returnToWelcomePage()
                return true
            }
        }
        return false
    }

    fun updatePlayerInformation() {
        textview_player_name.text = player.name
        textview_player_health.text = player.health.toString()
        textview_player_level.text = player.level.toString()
    }

    fun updateEnemyInformation() {
        if (enemies.size > 0) {
            textview_enemy_health.text = enemies[0].health.toString()
            textview_enemy_name.text = enemies[0].name
        }
    }

    fun updatePotionCount()
    {
        button_heal.setText(getString(R.string.button_heal) + " (${player.healingPotions})")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun returnToWelcomePage() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

}
