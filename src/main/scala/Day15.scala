package com.day15

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Day15 {

  // Scala function for customer risk classification
  def classifyRisk(transactionValue: Double): String = {
    if (transactionValue >= 50000)
      "High Risk"
    else if (transactionValue >= 20000)
      "Medium Risk"
    else
      "Low Risk"
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Scala Spark Day 15 - UDF Practice")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    println("========================================")
    println("DAY 15 - UDF PRACTICE")
    println("========================================")

    // Read transaction data
    val transactionsDF = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("transactions.csv")

    println("\n1. ORIGINAL TRANSACTION DATA")
    transactionsDF.show()

    // Create Scala UDF
    val riskUDF = udf(classifyRisk _)

    // Apply UDF using withColumn
    val riskDF = transactionsDF
      .withColumn(
        "risk_category",
        riskUDF(col("transaction_value"))
      )

    println("\n2. UDF - CUSTOMER RISK CATEGORY")
    riskDF.show()

    // Built-in Spark function comparison
    val builtInDF = transactionsDF
      .withColumn(
        "transaction_level",
        when(col("transaction_value") >= 50000, "High")
          .when(col("transaction_value") >= 20000, "Medium")
          .otherwise("Low")
      )

    println("\n3. BUILT-IN SPARK FUNCTION")
    builtInDF.show()

    // Register UDF with Spark SQL
    spark.udf.register(
      "classifyRisk",
      classifyRisk _
    )

    println("\n4. REGISTERED UDF")
    println("UDF 'classifyRisk' registered successfully.")

    // Temporary view
    riskDF.createOrReplaceTempView("customer_transactions")

    // Execute registered UDF through Spark SQL
    val sqlResult = spark.sql(
      """
        SELECT
          customer_id,
          customer_name,
          transaction_value,
          classifyRisk(transaction_value) AS sql_risk_category
        FROM customer_transactions
        ORDER BY transaction_value DESC
      """
    )

    println("\n5. REGISTERED UDF USING SPARK SQL")
    sqlResult.show()

    // Risk category summary
    val riskSummary = riskDF
      .groupBy("risk_category")
      .count()
      .orderBy("risk_category")

    println("\n6. RISK CATEGORY SUMMARY")
    riskSummary.show()

    println("\n7. UDF vs BUILT-IN FUNCTION")
    println("UDF      : Custom Scala logic that can be reused in Spark.")
    println("Built-in  : Native Spark functions such as when(), otherwise().")
    println("Built-in functions are generally preferred when they can solve the problem.")

    println("\n8. DAY 15 CONCEPTS")
    println("Scala Function -> udf() -> DataFrame withColumn()")
    println("Registered UDF can also be called from Spark SQL.")
    println("Customer risk is classified using transaction value.")

    println("\n========================================")
    println("DAY 15 COMPLETED SUCCESSFULLY")
    println("========================================")

    spark.stop()
  }
}
