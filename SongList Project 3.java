/*
Programming Project 3 - API Reading and Method Implementation

For this project, you will be searching and sorting data related to bridges.data_src_dependent.Song objects.
The Song data will be obtained from an online database and accessed via the BRIDGES API - https://bridgesdata.herokuapp.com/api/datasets/songLinks to an external site..

Read song data from the BRIDGES API.
Create a custom linked list of SLelement objects (http://bridgesuncc.github.io/doc/java-api/current/html/classbridges_1_1base_1_1_s_lelement.htmlLinks to an external site.) and populate the linked list with
Song objects created from the dataset.

To simplify the implementation of the linked list, you are required to implement this interface List.java Download List.java 

The class heading for the SongList class must be 
public class SongList implements cmsc256.List<bridges.data_src_dependent.Song>

Provide an additional method that returns a formatted list of all the songs by an artist (name given as a String parameter) to the method that appear on the linked list, grouped by album title 
(in alphabetical order) and by song title (in alphabetical order).

The name of the method must be:
 public String getSongsByArtist(String artist)
 
The returned String is to be formatted with each song on a separate line with the song title, artist and album labeled as shown here:
Title: Harder, Better, Faster, Stronger Artist: Daft Punk Album: Discovery

If no songs by the given artist are on the playlist, an appropriate message is to be displayed. Such as, “There are no songs by Tai Verdes in this playlist.”
It is expected that your program will be well documented and must contain a comment block at the beginning that includes the following information in an easy-to-read format:  the file name, your name, the project number, the course identifier (CMSC 256), and the current semester, and a brief description of the file’s purpose.
*/


package cmsc256;

import bridges.base.SLelement;
import bridges.connect.Bridges;

import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;

import java.io.IOException;
import java.util.ArrayList;

public class SongList implements cmsc256.List<bridges.data_src_dependent.Song>{

    private Bridges bridges;
    private DataSource ds;
    private SLelement<Song> header;
    private SLelement<Song> current;
    private SLelement<Song> tail;
    private int listSize;



    public SongList(){
        bridges = new Bridges(3,"MillerL","1627985354337");
        ds = bridges.getDataSource();
        try{
            ArrayList<Song> songList = ds.getSongData();

            header = new SLelement<Song>(songList.get(0));
            listSize++;
            current = header;
            for (int i = 1; i < songList.size(); i++) {
                current.setNext(new SLelement<Song>(current.getValue(), current.getNext()));
                current.setValue(songList.get(i));
                current = current.getNext();
                listSize++;
            }
            tail = current;
            current = header;
        }
        catch(IOException e){
            System.out.println("Unable to connect to Bridges.");
        }

    }

    @Override
    public void clear() {
        current = tail = new SLelement<Song>();
        header = new SLelement<Song>(tail);
        listSize = 0;
    }

    @Override
    public boolean insert(Song it) {
        current.setNext(new SLelement<Song>(current.getValue(),current.getNext()));
        current.setValue(it);
        if(tail == current){
            tail = current.getNext();
        }
        listSize++;
        return true;
    }

    @Override
    public boolean append(Song it) {
        tail.setNext(new SLelement<Song>());
        tail.setValue(it);
        tail = tail.getNext();
        listSize++;
        return true;
    }

    @Override
    public Song remove() {
        if(current == tail){
            return null;
        }
        Song it = current.getValue();
        current.setValue(current.getNext().getValue());
        if(current.getNext() == tail){
            tail = current;
        }
        current.setNext((current.getNext().getNext()));
        listSize--;
        return it;
    }

    @Override
    public void moveToStart() {
    current = header.getNext();
    }

    @Override
    public void moveToEnd() {
        current = tail;
    }

    @Override
    public void prev() {
        if(header.getNext() == current){
            return;
        }
        SLelement<Song> temp = header;
        while (temp.getNext() != current){
            temp = temp.getNext();
        }
        current = temp;
    }

    @Override
    public void next() {
        if(current != tail){
            current = current.getNext();
        }
    }

    @Override
    public int length() {
        return listSize;
    }

    @Override
    public int currPos() {
        SLelement<Song> temp = header.getNext();
        int i;
        for(i = 0; current != temp; i++){
            temp = temp.getNext();
        }
        return i;
    }

    @Override
    public boolean moveToPos(int pos) {
        if (pos < 0 || pos > listSize)
            return false;
        current = header.getNext();
        for(int i = 0; i < pos; i++){
            current = current.getNext();
        }
        return true;
    }

    @Override
    public boolean isAtEnd() {
        return current == tail;
    }

    @Override
    public Song getValue() {
        if(current == tail)
            return null;
        return current.getValue();
    }
    public String getSongsByArtist(String artist){
        return "";
    }
}
