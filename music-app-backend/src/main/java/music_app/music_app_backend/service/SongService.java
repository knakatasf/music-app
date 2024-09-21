package music_app.music_app_backend.service;

import music_app.music_app_backend.DTO.SongDTO;
import music_app.music_app_backend.entity.Song;
import music_app.music_app_backend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongDTO insertNewSong(SongDTO songDTO) {
        songRepository.save(new Song(songDTO.getSongName(), songDTO.getArtistName()));
        return songDTO;
    }
}
