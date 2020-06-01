package com.example.codeinandroid;

import com.example.codeinandroid.model.Data;

import java.util.ArrayList;

public class MenuData {
    private ArrayList<Data> mData = new ArrayList<>();

    public MenuData(String language) {
        switch (language) {
            case "C":
                mData.clear();
                mData.add(new Data("Introduction", "true"));
                mData.add(new Data("Program Structure", ""));
                mData.add(new Data("data types", ""));
                mData.add(new Data("Keywords & Identifiers", ""));
                mData.add(new Data("Variables", ""));
                mData.add(new Data("Constants", ""));
                mData.add(new Data("printf() and scanf()", ""));
                mData.add(new Data("Control Statements", "true"));
                mData.add(new Data("if-else", ""));
                mData.add(new Data("switch", ""));
                mData.add(new Data("for loop", ""));
                mData.add(new Data("while loop", ""));
                mData.add(new Data("do-while loop", ""));
                mData.add(new Data("break", ""));
                mData.add(new Data("continue", ""));
                mData.add(new Data("goto", ""));
                mData.add(new Data("Functions", "true"));
                mData.add(new Data("what is function?", ""));
                mData.add(new Data("call : Value & Reference", ""));
                mData.add(new Data("recursion", ""));
                mData.add(new Data("storage class", ""));
                mData.add(new Data("Array", "true   "));
                mData.add(new Data("1D-array", ""));
                mData.add(new Data("Multidimensional Arrays", ""));
                mData.add(new Data("arrays & functions", ""));
                mData.add(new Data("Pointers", "true"));
                mData.add(new Data("Pointers", ""));
                mData.add(new Data("Pointers and Arrays", ""));
                mData.add(new Data("Pointers and Functions", ""));
                mData.add(new Data("Dynamic Memory Allocation", ""));
                mData.add(new Data("Strings", "true"));
                mData.add(new Data("strings", ""));
                mData.add(new Data("gets() and puts() functions", ""));
                mData.add(new Data("string Functions", ""));
                mData.add(new Data("Structure & Unions", "true"));
                mData.add(new Data("struct", ""));
                mData.add(new Data("typedef", ""));
                mData.add(new Data("struct & functions", ""));
                mData.add(new Data("unions", ""));
                mData.add(new Data("File Handling", "true"));
                mData.add(new Data("File Handling", ""));

                break;
            case "Cpp":
                mData.clear();
                mData.add(new Data("Introduction", "true"));
                mData.add(new Data("Syntax", ""));
                mData.add(new Data("Comments", ""));
                mData.add(new Data("Variables", ""));
                mData.add(new Data("Output", ""));
                mData.add(new Data("User Input", ""));
                mData.add(new Data("Flow Control", "true"));
                mData.add(new Data("if-else", ""));
                mData.add(new Data("switch", ""));
                mData.add(new Data("for Loop", ""));
                mData.add(new Data("while Loop", ""));
                mData.add(new Data("do-while Loop", ""));
                mData.add(new Data("break", ""));
                mData.add(new Data("continue", ""));
                mData.add(new Data("goto", ""));
                mData.add(new Data("Functions", "true"));
                mData.add(new Data("Functions", ""));
                mData.add(new Data("Parameters/Arguments", ""));
                mData.add(new Data("Default Parameters", ""));
                mData.add(new Data("Multiple Parameters", ""));
                mData.add(new Data("Return Values", ""));
                mData.add(new Data("Pass By Reference", ""));
                mData.add(new Data("Function Overloading", ""));
                mData.add(new Data("Recursion", ""));
                mData.add(new Data("Arrays & String", "true"));
                mData.add(new Data("Arrays", ""));
                mData.add(new Data("Array to Function", ""));
                mData.add(new Data("Strings", ""));
                mData.add(new Data("Structures", "true"));
                mData.add(new Data("Structures", ""));
                mData.add(new Data("Structure and Function", ""));
                mData.add(new Data("Pointers", "true"));
                mData.add(new Data("Pointers", ""));
                mData.add(new Data("Dereference", ""));
                mData.add(new Data("Object-Oriented Programming", "true"));
                mData.add(new Data("OOP", ""));
                mData.add(new Data("Classes and Objects", ""));
                mData.add(new Data("Constructors", ""));
                mData.add(new Data("Constructor Overloading", ""));
                mData.add(new Data("Destructor", ""));
                mData.add(new Data("this Pointer", ""));
                break;
            case "Java":
                mData.clear();
                mData.add(new Data("Basic", "true"));
                mData.add(new Data("Syntax", ""));
                mData.add(new Data("Control Statements", "true"));
                mData.add(new Data("if-else", ""));
                mData.add(new Data("switch", ""));
                mData.add(new Data("for Loop", ""));
                mData.add(new Data("while Loop", ""));
                mData.add(new Data("do-while Loop", ""));
                mData.add(new Data("break", ""));
                mData.add(new Data("continue", ""));
                mData.add(new Data("Arrays", "true"));

                break;

            case "Python":
                mData.clear();
                mData.add(new Data("Basic", "true"));
                mData.add(new Data("Python Syntax", ""));
                mData.add(new Data("Comments", ""));
                mData.add(new Data("Variables", ""));
                mData.add(new Data("Data Types", ""));
                mData.add(new Data("Keywords", ""));
                mData.add(new Data("Operators", ""));
                mData.add(new Data("Python Flow Control", "true"));
                mData.add(new Data("if...else", ""));
                mData.add(new Data("while loop", ""));
                mData.add(new Data("for loop", ""));
                mData.add(new Data("break and continue", ""));
                mData.add(new Data("pass statement", ""));
                mData.add(new Data("Python Functions", "true"));
                mData.add(new Data("Functions", ""));
                mData.add(new Data("Function Arguments", ""));
                mData.add(new Data("Anonymous Function", ""));
                mData.add(new Data("Global, Local and Nonlocal variables", ""));
                mData.add(new Data("Global Keyword", ""));
                break;
        }
    }

    public ArrayList<Data> getData() {
        return mData;
    }
}
