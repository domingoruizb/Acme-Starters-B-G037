
package acme.features.sponsor.donation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsors.Donation;
import acme.entities.sponsors.Sponsorship;

@Repository
public interface SponsorDonationRepository extends AbstractRepository {

	@Query("SELECT s FROM Sponsorship s WHERE s.id = :sponsorshipId")
	Sponsorship findSponsorshipById(int sponsorshipId);

	@Query("SELECT d FROM Donation d WHERE d.id = :id")
	Donation findDonationById(int id);

	@Query("SELECT d FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Collection<Donation> findDonationsBySponsorshipId(int sponsorshipId);

}
