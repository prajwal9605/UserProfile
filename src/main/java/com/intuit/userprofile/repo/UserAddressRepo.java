package com.intuit.userprofile.repo;

import com.intuit.userprofile.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author prajwal.kulkarni on 20/09/21
 */

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, String> {

}
