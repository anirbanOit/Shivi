package com.example.project1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD = 1;
    private TextView mResponseTv, male, female;
    private EditText fname, lname, dob;
    private Spinner dept;
    private ImageView photo;
    private Bitmap bitmap;
    private Button add, prev;
    private final String regexpDate = "^(?:29(\\\\/|-|\\\\.)0?2\\\\3(?:(?:(?:1[6-9]|[2-9]\\\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:31(\\\\/|-|\\\\.)(?:0?[13578]|1[02]))\\\\1|(?:(?:29|30)(\\\\/|-|\\\\.)(?:0?[1,3-9]|1[0-2])\\\\2))(?:(?:1[6-9]|[2-9]\\\\d)?\\\\d{2})$|^(?:0?[1-9]|1\\\\d|2[0-8])(\\\\/|-|\\\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\\\4(?:(0?:1[6-9]|[2-9]\\\\d)?\\\\d{2})$";
    private final String regexp = "^[A-Z+a-z]+$";
    private APIService mAPIService;
    String TAG = "MainActivity: ";
    String gender,dateofBirth=null,firstName,lastName;
    String imageString;
    ArrayAdapter adapter;
    private Pattern pattern_date,pattern_fn,pattern_ln;
    private Matcher matcher_date,matcher_finame,matcher_laname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService =
                RetrofitClient.getClient().create(APIService.class);

        setSpinnerAdapter();
        setOnClickListener();
    }

    private void setOnClickListener() {
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = male.getText().toString().trim();
                genderToggle(gender);

            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = female.getText().toString().trim();
                genderToggle(gender);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = fname.getText().toString().trim();
                String lastName = lname.getText().toString().trim();
                String dateOfBirth = dob.getText().toString().trim();
                String department = dept.getSelectedItem().toString().trim();
                if(firstName != null && lastName != null && dateOfBirth != null){
                    if(validateDate() && validateName()){

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap = ((BitmapDrawable) photo.getDrawable()).getBitmap();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageBytes = baos.toByteArray();
                        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


                        String image = imageString.trim();
                            Post post = new Post(firstName, lastName, gender, dateOfBirth, department, image);
                            sendPost(post);

                    }


                }
                else
                    Toast.makeText(getApplication(),"Name and Date Not Validated",Toast.LENGTH_SHORT).show();


            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(obj);
            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagefromGallery();
            }
        });
    }

    private void init() {
        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        male = (TextView) findViewById(R.id.male);
        female = (TextView) findViewById(R.id.female);
        dob = (EditText) findViewById(R.id.dob);
        dept = (Spinner) findViewById(R.id.dept);
        photo = (ImageView) findViewById(R.id.img);
        add = (Button) findViewById(R.id.add);
        prev = (Button) findViewById(R.id.prev);
    }

    private void setSpinnerAdapter() {
        adapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept.setAdapter(adapter);
    }

    public void sendPost(Post post) {
        mAPIService.savePost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    //showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    Toast.makeText(MainActivity.this, "post submitted to API.", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(MainActivity.this);
                    } else {
                        builder = new AlertDialog.Builder(MainActivity.this);
                    }
                    builder.setTitle("Add User")
                            .setMessage("Data Inserted Succesfully.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Toast.makeText(MainActivity.this, "Unable to post ", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(MainActivity.this);
                    } else {
                        builder = new AlertDialog.Builder(MainActivity.this);
                    }
                    builder.setTitle("Add User")
                            .setMessage("Data not Inserted due to Timeout. Please Try Again.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
            }
        });
    }

    private void datePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.app.AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dob.setText(s);
                 dateofBirth = dob.getText().toString();
            }
        }, y, m, d);

        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        dialog.show();
    }

    //Date validation
    private boolean validateDate() {
        pattern_date = Pattern.compile(regexpDate);
        matcher_date = pattern_date.matcher(dateofBirth);

        if (matcher_date.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateName() {
        pattern_fn = Pattern.compile(regexp);
        pattern_ln = Pattern.compile(regexp);
        matcher_finame = pattern_fn.matcher(firstName);
        matcher_laname = pattern_ln.matcher(lastName);

        if (matcher_finame.matches() && matcher_laname.matches()) {
            return true;
        } else {
            return false;
        }

    }

    public void loadImagefromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                photo.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    public void genderToggle(String genderSelection) {
        if (genderSelection.equalsIgnoreCase("Male")) {
            //male.setBackgroundColor(Color.parseColor("#00CED1"));
            male.setBackgroundResource(R.drawable.gender);
            ShapeDrawable shapedrawable = new ShapeDrawable();
            shapedrawable.setShape(new RectShape());
            shapedrawable.getPaint().setColor(Color.BLACK);
            shapedrawable.getPaint().setStrokeWidth(10f);
            shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
            female.setBackgroundDrawable(shapedrawable);
            //female.setBackgroundColor(Color.parseColor("#E6E6E6"));
        }
        if (genderSelection.equalsIgnoreCase("Female")) {
            // female.setBackgroundColor(Color.parseColor("#00CED1"));
            female.setBackgroundResource(R.drawable.gender);
            ShapeDrawable shapedrawable = new ShapeDrawable();
            shapedrawable.setShape(new RectShape());
            shapedrawable.getPaint().setColor(Color.BLACK);
            shapedrawable.getPaint().setStrokeWidth(10f);
            shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
            male.setBackgroundDrawable(shapedrawable);
            // male.setBackgroundColor(Color.parseColor("#E6E6E6"));
        }
    }


}