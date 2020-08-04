package edu.uic.cs478.project21;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static SongDetails songDetails[] = new SongDetails[8];
    private static String youtubeUrl = "https://www.youtube.com/watch?v=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        populateSongDetails();
        ListView songListView = (ListView) findViewById(R.id.listView);
        //The view which would hold the list of songs
        populateSongDetails();
        //initializing the song details
        SongAdapter songListAdapter = new SongAdapter(this, songDetails);
        //the adapter to load the individdual rows with content
        songListView.setAdapter(songListAdapter);

        registerForContextMenu(songListView);
        // registering listener for a single click
        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri playVideoURI = Uri.parse(youtubeUrl + songDetails[position].getVideoCode());
                //video url to be played
                Intent playSongIntent = new Intent(Intent.ACTION_VIEW, playVideoURI);
                playSongIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(playSongIntent);

            }
        });
   }

    public void onCreateContextMenu(ContextMenu contextMenu,View view,ContextMenu.ContextMenuInfo contextMenuInfo)
    {
        super.onCreateContextMenu(contextMenu,view,contextMenuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_long_click_list,contextMenu);
        //inflating the menuitems
    }

    //custom function to initialize song details
    private void populateSongDetails() {
        songDetails[0]=new SongDetails("kalasala","Simbu","tScFmWH-heM");
        songDetails[7]=new SongDetails("anbil avan","Simbu","S1PkwgN50Pc");
        songDetails[1]=new SongDetails("love yourself","bieber","oyEuk8j8imI");
        songDetails[2]=new SongDetails("neduvali","Simbu","19CdwdItB3o");
        songDetails[3]=new SongDetails("Lean on","Major Lazer","YqeW9_5kURI");
        songDetails[4]=new SongDetails("Hello its me","Adele","YQHsXMglC9A");
        songDetails[5]=new SongDetails("Comfortably","Pink Floyd","J8fFVOoqepc");
        songDetails[6]=new SongDetails("Sameoldlove","Selena Gomez","9h30Bx4Klxg");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        //defining the action to be performed on longclick
    switch(item.getItemId())
    {
        case R.id.playSong:
            // playing the song option
            Uri playVideoURI = Uri.parse(youtubeUrl + songDetails[info.position].getVideoCode());
            Intent playSongIntent = new Intent(Intent.ACTION_VIEW, playVideoURI);
            playSongIntent.addCategory(Intent.CATEGORY_BROWSABLE);
            //to custom choose the browser on which the action should take place.
            startActivity(playSongIntent);
            break;

        case R.id.showSongDetail:
            //showing song details option
            String chosenTitle = songDetails[info.position].getTitleName();
            Intent wikiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/"+chosenTitle));
            wikiIntent.addCategory(Intent.CATEGORY_BROWSABLE);
             startActivity(wikiIntent);
            break;

        case R.id.showArtistDetail:
            //showing artist detail action
            String chosenArtist = songDetails[info.position].getArtist();
            Intent wikiArtistIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/"+chosenArtist));
            wikiArtistIntent.addCategory(Intent.CATEGORY_BROWSABLE);
            startActivity(wikiArtistIntent);
            break;

    }
        return super.onContextItemSelected(item);
    }

    //Custom adapter class to provide values for list items
    private class SongAdapter extends ArrayAdapter<SongDetails> {
        SongDetails[] songDetails;
        Context context;

        public SongAdapter(Context context, SongDetails[] songDetails) {
            super(context, 0, songDetails);
            this.context = context;
            this.songDetails = songDetails;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {

            LayoutInflater songItemlayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View songItemRowView = songItemlayoutInflater.inflate(R.layout.song_item_layout, parent, false);
            TextView songTitle = (TextView) songItemRowView.findViewById(R.id.textView1);
            TextView songArtist = (TextView) songItemRowView.findViewById(R.id.textView2);
            ImageView songImage = (ImageView) songItemRowView.findViewById(R.id.imageView);
            songTitle.setText(songDetails[position].getTitleName());
            songArtist.setText(songDetails[position].getArtist());
            //getting references to the components to set values to them
            switch(position) {
                case 0:
                    songImage.setImageResource(R.drawable.osthe2);
                    break;

                case 1:
                    songImage.setImageResource(R.drawable.bieber);
                    break;

                case 2:
                    songImage.setImageResource(R.drawable.osthe2);
                    break;

                case 3:
                    songImage.setImageResource(R.drawable.leanon);
                    break;

                case 4:
                   songImage.setImageResource(R.drawable.adele);
                    break;

                case 5:
                   songImage.setImageResource(R.drawable.pink);
                    break;

                case 6:
                   songImage.setImageResource(R.drawable.selena);
                    break;

                case 7:
                   songImage.setImageResource(R.drawable.vtv);
                    break;

                default:
                    break;

            }
            return songItemRowView;

        }

    }


}
