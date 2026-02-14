# Setup Instructions

## 1. Install Java

Download JDK 17 or above from:
https://adoptium.net

### Verify installation:

#### java -version
#### javac -version


---

## 2. Clone Repository

### git clone <repo-url>
### cd meditrack


---

## 3. Compile Project

### javac -d out src/main/java/com/airtribe/meditrack/**/*.java


---

## 4. Run Application

### java -cp out com.airtribe.meditrack.Main


---

## 5. Run with Data Load

### java -cp out com.airtribe.meditrack.Main --loadData


---

## 6. Run TestRunner

### java -cp out com.airtribe.meditrack.test.TestRunner



