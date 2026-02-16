
package acme.entities.sponsors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorshipRepository extends AbstractRepository {

	@Query("""
		SELECT SUM(d.money.amount)
		FROM Donation d
		WHERE d.sponsorship.id = :sponsorshipId
		""")
	// AND d.money.currency = 'EUR'
	Double computeTotalMoney(int sponsorshipId);

}
