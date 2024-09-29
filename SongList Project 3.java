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
