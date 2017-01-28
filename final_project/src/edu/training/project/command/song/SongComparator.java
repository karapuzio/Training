package edu.training.project.command.song;

import edu.training.project.entity.Song;

import java.util.Comparator;

/**
 * Created by Dell on 26.01.2017.
 */
public class SongComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        if (song1.getCoeffJakkara() < song2.getCoeffJakkara()) {
            return 1;
        } else {
            return -1;
        }
    }
}
