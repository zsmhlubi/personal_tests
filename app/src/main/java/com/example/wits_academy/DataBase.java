package com.example.wits_academy;

import static com.example.wits_academy.R.color.white;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    final static String ip  = "http://10.0.2.2/php_app";
    public static void teacher_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = ip + "/teaching_courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    ViewsClass.get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("teacher_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public static void get_all_courses(Context context, String user_number, LinearLayout courses_list, String newText) {
        String url = ip +"/courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    ViewsClass.get_information(context, newText, courses_list, jsonArray, teacher_name, course_code, course_names,user_number);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public static void student_courses(Context context, String user_number, LinearLayout courses_list, TextView display) {
        String url = ip + "/enrolled_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() != 0){
                            display.setVisibility(display.GONE);
                            ArrayList<String> course_names = new ArrayList<>();
                            ArrayList<String> course_code = new ArrayList<>();
                            ArrayList<String> teacher_name = new ArrayList<>();
                            ViewsClass.get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    TextView empty = new TextView(context);
                    empty.setText("No Courses enrolled, check the available Courses");
                    empty.setTextColor(white);
                    courses_list.addView(empty);
//                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void nav_student_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = ip + "/enrolled_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<String> course_names = new ArrayList<>();
                        ViewsClass.get_nav_list_layout(context, courses_list, jsonArray, course_names, user_number);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    TextView empty = new TextView(context);
                    empty.setText("No Courses enrolled, check the available Courses");
                    empty.setTextColor(white);
                    courses_list.addView(empty);
//                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void nav_teacher_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = ip + "/enrolled_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<String> course_names = new ArrayList<>();
                        ViewsClass.get_nav_list_layout(context, courses_list, jsonArray, course_names, user_number);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    TextView empty = new TextView(context);
                    empty.setText("No Courses enrolled, check the available Courses");
                    empty.setTextColor(white);
                    courses_list.addView(empty);
//                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //check if the user does exist in the current data base

    public static void exists(Context context, String user_password, String user_number){
        String url = ip + " /login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_number", user_number);
                data.put("password", user_password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    // check if its a teacher or student before going to the main pages of each user

    public static void back_to_menu(Context context, String user_number){
        String url = ip + " /back_to_menu.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

//to retrieve and view the course info on course homepage
    public static void course(Context context, String course_name, String number){
        String url = ip + "/back_to_menu.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, teacher_course_view.class);
                    intent.putExtra("courseName" , course_name);
                    intent.putExtra("userNumber", number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, student_course_view.class);
                    intent.putExtra("courseName" , course_name);
                    intent.putExtra("userNumber", number);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_number", number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    // Allow data to be saved on the database

    public static void save (Context context, Map < String, String > data_to_send){
        String url = ip + " /register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                System.out.println(error.toString().trim());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //changes the password of the user

    public static void change_password(Context context, Map < String, String > data_to_send){
        String url = ip + " /forgot_password.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


//for the teacher to create a course
    public static void create_course (Context context, Map < String, String > data_to_send){
        String url = ip + " /create_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, teacher_course_view.class);
                intent.putExtra("courseName", data_to_send.get("course_name"));
                intent.putExtra("userNumber" , data_to_send.get("employee_number"));
                context.startActivity(intent);

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                System.out.println(error.toString().trim());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        String url2 = ip +"/course_folder.php";
        StringRequest folderRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        requestQueue.add(folderRequest);
    }
//To allow the student to enroll into a course when they have the course password
    public static void enroll_on(Context context, String course_name, String course_password, String student_number){
        String url = ip + " /enroll.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim()+ " in " + course_name, Toast.LENGTH_SHORT).show();
                course(context,course_name,student_number);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", student_number);
                data.put("course_password", course_password);
                data.put("course_name", course_name);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
//get the user's details from the server to the profile page
    public static void profile(Context context, String userNumber, TextView name, TextView surname , TextView email,
                               TextView number){
        String url = ip + " /view_profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    name.setText(jsonObject.getString("first_name"));
                    surname.setText(jsonObject.getString("last_name"));
                    email.setText(jsonObject.getString("email_address"));

                    if (jsonObject.getString("user_role").equals("student")){
                        number.setText(userNumber);
                    }
                    else if (jsonObject.getString("user_role").equals("teacher")){
                        number.setText(userNumber);
                    }
                    else if (jsonObject.getString("user_role").equals("Student")){
                        number.setText(userNumber);
                    }
                    else if (jsonObject.getString("user_role").equals("Teacher")){
                        number.setText(userNumber);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_number", userNumber);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
//change the profile picture of the user
    public static void change_profile(Context context, Map<String, String> map){
        String url = ip +"/change_profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",map.get("user_number"));
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",map.get("user_number"));
                    context.startActivity(intent);
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = map;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
//upload user's image to the edit profile page
    public static void upload_image(Context context, String image_intent, String userNumber){
        String url = ip +"/upload_image.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("userNumber", userNumber);
                data.put("imageURL", image_intent);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
//get user's image
    public static void get_image(Context context, String userNumber, ImageView imageView){
        Picasso.get()
                .load(ip + "/profile_photos/" + userNumber + ".jpg")
                .error(R.drawable.course_pic_1)
                .placeholder(R.drawable.profile_icon)
                .fit()
                .into(imageView);
    }
    //to retrieve course image
    public static void get_course_image(Context context, String courseName, ImageView imageView){
        Picasso.get()
                .load(ip + "/profile_photos/" + courseName + ".jpg")
                .error(R.drawable.course_pic_1)
                .placeholder(R.drawable.profile_icon)
                .fit()
                .into(imageView);
    }

}
