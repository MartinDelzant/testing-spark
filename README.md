# testing-spark
Testing Spark 1.5.1 for personal purposes

## Requirements

 * Scala: 2.10.6
 * SBT : 0.13.9
 * Spark: 1.5.1 
 - I personnaly used the prebuilt version with Hadoop 2.6, available from [here](http://www.apache.org/dyn/closer.lua/spark/spark-1.5.1/spark-1.5.1-bin-hadoop2.6.tgz)

## Usage

### Cloning

  git clone https://github.com/Tinmard/testing-spark

### Building the jar

To be able to submit our app with spark-submit, we need to build a fat jar :

  cd testing-spark
  sbt package

The .jar will be located in the target/scala-2.10/ folder

### WordCount

Launch : 
  $SPARK\_HOME/bin/spark-submit --class "common.WordCount" --master=local[n] testing-spark/scala-2.10/testingspark\_2.10-1.0.jar inputFile outputDirectory

The output is a directory because the method .saveAsTextFile (on RDD's) is called

