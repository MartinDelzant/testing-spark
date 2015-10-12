package common

import org.apache.spark._

object WordCount {
  def main(args:Array[String]): Unit ={
    val inputFile = args(0)
    val outputFile = args(1)
    val conf = new SparkConf().setAppName("wordcount")
    val sc = new SparkContext(conf)
    val data = sc.textFile(inputFile).cache()
    data.flatMap(_.split("\\W+"))
        .map(x => (x,1))
        .reduceByKey((a,b) => a+b)
        .saveAsTextFile(outputFile)
  }
}
