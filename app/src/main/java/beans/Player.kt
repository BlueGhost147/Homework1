package beans

class Player(
        name : String , health : Int, attackStrenght : Int, isAlive : Boolean,
        var level : Int, var experience : Int, var healingPotions : Int
) : LivingThing(name, health, attackStrenght, isAlive) {


    fun heal()
    {
        if(healingPotions > 0) {
            healingPotions--;
            health += 50;
        }
    }
}