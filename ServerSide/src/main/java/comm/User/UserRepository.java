package comm.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {
    //"rel" is what the FindByTitleContaining is in the endpoint url. this replaces it with a cleaner version

    @RestResource(rel = "username-contains", path = "containsUsername")
    Page<User> findByUsernameContaining(@Param("username") String username, Pageable page);
}
