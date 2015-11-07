package parser

import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD

/*
Usage : IrisParser( <your Spark Context>, "/path/to/iris.csv")
 */
object IrisParser {

  def parseRaw(line:String):LabeledPoint = {
    val list = line.split(",")
    val categ = list(4) match {
      case "Iris-setosa" => 0
      case "Iris-versicolor" => 1
      case "Iris-virginica" => 2
      case _ => throw new MatchError("Iris parseRaw matching error")
    }
    LabeledPoint(categ, Vectors.dense(list.dropRight(1).map(_.toDouble)))
  }

  def apply(sc:SparkContext, path:String):RDD[LabeledPoint] = {
    sc.textFile(path).map(parseRaw _)
  }

}
