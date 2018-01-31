object Main {

    def lg(base: Int, n: Double) : Double = {
        math.log(n)/math.log(base)
    }

    def y(x: Double) : Double = {
        math.sin(x*x/2) / lg(2, x+4)
    }

    def main(args: Array[String]) : Unit = {
        val ys: Array[Double] = Array(11)
        for (x <- 0 to 10) {
            val fx = y(x.toDouble)
            println(x + " : " + fx)
            ys(x) = fx
        }
    }

}
