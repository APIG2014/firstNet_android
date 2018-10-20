package com.example.albena.firstnet_hack.feature;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.util.ListenableList;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inflate MapView from layout
        mMapView = (MapView) findViewById(R.id.mapView);

        final ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 37.777220000000064, -122.43145999999996, 16);
        // set the map to be displayed in the mapview
        mMapView.setMap(map);

        map.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                SpatialReference sr = map.getSpatialReference();
                // create a new graphics overlay and add it to the mapview
                GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
                mMapView.getGraphicsOverlays().add(graphicsOverlay);

                //[DocRef: Name=Point graphic with symbol, Category=Fundamentals, Topic=Symbols and Renderers]
                //create a simple marker symbol
                SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.RED, 12f); //size 12, style of circle

                //add a new graphic with a new point geometry
                Point graphicPoint = new Point( -1.3629378889243433E7 , 4547876.6072443975, sr);
                Log.i("Point", graphicPoint.toString());
                Graphic graphic = new Graphic(graphicPoint, symbol);

                graphicsOverlay.getGraphics().add(graphic);
                ListenableList<Graphic> graphicList = graphicsOverlay.getGraphics();
                Log.i("Size of list = ", ""+ graphicList.size());
            }
        });
        map.loadAsync();
    }

    @Override
    protected void onPause(){
        super.onPause();
        // pause MapView
        mMapView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        // resume MapView
        mMapView.resume();
    }
}
