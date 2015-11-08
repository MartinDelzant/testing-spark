package common

import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.optimization.{L1Updater, SquaredL2Updater}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import parser.IrisParser
import org.apache.spark.{SparkConf, SparkContext}

object IrisLogisticRegression {

  def main (args: Array[String]): Unit = {
    /*
    USAGE :
    Just give two params when running the program :
    - "l1" or "l2" depending on which kind of regularization you want to perform
    - a Double value that will be the regularization
     */

    val conf = new SparkConf().setAppName("TestingRF_on_Iris")
    val sc = new SparkContext(conf)

    val data : RDD[LabeledPoint] = IrisParser(sc,"data/iris.csv")
    val splits = data.randomSplit(Array(0.75,0.25), seed = 1L)
    val training = splits(0).cache()
    val testing = splits(1)

    val model = new LogisticRegressionWithLBFGS()
      .setNumClasses(3)

    model.optimizer
      .setUpdater(args(0) match {
      case "l1" => new L1Updater
      case "l2" => new SquaredL2Updater
    })
      .setRegParam(args(1).toDouble)

    val regularizedModel = model.run(training)

    val prediction = testing.map{
      case LabeledPoint(label, features) => (regularizedModel.predict(features), label)
    }

    val metrics = new MulticlassMetrics(prediction)
    println("Precision Test set = " + metrics.precision)
    val confusionMatrix = metrics.confusionMatrix
    print("Confusion Matrix :")
    print(confusionMatrix)
  }
}
