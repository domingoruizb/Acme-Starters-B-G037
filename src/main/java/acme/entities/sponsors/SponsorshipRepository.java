
package acme.entities.sponsors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorshipRepository extends AbstractRepository {

	@Query("SELECT SUM(d.money.amount) FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Double computeTotalMoney(int sponsorshipId);

	@Query("SELECT s FROM Sponsorship s WHERE s.ticker = :ticker")
	Sponsorship findSponsorshipByTicker(String ticker);

	@Query("SELECT COUNT(d) > 0 FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Boolean existsBySponsorshipId(int sponsorshipId);

}
