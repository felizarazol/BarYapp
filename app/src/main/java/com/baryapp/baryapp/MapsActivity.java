package com.baryapp.baryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baryapp.baryapp.util.GMapV2Direction;
import com.baryapp.baryapp.util.ManageAsyncTask;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;

    private Location myLocation;
    private GMapV2Direction md;
    private CameraUpdate cu;
    private LatLngBounds.Builder builder;
    private Intent inte;

    private LatLng prestLatLng;
    private Bitmap imageServ;
    private BitmapDescriptor bDIcon;
    private BitmapDescriptor bDIconMulti;
    private Bitmap pin;
    private ArrayList<Marker> markers = new ArrayList<Marker>();
    private ArrayList<Marker> markersRec = new ArrayList<Marker>();
    private int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        md = new GMapV2Direction();
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        inte = getIntent();


        if (map != null) {

            map.setMyLocationEnabled(true);
            map.setInfoWindowAdapter(new MyInfoWindowAdapter());
            map.setOnInfoWindowClickListener(this);
            map.setOnMarkerClickListener(this);
            myLocation = map.getMyLocation();

            map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {

                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    if(!isGPSEnabled && !isNetworkEnabled) {
                        Toast.makeText(MapsActivity.this, getResources().getText(R.string.error_localization_detail), Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });

//            imageServ = BitmapFactory.decodeResource(getResources(), manageImage.imageServiceMaps(idServ));

//            pin= BitmapFactory.decodeResource(getResources(), R.drawable.pin);

//            bDIcon = combineImages(pin, imageServ);

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //getting GPS status
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            //getting network status
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                //First get location from Network Provider
                if (isNetworkEnabled) {
                    if (locationManager != null) {
                        myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
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

            builder = new LatLngBounds.Builder();
//            if (list) {


//                ArrayList<ListaPrestadorEntrada> datos = inte.getParcelableArrayListExtra("datos");
//                ArrayList<ListaPrestadorEntrada> datosMulti = new ArrayList<ListaPrestadorEntrada>();
//
//                for (int i = 0; i < datos.size(); i++) {
//                    datosMulti = new ArrayList<ListaPrestadorEntrada>();
//                    datosMulti.add(datos.get(i));
//                    for (int j = i + 1; j < datos.size(); j++) {
//                        if (Double.parseDouble(datos.get(i).getLatitud()) == Double.parseDouble(datos.get(j).getLatitud()) && Double.parseDouble(datos.get(i).getLongitud()) == Double.parseDouble(datos.get(j).getLongitud())) {
//                            datosMulti.add(datos.get(j));
//                        }
//                    }
//
//                    if (datosMulti.size() > 1) {
//                        arrayMulti.add(datosMulti);
//                        datos.removeAll(datosMulti);
//                        i--;
//                    }
//                }
//
//                datosMulti = new ArrayList<ListaPrestadorEntrada>();
//                ListaPrestadorEntrada multi=new ListaPrestadorEntrada("", "varios", "", "", "", "", "", "", "", "", "", 0);
//                for (int i = 0; i < arrayMulti.size(); i++) {
//                    datosMulti = arrayMulti.get(i);
//                    String num = datosMulti.size()+"";
//
//                    bDIconMulti = combineImageText(pin, num);
//                    LatLng pointLatLong = new LatLng(Double.parseDouble(datosMulti.get(0).getLatitud()), Double.parseDouble(datosMulti.get(0).getLongitud()));
//
//                    Marker marker = map.addMarker(new MarkerOptions().position(pointLatLong).title(getResources().getString(R.string.varios_prest)).snippet(datosMulti.get(0).getDireccion())
//                            .icon(bDIconMulti)
//                            .anchor(0.5f, 0.5f));
//                    builder.include(pointLatLong);
//
//                    hashMarkerMulti.put(marker.getId(), datosMulti);
//                    extraMarkerInfo.put(marker.getId(), multi);
//                }

//                for (int i = 0; i < datos.size(); i++) {
//                    LatLng pointLatLong = new LatLng(Double.parseDouble(datos.get(i).getLatitud()), Double.parseDouble(datos.get(i).getLongitud()));
//
//                    Marker marker = map.addMarker(new MarkerOptions().position(pointLatLong).title(datos.get(i).getTitle()).snippet(datos.get(i).getDireccion())
//                            .icon(bDIcon)
//                            .anchor(0.5f, 0.5f));
//                    builder.include(pointLatLong);
//
//                    extraMarkerInfo.put(marker.getId(), datos.get(i));
//                }
//            } else {
//
//                ListaPrestadorEntrada prestador = getIntent().getExtras().getParcelable("prestador");
//                LatLng pointLatLong = new LatLng(Double.parseDouble(prestador.getLatitud()), Double.parseDouble(prestador.getLongitud()));
//                final Marker marker = map.addMarker(new MarkerOptions().position(pointLatLong).title(prestador.getTitle()).snippet(prestador.getDireccion())
//                        .icon(bDIcon)
//                        .anchor(0.5f, 0.5f));
//                builder.include(pointLatLong);
//                prestLatLng = pointLatLong;
//                extraMarkerInfo.put(marker.getId(), prestador);
//                if (inte.getExtras().getBoolean("route")) {
//                    myLocation = map.getMyLocation();
//                    if (myLocation != null) {
//                        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
//                                myLocation.getLongitude());
//                        String url = md.getDocument(myLatLng, marker.getPosition(),
//                                com.eelseth.sanitas.library.GMapV2Direction.MODE_DRIVING);
//
//                        ManageAsyncTask manageAT = new ManageAsyncTask(this, "MapsActivity");
//                        String params = new String(url);
//                        manageAT.sendRoute(params);
//                    } else {
//                        locationSleep(marker);
//                    }
//                }
//            }
        }
    }


    private void locationSleep(final Marker marker) {

        if (myLocation == null) {
            if (k < 20) {
                final Handler festHandler = new Handler();
                festHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        k++;
                        myLocation = map.getMyLocation();
                        locationSleep(marker);
                        festHandler.removeCallbacks(this);
                    }
                }, 1000);
            }
        } else {
            LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            String url = md.getDocument(myLatLng, marker.getPosition(), GMapV2Direction.MODE_DRIVING);
            ManageAsyncTask manageAT = new ManageAsyncTask(this, "MapsActivity");
            String params = new String(url);
            manageAT.sendRoute(params);
            boundCamera();
        }

    }

//    public void btn_ubicacion(View view) {
//
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        if(isGPSEnabled || isNetworkEnabled) {
//            map.setMyLocationEnabled(true);
//
//            myLocation = map.getMyLocation();
//            if (myLocation != null) {
//                LatLng myLatLng = new LatLng(myLocation.getLatitude(),
//                        myLocation.getLongitude());
//
//                CameraPosition myPosition = new CameraPosition.Builder()
//                        .target(myLatLng).zoom(13).bearing(90).tilt(30).build();
//                map.animateCamera(
//                        CameraUpdateFactory.newCameraPosition(myPosition));
//            }
//        }else{
//            Toast.makeText(MapsActivity.this, getResources().getText(R.string.error_localization_detail), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void btn_parqueadero(View view) {
//        if (map != null) {
//            if (!inte.getExtras().getBoolean("list")) {
//                if (!((Button) view).isSelected()) {
//                    ((Button) view).setBackgroundResource(R.drawable.parqueaderodown);
//
//                    if (markers.isEmpty()) {
//
//                        ListaPrestadorEntrada prestador = getIntent().getExtras().getParcelable("prestador");
//                        LatLng pointLatLong = new LatLng(Double.parseDouble(prestador.getLatitud()), Double.parseDouble(prestador.getLongitud()));
//                        String url = UrlWS.urlParking(prestador.getLatitud(), prestador.getLongitud());
//
//
//                        ManageAsyncTask manageAT = new ManageAsyncTask(this, "MapsActivity");
//
//                        manageAT.sendGet(url);
//
//                    } else {
//                        markersRec = new ArrayList<Marker>();
//                        for (int i = 0; i < markers.size(); i++) {
//
//                            Marker marker = map.addMarker(new MarkerOptions().position(markers.get(i).getPosition()).title(markers.get(i).getTitle()).snippet("parking")
//                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pinpark))
//                                    .anchor(0.5f, 0.5f));
//                            markersRec.add(marker);
//                        }
//                        markers = markersRec;
//                    }
//                    view.setSelected(true);
//                    moveCam();
//                } else {
//                    ((Button) view).setBackgroundResource(R.drawable.parqueaderonormal);
//                    markersRec = markers;
//                    for (int i = 0; i < markersRec.size(); i++) {
//                        markersRec.get(i).remove();
//                    }
//                    view.setSelected(false);
//                }
//            }
//        }
//    }

    @Override
    public void onInfoWindowClick(Marker marker) {

//        ListaPrestadorEntrada prestador = extraMarkerInfo.get(marker.getId());
//
//        if(prestador.getTitle().equals("varios")){
//            ArrayList<ListaPrestadorEntrada> multiPrest = hashMarkerMulti.get(marker.getId());
//
//            Intent intent = new Intent(MapsActivity.this, ListMultiPrestActivity.class);
//            intent.putExtra("multiPrest", true);
//            intent.putParcelableArrayListExtra("multiPrestDatos", multiPrest);
//
//            intent.putExtra("flag", flag);
//            intent.putExtra("tipoOrdena", tipoOrdena);
//            intent.putExtra("tipoOrdenaPorDefecto", tipoOrdenaPorDefecto);
//            intent.putExtra("servicio", nameServ);
//            intent.putExtra("idservicio", idServ);
//            startActivity(intent);
//
//        }else {
//            Intent mainIntent = new Intent(MapsActivity.this, DetailActivity.class);
//            mainIntent.putExtra("directorio", flag);
//            mainIntent.putExtra("prestador", prestador);
//            mainIntent.putExtra("id", prestador.getId());
//            mainIntent.putExtra("idServ", idServ);
//            mainIntent.putExtra("lat", prestador.getLatitud());
//            mainIntent.putExtra("long", prestador.getLongitud());
//            mainIntent.putExtra("name", prestador.getTitle());
//            mainIntent.putExtra("dir", prestador.getDireccion());
//            mainIntent.putExtra("tel", prestador.getTelefono());
//            mainIntent.putExtra("puntuacion", prestador.getPuntuacion());
//            mainIntent.putExtra("valor", prestador.getValor());
//            mainIntent.putExtra("servicio", nameServ);
//
//            startActivity(mainIntent);
//        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.getSnippet().equals("parking")) {
            return true;
        }
        return false;
    }

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        //   private final View myContentsView;

        MyInfoWindowAdapter() {
            //      myContentsView = getLayoutInflater().inflate(R.layout.custom_info_marker, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

//            ImageView imageNext = (ImageView) myContentsView.findViewById(R.id.image_next);
//            imageNext.setColorFilter(Color.argb(255, 114, 223, 0));
//            ImageView imagePrest = (ImageView) myContentsView.findViewById(R.id.image_prestador);
//            imagePrest.setImageBitmap(imageServ);
//            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.text_title));
//            tvTitle.setText(marker.getTitle());
//            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.text_serv));
//            tvSnippet.setText(nameServ);
//            TextView tvDirection = ((TextView) myContentsView.findViewById(R.id.text_dir));
//            tvDirection.setText(marker.getSnippet());
//
//            ListaPrestadorEntrada prestador = extraMarkerInfo.get(marker.getId());

            return null;
        }

        @Override
        public View getInfoWindow(Marker marker) {

            return null;
        }
    }

    public void postRoute(Document doc){
        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(7).color(
                Color.argb(255, 0, 151, 207));

        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }
        Polyline polylin = map.addPolyline(rectLine);
    }


    public void processingResult(String result) {
        try {
            String lat, lng, name, dir, vicinity;
            ArrayList<String> categ = new ArrayList<String>();

            JSONObject obj = new JSONObject(result);

            JSONArray results = obj.getJSONArray("results");


            for (int i = 0; i < results.length(); i++) {

                JSONObject subResult = results.getJSONObject(i);
                JSONObject geometry = subResult.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                lat = location.getString("lat");
                lng = location.getString("lng");
                name = subResult.getString("name");
                vicinity = subResult.getString("vicinity");
                LatLng pointLatLong = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                Marker marker = map.addMarker(new MarkerOptions().position(pointLatLong).title(name).snippet("parking")
                        //            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pinpark))
                        .anchor(0.5f, 0.5f));
                markers.add(marker);
            }
        } catch (JSONException e) {
            Log.d("JSONObject", e.getLocalizedMessage());
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (map != null) {
                // moveCam();
            }
        }
    }

    private void moveCam() {
        float scale = getResources().getDisplayMetrics().density;
        int padding; // offset from edges of the map in pixels
        if (myLocation != null) {
            LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            builder.include(myLatLng);
            padding = (int) (30 * scale);
            LatLngBounds bounds = builder.build();
            cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        } else {

            if (!inte.getExtras().getBoolean("list")) {
                cu = CameraUpdateFactory.newLatLngZoom(prestLatLng, 11f);
            } else {
                if (builder != null) {
                    padding = (int) (60 * scale);
                    LatLngBounds bounds = builder.build();
                    cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                }
            }
        }

        map.animateCamera(cu);
    }

    private void boundCamera() {

        float scale = getResources().getDisplayMetrics().density;
        int padding;

        LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        builder.include(myLatLng);
        padding = (int) (60 * scale);
        LatLngBounds bounds = builder.build();
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
    }

    public BitmapDescriptor combineImages(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp2.getConfig());

        float left = (bmp1.getWidth()/2) - (bmp2.getWidth() / 2);
        float top = (bmp1.getHeight()*3/8) - (bmp2.getHeight() / 2);

        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, 0, 0, null);
        canvas.drawBitmap(bmp2, left, top, null);

        return BitmapDescriptorFactory.fromBitmap(bmOverlay);
    }

    public BitmapDescriptor combineImageText(Bitmap bmp, String text) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        float left;
        float top = (bmp.getHeight() * 17 / 32);

        float scale = getResources().getDisplayMetrics().density;

        int size = (int)(26*scale);
        if(text.length()>2){
            text="99+";
            left = (bmp.getWidth()* 10 / 40);
            size=(int)(20*scale);
        }else if(text.length()==2){
            left = (bmp.getWidth()* 21 / 80);
            size = (int)(26*scale);
        }else{
            left = (bmp.getWidth()* 31 / 80);
            size = (int)(26*scale);
        }

        Paint paint = new Paint();
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Roboto-Light.ttf");
        paint.setTypeface(tf);
        //  paint.setColor(getResources().getColor(R.color.blue_text_white));
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);

        Canvas canvas = new Canvas(bmOverlay);

        canvas.drawBitmap(bmp, 0, 0, null);
        canvas.drawText(text, left, top, paint);

        return BitmapDescriptorFactory.fromBitmap(bmOverlay);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}