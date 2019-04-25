# Streash

## Introduction

This project is a java shell interpreting command written in *reverse polish notation*. You'll be able to find the command later in this tutorial.

## User

In this section, you will be given all the command you can use.

### Commands

* \<integers\> : create a stream of integers
* \<revintegers\> : create a reverse stream of integers
* \<fibo\> : create a stream following the fibonnaci sequence
* \<random\> : create a stream with randomly generated element
* \<slice\> : take a part of the stream
* \<get\> : get the ith element of the stream
* \<sum\> : do the sum of all the elements of the stream
* \<product\> : do the product of all the element of the stream
* \<inter\> : do the intersection between two streams
* \<add\> : add two numbers
* \<sub\> : sub two numbers
* \<mul\> : multiply two numbers
* \<div\> : divide two numbers
* \<sort\> : sort the stream
* \<concat\> : concatenate two streams
* \<repeat\> : repeat the stream a given amount of times
* \<shuffle\> : suffle a stream wether it is already unordered or not
* \<average\> : do the average between all the elements of the stream
* \<len\> : get the length of the stream
* \<min\> : get the minimum of the stream
* \<max\> : get the maximum of the stream
* \<print\> : print the stream

### Meta Commands
* /printvar [name_of_the_var]
* /printvars : print all the vars
* /printvars alpha : print all the vars in a alphabetical manner
* /quit : quit the shell

### Example(s)

Here is/are examples of what you could write in the shell.

```
1 <integers> 0 10 <slice> <sum>
# This give you the sum of all elements ranging from 0 to 10
```


## Developer

### Architecture of the project
The project can be divided in two types of classes. On one side you have the data structures, on the other, you have the command.
The variables are located in the directory *"variables"*. The commands are located in the *"commands"* directory.

#### Overview

All data structures are implementing the ***Variable*** interface. This interface has some sub interfaces such as VarStream which is meant to be used by stream data structure.
All commands are extending the ***AbstractCommand*** class. The general behavior of a command is : created with empty ArrayList of args. Each time you encounter a arg you **add it** to the array and then you **process** the command to get the variable.


#### Help us

If you want to add a classes to the project, you should follow those steps:
1. Add the data structure in the correct directory
2. Add the command to the correct directory
3. Add the line in the class *CommandFactory*
4. Implement the logic in the console


### TO DO

If you have time, please consider trying to implement the following elements:

 * :white_large_square: Save session (*serialize* hashmap via json)
 * :white_large_square: Import session (*deserialize* file)
