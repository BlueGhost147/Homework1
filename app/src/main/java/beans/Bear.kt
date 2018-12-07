package beans

class Bear (
        name : String , health : Int, attackStrenght : Int, isAlive : Boolean,
        var aggressionLevel : Int = 0
) : LivingThing(name, health, attackStrenght, isAlive) {

    override fun takeDamageFrom(attacker : LivingThing, damage : Int)
    {
        super.takeDamageFrom(attacker, damage)

        // Check if the bear survived the attack
        if(this.isAlive) {
            aggressionLevel += 50

            if (aggressionLevel > 100) {
                this.attack(attacker)
            }
        }
    }
}