package beans

import android.util.Log

open class LivingThing(val name : String,
                  var health : Int,
                  val attackStrenght : Int,
                  var isAlive : Boolean = true) {

    open fun attack(target: LivingThing) {

        if (this.isAlive) {

            Log.i("GameLog", "Attacking ${target.name} with attackStrength: ${attackStrenght}")

            if (target.isAlive)
                target.takeDamageFrom(this, attackStrenght)
            else
                Log.v("GameLog", "But ${target.name} is already dead!")
        }
        else
            Log.v("GameLog", "${this.name} can´t attack, it is already dead!")
    }

    open fun takeDamageFrom(attacker: LivingThing, damage: Int) {
        Log.i("GameLog", "${name} is taking ${damage} damage from: ${attacker.name}")
        this.health = this.health - damage
        if (this.health <= 0) {
            this.isAlive = false
            Log.i("GameLog", "${this.name} died!")
        }

    }
}