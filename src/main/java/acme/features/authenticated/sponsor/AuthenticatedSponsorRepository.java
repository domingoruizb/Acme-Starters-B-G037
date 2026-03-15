
package acme.features.authenticated.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.realms.Sponsor;

@Repository
public interface AuthenticatedSponsorRepository extends AbstractRepository {

	@Query("SELECT ua FROM UserAccount ua WHERE ua.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("SELECT s FROM Sponsor s WHERE s.userAccount.id = :id")
	Sponsor findSponsorByUserAccountId(int id);

}
