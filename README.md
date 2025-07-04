# 🧮 String Calculator - TDD 

This repository contains a Java implementation of the **String Calculator Kata** using **Test-Driven Development (TDD)** principles. Each functionality was added incrementally with tests written first, following the red-green-refactor cycle.

---

## 📖 Problem Statement

Implement a calculator that accepts a string of numbers separated by delimiters and returns their sum. This exercise helps reinforce core TDD practices.

---

## ✅ Features

| Feature | Description |
|--------|-------------|
| ✅ Empty input returns 0 | `add("") → 0` |
| ✅ Single number returns itself | `add("4") → 4` |
| ✅ Two numbers return their sum | `add("1,5") → 6` |
| ✅ Multiple numbers supported | `add("1,2,3") → 6` |
| ✅ Supports newline (`\n`) as delimiter | `add("1\n2,3") → 6` |
| ✅ Custom delimiters (e.g. `//;\n1;2`) | `add("//;\n1;2") → 3` |
| ✅ Multiple custom delimiters | `add("//[*][%]\n1*2%3") → 6` |
| ✅ Multi-character delimiters | `add("//[***]\n1***2***3") → 6` |
| ✅ Throws on negative numbers | `add("1,-2") → IllegalArgumentException` |
| ✅ Lists all negative numbers in exception | `add("1,-2,-3") → "Negative numbers not allowed: [-2, -3]"` |
| ✅ Handles overflow with `ArithmeticException` | `add("2147483647,1") → ArithmeticException` |
| ✅ Non-numeric input throws `NumberFormatException` | `add("1,abc,3") → NumberFormatException` |

---

## 🧪 TDD Process Followed

This project strictly follows the **Red-Green-Refactor** cycle:

1. 🔴 Write a failing test for a new feature.
2. ✅ Write the minimum code to pass the test.
3. 🔁 Refactor the implementation and tests for clarity.
4. 🔄 Repeat until all specifications are covered.

Each feature was developed with a failing test first and committed after passing, demonstrating clean TDD workflow.

---

## 🚀 Getting Started

To run this project locally:
```bash
git clone https://github.com/your-username/string-calculator-tdd.git
cd string-calculator-tdd
mvn test
```
---

### 🔧 Requirements
- Java 17+
- JUnit 5
- Gradle or Maven
