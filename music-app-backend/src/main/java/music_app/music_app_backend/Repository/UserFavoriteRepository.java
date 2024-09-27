package music_app.music_app_backend.Repository;

import music_app.music_app_backend.Entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {

//    @Query("SELECT uf.song FROM UserFavorite uf WHERE uf.user.id = :userId")
//    List<SongDTO> findFavoriteSongsByUserId(Long userId);

    boolean existsByUserIdAndSongId(Long userId, Long songId);
}
