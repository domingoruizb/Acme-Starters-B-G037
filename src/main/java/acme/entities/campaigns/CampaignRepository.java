
package acme.entities.campaigns;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface CampaignRepository extends AbstractRepository {

	@Query("SELECT sum(m.effort) FROM Milestone m WHERE m.campaign.id = :campaignId")
	Double findTotalOfferByCampaign(int campaignId);
}
