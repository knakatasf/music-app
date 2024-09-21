package music_app.music_app_backend.DTO;

import music_app.music_app_backend.entity.Song;

public class SongDTO {
    private String songName;
    private String artistName;

    public SongDTO(String songName, String artistName) {
        setSongName(songName);
        setArtistName(artistName);
    }

    public String getSongName() { return songName; }
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public static SongDTO fromEntity(Song song) {
        return new SongDTO(song.getSongName(), song.getArtistName());
    }
}
