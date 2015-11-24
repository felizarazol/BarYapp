package com.baryapp.baryapp.util;


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import com.baryapp.baryapp.MapsActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
*/


public class ManageAsyncTask {
    private String name;
    private Context context;

    public ManageAsyncTask(Context context, String name) {
        this.context = context;
        this.name = name;

    }

    public void sendGet(String url) {
        SendHttpGet t = new SendHttpGet();
        String params = new String(url);
        t.execute(params);
    }
/*
    public void updateDB(String url) {
        SendHttpNewJson t = new SendHttpNewJson();
        String params = new String(url);
        t.execute(params);
    }

    public void sendPost(String url) {
        SendHttpPost t = new SendHttpPost();
        String params = new String(url);
        t.execute(params);
    }
    public void downLoadImage(String url) {
        SendHttpImage t = new SendHttpImage();
        String params = new String(url);
        t.execute(params);
    }
*/
    public void sendRoute(String url) {
        SendHttpRoute t = new SendHttpRoute();
        String params = new String(url);
        t.execute(params);
    }/*

    public void locationCity(String url) {
        locationGeo t = new locationGeo();
        String params = new String(url);
        t.execute(params);
    }*/

    private class SendHttpGet extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String data = GET(url);
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
//                if (name.equals("ConfigurationActivity")) {
//                    ((ConfigurationActivity) context).processingResult(result);
//                } else if (name.equals("DetailActivity")) {
//                    ((DetailActivity) context).processingResult(result);
//                } else if (name.equals("ListActivity")) {
//                    ((ListActivity) context).processingResultCitaProx(result);
//                    ((ListActivity) context).setIsFinish(true);
//                } else if (name.equals("ListActivityDef")) {
//                    ((ListActivity) context).processingResultSortDefault(result);
//                    ((ListActivity) context).setIsFinishDefault(true);
//                } else if (name.equals("ListMultiActivity")) {
//                    ((ListMultiPrestActivity) context).processingResultCitaProx(result);
//                    ((ListMultiPrestActivity) context).setIsFinish(true);
//                } else if (name.equals("ListMultiActivityDef")) {
//                    ((ListMultiPrestActivity) context).processingResultSortDefault(result);
//                    ((ListMultiPrestActivity) context).setIsFinishDefault(true);
//                } else if (name.equals("SplashActivity")) {
//                    ((SplashActivity) context).processingResult(result);
//                } else if (name.equals("YoPrestadorActivityLogin")) {
//                    ((YoPrestadorActivity) context).processingResult(result);
//                    ((YoPrestadorActivity) context).progressBar.cancel();
//                } else if (name.equals("YoPrestadorActivityPerfil")) {
//                    ((YoPrestadorActivity) context).processingResultPerfil(result);
//                    ((YoPrestadorActivity) context).progressBar.cancel();
//                }else
            if (name.equals("MapsActivity")) {
                    ((MapsActivity) context).processingResult(result);
            }
            } else {
//                if (name.equals("ListActivity")) {
//                    ((ListActivity) context).setIsFinish(true);
//                }else if (name.equals("ListActivityDef")) {
//                    ((ListActivity) context).setIsFinishDefault(true);
//                }else if (name.equals("ListMultiActivity")) {
//                    ((ListActivity) context).setIsFinish(true);
//                } else if (name.equals("ListMultiActivityDef")) {
//                    ((ListActivity) context).setIsFinishDefault(true);
//                } else if (name.equals("YoPrestadorActivityLogin")) {
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//                    alertDialogBuilder.setTitle(R.string.app_name);
//                    alertDialogBuilder.setMessage(R.string.text_error_envio);
//                    alertDialogBuilder.setPositiveButton("Aceptar",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                }
//                            });
//                    AlertDialog alertDialog = alertDialogBuilder.create();
//                    alertDialog.show();
//
//                    ((YoPrestadorActivity) context).progressBar.cancel();
//
//                } else if (name.equals("YoPrestadorActivityPerfil")) {
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//                    alertDialogBuilder.setTitle(R.string.app_name);
//                    alertDialogBuilder.setMessage(R.string.text_error_envio);
//                    alertDialogBuilder.setPositiveButton("Aceptar",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                }
//                            });
//                    AlertDialog alertDialog = alertDialogBuilder.create();
//                    alertDialog.show();
//
//                    ((YoPrestadorActivity) context).progressBar.cancel();
//                }
            }
        }
    }


    public static String GET(String url) {
        InputStream inputStream = null;
        String result = null;
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);

            } else {
                result = "Did not work!";
            }


        } catch (ClientProtocolException e) {
            Log.e("ClientProtocol", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getLocalizedMessage());
        }

        return result;
    }


    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

/*
    private class SendHttpNewJson extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            conf.setStatusUpdate(false);

            String url = params[0];

            InputStream inputStream = null;
            String result = "";
            int count;
            try {

                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();

                // make GET request to the given URL
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();
                OutputStream fos;
                if (name.equals("ConfigurationActivity")) {
                    fos = new FileOutputStream(((ConfigurationActivity) context).uri);
                } else {
                    fos = new FileOutputStream(((SplashActivity) context).uri);
                }
                fos.flush();

                long lenghtOfFile = httpResponse.getEntity().getContentLength();

                byte data[] = new byte[1024];
                long total = 0;
                int progress = 0;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;

                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    fos.write(data, 0, count);
                }

                fos.flush();

                // closing streams
                fos.close();
                inputStream.close();

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
                conf.setNameJSON("CuadroMedicoRestColsanitas_2015_04_28-23_36_26.json");
                conf.setStatusUpdate(true);
            }

            return "";
        }

        protected void onProgressUpdate(String... progress) {
            if (name.equals("ConfigurationActivity")) {
                ((ConfigurationActivity) context).progressBar.setProgress(Integer.parseInt(progress[0]));
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (name.equals("ConfigurationActivity")) {
                ((ConfigurationActivity) context).updateDB();
            } else {
                ((SplashActivity) context).updateDB();
            }
        }

    }

    private class SendHttpPost extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];

            String data = postData(url);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null) {
                    if (name.equals("DetailActivity")) {
                        ((DetailActivity) context).processingResultMeGusta(result);
                        ((DetailActivity) context).progressBar.cancel();
                    }else if (name.equals("InfoErrorActivity")) {
                        ((InfoErrorActivity) context).processingResult(result);
                        ((InfoErrorActivity) context).progressBar.cancel();
                    }else if (name.equals("MedicActivity")) {
                        ((MedicActivity) context).processingResultSave(result);
                        ((MedicActivity) context).progressBar.cancel();
                    }else if (name.equals("InstitucionActivity")) {
                        ((InstitucionActivity) context).processingResultSave(result);
                        ((InstitucionActivity) context).progressBar.cancel();
                    }
            } else {

                if (name.equals("DetailActivity")) {
                    ((DetailActivity) context).progressBar.cancel();
                } else if (name.equals("InfoErrorActivity")) {
                    ((InfoErrorActivity) context).progressBar.cancel();
                }else if (name.equals("MedicActivity")) {
                    ((MedicActivity) context).progressBar.cancel();
                }else if (name.equals("InstitucionActivity")) {
                    ((InstitucionActivity) context).progressBar.cancel();
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(R.string.app_name);
                alertDialogBuilder.setMessage(R.string.noconectado);
                alertDialogBuilder.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }


        }

    }

    public String postData(String url1) {
        // Create a new HttpClient and Post Header
        InputStream inputStream = null;
        String result = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url1);

        try {
            // Add your data

            List<NameValuePair> nameValuePairs= new ArrayList<NameValuePair>(2);

            if (name.equals("DetailActivity")) {
                nameValuePairs = new ArrayList<NameValuePair>(2);

                nameValuePairs = postMeGusta(nameValuePairs);
            } else if (name.equals("InfoErrorActivity")){
                nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs = postInfoError(nameValuePairs);
            }else if (name.equals("MedicActivity")){
                nameValuePairs = postMedic(nameValuePairs);
            }else if (name.equals("InstitucionActivity")){
                nameValuePairs = postInsti(nameValuePairs);
            }

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            inputStream = response.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null) {
                result = ManageAsyncTask.convertInputStreamToString(inputStream);

            } else {
                result = null;
            }

        } catch (ClientProtocolException e) {
            Log.e("ClientProtocol", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getLocalizedMessage());
        }

        return result;

    }

    private List<NameValuePair> postMedic(List<NameValuePair> nameValuePairs) {

        String biografia = null, title, univer, year;
        for (int i = 0; i < ((MedicActivity) context).layoutEstudios.getChildCount(); i++) {
            title = ((EditText) ((LinearLayout) ((MedicActivity) context).layoutEstudios.getChildAt(i)).getChildAt(1)).getText().toString();
            univer = ((EditText) ((LinearLayout) ((MedicActivity) context).layoutEstudios.getChildAt(i)).getChildAt(3)).getText().toString();
            year = ((EditText) ((LinearLayout) ((MedicActivity) context).layoutEstudios.getChildAt(i)).getChildAt(5)).getText().toString();

            if (i == 0) {
                biografia = title + "$" + univer + "$" + year;
            } else {

                biografia = biografia + "|" + title + "$" + univer + "$" + year;
            }
        }
        biografia = biografia + "|" + ((MedicActivity) context).textReco.getText().toString();

        if (((MedicActivity) context).textOpor.getText().toString().equals("")) {
            biografia = biografia + "|" + "0";
        } else {
            biografia = biografia + "|" + ((MedicActivity) context).textOpor.getText().toString();
        }

        String encodedImage;

        if (((MedicActivity) context).imageSend != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ((MedicActivity) context).imageSend.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        } else {
            encodedImage = "";
        }

        nameValuePairs.add(new BasicNameValuePair("foto", encodedImage));
        nameValuePairs.add(new BasicNameValuePair("biografia", biografia));

        nameValuePairs.add(new BasicNameValuePair("user", ((MedicActivity) context).user));

        nameValuePairs.add(new BasicNameValuePair("pass", ((MedicActivity) context).pass));
        return nameValuePairs;
    }

    private List<NameValuePair> postInsti(List<NameValuePair> nameValuePairs) {

        String biografia = null, title, univer, year;
        for (int i = 0; i < ((InstitucionActivity) context).layoutEstudios.getChildCount(); i++) {
            title = ((EditText) ((LinearLayout) ((InstitucionActivity) context).layoutEstudios.getChildAt(i)).getChildAt(1)).getText().toString();
            univer = ((EditText) ((LinearLayout) ((InstitucionActivity) context).layoutEstudios.getChildAt(i)).getChildAt(3)).getText().toString();
            year = ((EditText) ((LinearLayout) ((InstitucionActivity) context).layoutEstudios.getChildAt(i)).getChildAt(5)).getText().toString();

            if (i == 0) {
                biografia = title + "$" + univer + "$" + year;
            } else {

                biografia = biografia + "|" + title + "$" + univer + "$" + year;
            }
        }
        biografia = biografia + "|" + ((InstitucionActivity) context).textReco.getText().toString();

        if (((InstitucionActivity) context).textOpor.getText().toString().equals("")) {
            biografia = biografia + "|" + "0";
        } else {
            biografia = biografia + "|" + ((InstitucionActivity) context).textOpor.getText().toString();
        }

        String encodedImage;

        if (((InstitucionActivity) context).imageSend != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ((InstitucionActivity) context).imageSend.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        } else {
            encodedImage = "";
        }

        nameValuePairs.add(new BasicNameValuePair("foto", encodedImage));
        nameValuePairs.add(new BasicNameValuePair("biografia", biografia));

        nameValuePairs.add(new BasicNameValuePair("user", ((InstitucionActivity) context).user));

        nameValuePairs.add(new BasicNameValuePair("pass", ((InstitucionActivity) context).pass));
        return nameValuePairs;
    }

    private List<NameValuePair> postMeGusta(List<NameValuePair> nameValuePairs) {
        if (((DetailActivity) context).directorio) {
            nameValuePairs.add(new BasicNameValuePair("idprestador", ((DetailActivity) context).idPrest));
        } else {
            nameValuePairs.add(new BasicNameValuePair("idPunto", ((DetailActivity) context).idPrest));
        }

        nameValuePairs.add(new BasicNameValuePair("identificador", ((DetailActivity) context).identificador));

        return nameValuePairs;
    }

    private List<NameValuePair> postInfoError(List<NameValuePair> nameValuePairs) {
        String inconsistencias = null;
        if (((InfoErrorActivity) context).layouTel.isSelected()) {
            if (inconsistencias == null) {
                inconsistencias = "Telefono";
            } else {
                inconsistencias = inconsistencias + "|Telefono";
            }

        }
        if (((InfoErrorActivity) context).layoutDir.isSelected()) {
            if (inconsistencias == null) {
                inconsistencias = "Direccion";
            } else {
                inconsistencias = inconsistencias + "|Direccion";
            }
        }
        if (((InfoErrorActivity) context).layoutServ.isSelected()) {
            if (inconsistencias == null) {
                inconsistencias = "Servicios";
            } else {
                inconsistencias = inconsistencias + "|Servicios";
            }

        }

        nameValuePairs.add(new BasicNameValuePair("idprestador", ((InfoErrorActivity) context).idPrest));
        nameValuePairs.add(new BasicNameValuePair("inconsistencias", inconsistencias));
        return nameValuePairs;
    }


    private class SendHttpImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.contenedorfoto_detail_mask);

            int imageHeight;
            if (name.equals("DetailActivity")) {
                mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.contenedorfoto_detail_mask);
                imageHeight = (int) (((DetailActivity) context).tamaño = mask.getHeight());
            }else if (name.equals("MedicActivity")) {
                mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.contenedorfoto_yoprestador_mask);
                imageHeight = (int) (((MedicActivity) context).tamaño = mask.getHeight());
            }else if (name.equals("InstitucionActivity")) {
                mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.contenedorfoto_yoprestador_mask);
                imageHeight = (int) (((InstitucionActivity) context).tamaño = mask.getHeight());
            }else {
                imageHeight = (int) (mask.getHeight());
            }
            String url = params[0];

            DownloadImage client = new DownloadImage(url);
            Bitmap data = client.downloadImageHttp(context, imageHeight);

            return data;
        }

        @Override
        protected void onPostExecute(Bitmap img) {

            if (name.equals("DetailActivity")) {
                ((DetailActivity) context).postDownImage(img);
            }else if (name.equals("MedicActivity")) {
                ((MedicActivity) context).postDownImage(img);
            }else if (name.equals("InstitucionActivity")) {
                ((InstitucionActivity) context).postDownImage(img);
            }

        }
    }
*/

    private class SendHttpRoute extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... params) {
            String url = params[0];
            Document data = postDataRoute(url);
            return data;
        }

        @Override
        protected void onPostExecute(Document doc) {

            if (doc != null) {
                ((MapsActivity)context).postRoute(doc);
            }
        }

    }
    private Document postDataRoute(String url) {
        //try {
            //Todo quitar esto

  /*          HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse response = httpClient.execute(httpPost, localContext);
            InputStream in = response.getEntity().getContent();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = builder.parse(in);
            return doc;
        } catch (Exception e) {
            Log.e("httpClient", e.getLocalizedMessage());
        }*/
        return null;
    }
    private class locationGeo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            double lat = 4.614121, lng = -74.175604;

            Location loc = myLocation();
            if (loc != null) {
                lat = loc.getLatitude();
                lng = loc.getLongitude();
            }
      //      confCiudad(lat, lng);

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
/*
    public void confCiudad(double lat, double lng) {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (!addresses.isEmpty()) {

                String myCiudad = addresses.get(0).getLocality();
                myCiudad = Normalizer.normalize(myCiudad, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                myCiudad = myCiudad.toUpperCase();

                Dao dao = getHelper().getCiudadDao();
                List ciudades = dao.queryForAll();

                if (ciudades.isEmpty()) {
                    Log.e("busqueda", "No se encontraron ciudades");
                } else {
                    String nameCiudad;
                    for (int i = 0; i < ciudades.size(); i++) {
                        nameCiudad = ((Ciudad) ciudades.get(i)).getNombre();
                        if (nameCiudad.contains(myCiudad)) {
                            conf.setCiudadId(((Ciudad) ciudades.get(i)).getId());
                        }
                    }
                }
            }

        } catch (IOException e) {
            Log.e("IOException", e.getLocalizedMessage());
        } catch (SQLException e) {
            Log.e("SQLException", e.getLocalizedMessage());
        }

    }
    */
    private Location myLocation() {
        Location myLocation = null;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        //getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {

            //First get location from Network Provider
            if (isNetworkEnabled) {
                if (locationManager != null)
                    myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            //if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                if (myLocation == null) {
                    if (locationManager != null) {
                        myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            }
        }

        return myLocation;
    }
    /*
    private DbHelper getHelper() {

        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(context, DbHelper.class);
        }
        return mDBHelper;
    }*/
}
