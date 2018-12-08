package beans

class Player(
        name : String , health : Int, attackStrenght : Int, isAlive : Boolean,
        var level : Int, var experience : Int, var healingPotions : Int
) : LivingThing(name, health, attackStrenght, isAlive) {

    override val attackStrenght : Int = attackStrenght
        get() {
            return field * level
        }

    override fun attack(target: LivingThing) {
        if(target.isAlive) {
            super.attack(target)
            if(!target.isAlive)
            {
                experience += 20
                if(experience > 100)
                {
                    experience -=100
                    level++
                }
            }
        }
    }

    fun heal()
    {
        if(healingPotions > 0) {
            healingPotions--;
            health += 50;
        }
    }
}