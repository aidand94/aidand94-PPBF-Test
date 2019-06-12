# aidand94-PPBF-Test
## About
This displays conversion of a String representing match time in one format to a string in another format.

### Input Format: 
[period] minutes:seconds.milliseconds

### Output Formats (Normal, Added Time):
normalTimeMinutes:normalTimeSeconds - period

normalTimeMinutes:normalTimeSeconds +additionalMinutes:additionalSeconds - period

# Compiling and Running

## Linux (Ubuntu 18.04.02)
1. Open terminal and clone the repo
```
git clone git@github.com:aidand94/aidand94-PPBF-Test.git
```

2. If JDK is not installed
```
sudo apt-get install openjdk-11-jdk
```

3. Compile
```
cd aidand94-PPBF-Test
javac MatchTimeFormatter.java
```

4. Run
```
java MatchTimeFormatter
```

# Notes
### Assumptions 
There's an example that states
```
if Input == "[FT] 90:00.000" 
then Expected Output == "90:00 +00:00 – FULL_TIME"
```
I assume this incorrect? The reason for this is, is reading the brief it states:

"When a given period goes into additional time (i.e. > 45:00.000 for first half, > 90.00.000 for the second half), the added minutes and seconds are represented separately in the format"

If what's given in the examples is correct then shouldn't the example 
```
Input: "[HT] 45:00.000"
Expected Output: "45:00 – HALF_TIME"
Output following the same form as previous example: "45:00 +00:00 - HALF_TIME"
```
I emailed about this confusion and hadn't heard back yet so I've stuck to my assumption.

#### Changes if assumption is incorrect
Line 45:
```
if(((minutes >= 90)) && ((periodShortForm.equals("H2") || periodShortForm.equals("FT")))) {
```
This would give all the correct expected outputs.

### Workings
I've also added some images with some quick dooles on a page that might give some more insight into my thought processes.

### Git repo
If you'd like me to make this repo private just ask :)
