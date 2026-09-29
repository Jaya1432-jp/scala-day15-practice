## Results

### Original Transaction Data
10 transactions were successfully loaded.

| Customer ID | Name | Transaction Value |
|---|---|---:|
| C001 | Jaya | 5000 |
| C002 | Ravi | 15000 |
| C003 | Prakash | 28000 |
| C004 | Anil | 45000 |
| C005 | Suresh | 8000 |
| C006 | Kiran | 35000 |
| C007 | Arjun | 12000 |
| C008 | Manoj | 60000 |
| C009 | Rahul | 22000 |
| C010 | Vinay | 70000 |

### UDF Risk Classification

| Customer ID | Transaction Value | Risk |
|---|---:|---|
| C001 | 5000 | Low Risk |
| C002 | 15000 | Low Risk |
| C003 | 28000 | Medium Risk |
| C004 | 45000 | Medium Risk |
| C005 | 8000 | Low Risk |
| C006 | 35000 | Medium Risk |
| C007 | 12000 | Low Risk |
| C008 | 60000 | High Risk |
| C009 | 22000 | Medium Risk |
| C010 | 70000 | High Risk |

### Risk Summary

| Risk Category | Count |
|---|---:|
| High Risk | 2 |
| Medium Risk | 4 |
| Low Risk | 4 |

### UDF Registration

`classifyRisk` was successfully registered as a Spark UDF and used through Spark SQL.

### Concepts Completed

- Scala UDF
- `withColumn()`
- Built-in `when()` and `otherwise()`
- UDF registration
- Spark SQL with registered UDF
- UDF vs built-in function comparison

### Execution Status

```text
DAY 15 COMPLETED SUCCESSFULLY
Total time: 16 seconds
