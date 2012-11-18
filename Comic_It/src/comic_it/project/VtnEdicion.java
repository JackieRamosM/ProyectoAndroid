package comic_it.project;
import comic_it.project.R.id;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class VtnEdicion extends TabActivity{	
    
	@Override
    public void onCreate(Bundle savedInstanceState){    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla);
                        
        TabHost tabHost = getTabHost();
        
        // Tab for the comic
        TabSpec comicspec = tabHost.newTabSpec("Comic");
        comicspec.setIndicator("Comic", getResources().getDrawable(R.drawable.pes1_comic));
        Intent comicIntent = new Intent(VtnEdicion.this,P1_comic.class);        
        comicspec.setContent(comicIntent);        
 
        // Tab for Symbols
        TabSpec symbolsspec = tabHost.newTabSpec("Symbol");
        symbolsspec.setIndicator("Symbol",this.getResources().getDrawable(R.drawable.pes2_symbols));        
        Intent symbolsIntent = new Intent(VtnEdicion.this,P2_symbols.class);
        symbolsspec.setContent(symbolsIntent);
 
        // Tab for Bubbles
        TabSpec bubblesspec = tabHost.newTabSpec("Bubble");
        bubblesspec.setIndicator("Bubble",this.getResources().getDrawable(R.drawable.pes3_bubbles));
        Intent bubblesIntent = new Intent(VtnEdicion.this,P3_bubbles.class);
        bubblesspec.setContent(bubblesIntent);

        // Tab for Finish
        TabSpec donespec = tabHost.newTabSpec("Done");
        donespec.setIndicator("Done",this.getResources().getDrawable(R.drawable.pes4_done));
        Intent doneIntent = new Intent(VtnEdicion.this,P4_done.class);
        donespec.setContent(doneIntent);        
                               
        tabHost.addTab(comicspec);       
        tabHost.addTab(symbolsspec);
        tabHost.addTab(bubblesspec);
        tabHost.addTab(donespec);        
        tabHost.setBackgroundColor(Color.rgb(245,231,11));        
    }
}
