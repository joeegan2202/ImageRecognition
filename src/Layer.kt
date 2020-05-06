import kotlin.random.Random

data class Ideal(var weights: ArrayList<ArrayList<Double>>, var children: ArrayList<Double>)

class Layer (childNodes: Int, size: Int, random: Boolean, weights: ArrayList<ArrayList<Double>> = ArrayList(), biases: ArrayList<Double> = ArrayList()) {
    private val weights: ArrayList<ArrayList<Double>>
    private val biases: ArrayList<Double>
    private val states: ArrayList<Double> = ArrayList()
    private val size: Int
    private val childNodes: Int
    private var childStates: ArrayList<Double> = ArrayList()

    init {
        if(random){
            this.size = size
            this.childNodes = childNodes
            this.weights = ArrayList(size)
            this.biases = ArrayList(size)

            for(i in 0 until size){
                this.biases.add(Random.nextDouble(-1.0, 1.0))
                var temp = ArrayList<Double>(childNodes)
                for(j in 0 until childNodes){
                    temp.add(Random.nextDouble(-1.0, 1.0))
                }
                this.weights.add(temp)
            }
        } else {
            this.weights = weights
            this.size = weights.size
            this.childNodes = weights[0].size
            this.biases = biases
        }
    }

    fun activate(inputs: ArrayList<Double>){
        childStates = inputs
        for(i in 0 until size){
            var temp = biases[i]
            for(j in 0 until childNodes){
                temp += weights[i][j] * inputs[j]
            }
            states.add(Math.tanh(temp))
        }
    }

    fun cost(ideals: ArrayList<Double>): Ideal {
        var differences = ArrayList<Double>()
        var ideal = Ideal(ArrayList(size), ArrayList(childNodes))
        for(state in states){
            differences.add(Math.abs(ideals[states.indexOf(state)]-state))
        }

        var tempChildren = ArrayList<ArrayList<Double>>(childNodes)

        for(i in 0 until size){
            tempChildren.add(i, ArrayList())
            var tempWeights = ArrayList<Double>(childNodes)
            for(j in 0 until childNodes){
               if(ideals[i]*childStates[j] > 0) {
                   tempWeights.add(j, differences[i])
                   tempChildren[i].add(if(childStates[j]>0) 1.0 else -1.0)
               } else {
                   tempWeights.add(j, -differences[i])
                   tempChildren[i].add(if(childStates[j]>0) -1.0 else 1.0)
               }
            }
            ideal.weights.add(i, tempWeights)
        }

        for(i in 0 until childNodes){
            var total = 0.0
            for(j in 0 until size){
                total += tempChildren[j][i]
            }
            ideal.children.add(i, total/size)
        }
        for(child in tempChildren){
            print(child[0])
        }

        return ideal
    }

    override fun toString(): String{
        var string = ""

        string = states.toString()

        return string
    }
}