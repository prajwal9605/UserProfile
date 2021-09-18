package com.intuit.userprofile.repo;

import com.intuit.userprofile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    User getByEmail(String emailId);

}
