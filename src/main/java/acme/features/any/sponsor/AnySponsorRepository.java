
package acme.features.any.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsors.Sponsorship;
import acme.realms.Sponsor;

@Repository
public interface AnySponsorRepository extends AbstractRepository {

	@Query("SELECT s FROM Sponsorship s WHERE s.id = :sponsorshipId")
	Sponsorship findSponsorshipById(int sponsorshipId);

	@Query("SELECT s.sponsor FROM Sponsorship s WHERE s.id = :sponsorshipId")
	Sponsor findSponsorBySponsorshipId(int sponsorshipId);

}
