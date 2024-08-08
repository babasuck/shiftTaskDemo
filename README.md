# shiftTaskDemo

## Description

Utility for parse files from cmd line and split lines depends type of line content: integers float or strings.

```dtd
Input sample -f -p sample- -o D:/ in1.txt

Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500

Output sample-strings.txt

consectetur adipiscing
тестовое задание

Output sample-integers.txt
45
100500

Output sample-floats.txt
3.1415
-0.001
```

## Requirements
Test task for shift Java course by CFT\
Version of Java JDK - Java SE 22 \
Build system - Maven 4.0.0 \
Dependencies - Jcommander to parse command line parameters, JUnit 5 

```dtd
        <dependency>
            <groupId>org.jcommander</groupId>
            <artifactId>jcommander</artifactId>
            <version>1.83</version>
        </dependency>
```
## Usage

````
Usage: <main class> [options] Files

Options:

-a, -append  
Append mode. If disabled new files will create.  
Default: false

-f, -full  
Print verbose statistics.  
Default: false

-o, -output  
Filtered files output path.  
Default: <empty string>

-p, -prefix  
Output files prefix.  
Default: result-

-s, -small  
Print small statistics.  
Default: false
````