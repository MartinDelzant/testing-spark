# testing-spark
Testing Spark 1.5.1 for personal purposes

## Requirements

 * Scala: 2.10.6
 * SBT : 0.13.9
 * Spark: 1.5.1 
 - I personnaly used the prebuilt version with Hadoop 2.6, available from [here](http://www.apache.org/dyn/closer.lua/spark/spark-1.5.1/spark-1.5.1-bin-hadoop2.6.tgz)

## Usage

### Cloning

`git clone https://github.com/Tinmard/testing-spark`

### Building the jar

To be able to submit our app with spark-submit, we need to build a fat jar :

```
cd testing-spark
sbt package
```

The .jar will be located in the `target/scala-2.10/` folder

### Launching
  
`$SPARK\_HOME/bin/spark-submit --class "common.<ObjectName>" --master=local[n] testing-spark/scala-2.10/testingspark\_2.10-1.0.jar list_of_args`

## Implemented Functions

### Simple App

Just to test if Spark is working. It counts the number of `a` and `b` in the lines of the `inputFile`

### WordCount

Does a word count on the `inputFile` and saves it to the `outputFile`



