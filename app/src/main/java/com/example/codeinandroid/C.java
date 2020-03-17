package com.example.codeinandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class C extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<Data> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AddingData();
        adapter = new RecyclerAdapter(mData, getApplicationContext(), this);
        recyclerView.setAdapter(adapter);

    }

    private void AddingData() {
        mData = new ArrayList<>();
        mData.add(new Data("Basic Programs", "true"));
        mData.add(new Data("Write a program to display the message HELLO WORLD. ", ""));
        mData.add(new Data("Write a program to declare some variables of type int, float and double.Assign some values to these variables and display these values.  ", ""));
        mData.add(new Data("Write a program to find the addition, subtraction, multiplication and division of two numbers.  ", ""));
        mData.add(new Data("Programs on variables ", "true"));
        mData.add(new Data("Write a program to swap two numbers without using third variable.  ", ""));
        mData.add(new Data("Write a program to find the area of rectangle, square and circle", ""));
        mData.add(new Data("Write a program to find the volume of a cube, sphere, and cylinder.", ""));
        mData.add(new Data("Conditional statements and loops(basic)", "true"));
        mData.add(new Data("Write a program to enter a number from the user and display the month name. If number >13 then display invalid input using switch case.", ""));
        mData.add(new Data("Write a program to check whether the number is even or odd", ""));
        mData.add(new Data("Write a program to check whether the number is positive, negative or zero", ""));
        mData.add(new Data("Write a program to find the factorial of a number.", ""));
        mData.add(new Data("Write a program to check whether the entered number is prime or not", ""));
        mData.add(new Data("Write a program to find the largest of three numbers. ", ""));
        mData.add(new Data("Conditional statements and loops(advanced)", "true"));
        mData.add(new Data("Write a program to find the sum of squares of digits of a number.", ""));
        mData.add(new Data("Write a program to reverse the digits of an integer. ", ""));
        mData.add(new Data("Write a program to find the sum of numbers from 1 to 100.", ""));
        mData.add(new Data("Write a programs to print the Fibonacci series.", ""));
        mData.add(new Data("Write a program to find the reverse of a number", ""));
        mData.add(new Data("Write a program to find whether a given number is palindrome or not.", ""));
        mData.add(new Data("Write a program that solve the quadratic equation", ""));
        mData.add(new Data("Write a program to check whether the entered number is Armstrong or not", ""));
        mData.add(new Data("Write a program to count the digit in a number", ""));
        mData.add(new Data("Programs on patterns", "true"));
        mData.add(new Data("Programs on different patterns", ""));
        mData.add(new Data("Functions", "true"));
        mData.add(new Data("Programs on Functions.", ""));
        mData.add(new Data("Recursive functions", "true"));
        mData.add(new Data("Write a program to find the factorial of a number using recursive function.", ""));
        mData.add(new Data("Write a program to find the sum of natural number using recursive function.", ""));
        mData.add(new Data("Arrays", "true"));
        mData.add(new Data("Write a program to find the largest value that is stored in the array", ""));
        mData.add(new Data("Write a program using pointers to compute the sum of all elements stored in an array", ""));
        mData.add(new Data("Write a program to arrange the ‘n’ numbers stored in the array in ascending and descending order.", ""));
        mData.add(new Data("Write a program that performs addition and subtraction of matrices", ""));
        mData.add(new Data("Write a program that performs multiplication of matrices.", ""));
        mData.add(new Data("Pointers", "true"));
        mData.add(new Data("Write a program to demonstrate the use of pointers.", ""));
        mData.add(new Data("Write a program to perform addition and subtraction of two pointer variables", ""));
        mData.add(new Data("Structures and Unions", "true"));
        mData.add(new Data("Programs on structures.", ""));
        mData.add(new Data("Programs on unions.", ""));

    }

    @Override
    public void OnClick(int position, View itemView, String title) {

        try {
            InputStream is = getAssets().open("cdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("employees");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employee = jsonArray.getJSONObject(i);
                String firstName = employee.getString("name");
                int age2 = employee.getInt("age");
                String mail = employee.getString("email");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnLongPress(int position, View itemView) {
        Toast.makeText(this, "" + mData.get(position).isHeadFlag(), Toast.LENGTH_SHORT).show();
    }
}
