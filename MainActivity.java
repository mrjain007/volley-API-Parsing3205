package com.example.hplaptop.json_example3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button submit;
    ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> gangwal;
    String category_type, product_id, product_name, description, quantity, image, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        submit = (Button) findViewById(R.id.submit);
        gangwal = new ArrayList<HashMap<String, String>>();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Fetch_Asynctask().execute();
            }
        });


    }
        private class Fetch_Asynctask extends AsyncTask<Void, Void, Void> {

            // static final String TAG = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                // Making a request to url and getting response

                String jsonStr = sh.makeServiceCall("http://demo.ornate-it.com/gangwal/app/categorytype_wise_product.php?category_id=1");
                // Log.e("Tag", "Response from url: " + jsonStr);

                System.out.println("Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("response");
                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {

                            JSONObject c = contacts.getJSONObject(i);
                            category_type = c.getString("category_type");
                            product_id = c.getString("product_id");
                            product_name = c.getString("product_name");
                            description = c.getString("description");
                            quantity = c.getString("quantity");
                            image = c.getString("image");
                            price = c.getString("price");
                            HashMap<String, String> contact = new HashMap<String, String>();
                            contact.put("category_type", category_type);
                            contact.put("product_id", product_id);
                            contact.put("product_name", product_name);
                            contact.put("description", description);
                            contact.put("quantity", quantity);
                            contact.put("image", image);
                            contact.put("price", price);
                            gangwal.add(contact);
                        }
                    } catch (final JSONException e) {
                        Log.e("Tag", "Json parsing error: " + e.getMessage());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
      /* Toast.makeText(getApplicationContext(),
         "Server is down" + e.getMessage(),
         Toast.LENGTH_LONG).show();*/
                            }
                        });

                    }
                } else {
                    Log.e("Tag", "Couldn't get data from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
     /* Toast.makeText(getApplicationContext(),
        "Couldn't get data from server.",
        Toast.LENGTH_LONG).show();*/

                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog


                if (pDialog.isShowing())

                    pDialog.dismiss();


                gangwal_List_Adapter adapter = new gangwal_List_Adapter(MainActivity.this, gangwal);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        category_type=gangwal.get(position).get(category_type);

                    }
                });


            }
        }







}