package music_app.music_app_backend.repository;

import music_app.music_app_backend.DTO.SongDTO;
import music_app.music_app_backend.entity.Song;
import music_app.music_app_backend.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {

//    @Query("SELECT uf.song FROM UserFavorite uf WHERE uf.user.id = :userId")
//    List<SongDTO> findFavoriteSongsByUserId(Long userId);

    boolean existsByUserIdAndSongId(Long userId, Long songId);
}
