package music_app.music_app_backend.Service;

import music_app.music_app_backend.Entity.AppUser;
import music_app.music_app_backend.Entity.Song;
import music_app.music_app_backend.Entity.UserFavorite;
import music_app.music_app_backend.Repository.UserFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteService {
    private final UserFavoriteRepository userFavoriteRepository;

    @Autowired
    public UserFavoriteService(UserFavoriteRepository userFavoriteRepository) {
        this.userFavoriteRepository = userFavoriteRepository;
    }

    public void addFavorite(Long userId, Long songId) {
        if (!userFavoriteRepository.existsByUserIdAndSongId(userId, songId)) {
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setUser(new AppUser(userId));
            userFavorite.setSong(new Song(songId));
            userFavoriteRepository.save(userFavorite);
        }
    }

//    public List<SongDTO> getUserFavoriteSongs(Long userId) {
//        return userFavoriteRepository.findFavoriteSongsByUserId(userId);
//    }
}
