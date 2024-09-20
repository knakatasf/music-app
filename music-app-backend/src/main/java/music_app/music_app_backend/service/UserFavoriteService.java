package music_app.music_app_backend.service;

import music_app.music_app_backend.entity.Song;
import music_app.music_app_backend.entity.User;
import music_app.music_app_backend.entity.UserFavorite;
import music_app.music_app_backend.repository.UserFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            userFavorite.setUser(new User(userId));
            userFavorite.setSong(new Song(songId));
            userFavoriteRepository.save(userFavorite);
        }
    }

    public List<Song> getUserFavoriteSongs(Long userId) {
        return userFavoriteRepository.findFavoriteSongsByUserId(userId);
    }
}
