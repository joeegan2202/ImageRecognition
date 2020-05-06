class Network(layerProto: ArrayList<Int>, inputs: Int, outputs: Int){
    var layers = ArrayList<ArrayList<Layer>>()
    val inputs = inputs
    val outputs = outputs
    val innerSize = (inputs+outputs)/2

    init {
        for (i in 0 until layerProto.size){
            var networkLayer = ArrayList<Layer>()

            for (j in 0 until layerProto[i]){
                when(i) {
                    0 -> networkLayer.add(
                        j,
                        Layer(inputs / layerProto[i], innerSize / layerProto[i], true)
                    )
                    layerProto.size - 1 -> networkLayer.add(
                        j,
                        Layer(innerSize / layerProto[i], outputs / layerProto[i], true)
                    )
                    else -> {
                        networkLayer.add(
                            j,
                            Layer(innerSize/layerProto[i], innerSize/layerProto[i], true)
                        )
                    }
                }
            }

            layers.add(i, networkLayer)
        }
    }

    fun testRun(inputs: ArrayList<Double>){
        if(inputs.size % layers[0].size != 0){
            print("Inputs are invalid for the current network!")
            return
        }

        var size = inputs.size/layers[0].size
        for(i in 0 until layers[0].size){
            layers[0][i].activate(ArrayList(inputs.subList((i*size), (i*size+size)-1)))
        }

        print(layers[0][0])
    }

    fun unifyLayer(layerNum: Int){

    }
}