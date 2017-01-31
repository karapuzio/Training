package edu.training.project.command.song;

import edu.training.project.entity.Song;

import java.util.Comparator;

/**
 * Class is used to compare song at similarity coefficient.
 *
 * @author Skidan Olya
 * @version 1.0
 * @see Comparator
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
