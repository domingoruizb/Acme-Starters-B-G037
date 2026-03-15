
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsors.Donation;
import acme.entities.sponsors.Sponsorship;

@Repository
public interface SponsorSponsorshipRepository extends AbstractRepository {

	@Query("SELECT s FROM Sponsorship s WHERE s.sponsor.id = :sponsorId")
	Collection<Sponsorship> findSponsorshipsBySponsorId(int sponsorId);

	@Query("SELECT s FROM Sponsorship s WHERE s.id = :id")
	Sponsorship findSponsorshipById(int id);

	@Query("SELECT d FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Collection<Donation> findDonationsBySponsorshipId(int sponsorshipId);

	@Query("SELECT s FROM Sponsorship s WHERE s.ticker = :ticker")
	Sponsorship findSponsorshipByTicker(String ticker);

	@Query("SELECT count(d) > 0 FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Boolean existsBySponsorshipId(int sponsorshipId);

}
