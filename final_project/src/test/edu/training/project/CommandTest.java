package test.edu.training.project;

import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import org.junit.Test;

/**
 * Created by Dell on 29.01.2017.
 */
public class CommandTest {

    @Test(expected = DAOException.class)
    public void getSongByIdTest() throws DAOException{
        SongDAO songDAO = new SongDAO();
        songDAO.getSongById(-3);
    }

    @Test(expected = DAOException.class)
    public void addSongTest() throws DAOException{
        SongDAO songDAO = new SongDAO();
        songDAO.deleteSongById(-1);
    }
}
