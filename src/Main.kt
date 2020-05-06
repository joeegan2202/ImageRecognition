import kotlin.random.Random
import kotlin.collections.ArrayList

fun main(args: Array<String>){
    var proto = ArrayList<Int>()
    proto.add(4)
    proto.add(3)
    proto.add(3)
    proto.add(1)

    var testNetwork = Network(proto, 16, 2)
    var inputs = ArrayList<Double>()
    for(i in 0..31){
        inputs.add(Random.nextDouble(-1.0, 1.0))
    }

    testNetwork.testRun(inputs)
    //testLayer.activate(inputs)
    //println(testLayer.states)

    var ideals = ArrayList<Double>(32)
    for(i in 0 until 32){
        ideals.add(i, Random.nextDouble(-1.0, 1.0))
    }
    //println(testLayer.cost(ideals).children)
}