
package acme.entities.campaigns;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface CampaignRepository extends AbstractRepository {

	@Query("SELECT sum(m.effort) FROM Milestone m WHERE m.campaign.id = :campaignId")
	Double findTotalOfferByCampaign(int campaignId);

	@Query("SELECT count(m) > 0 FROM Milestone m WHERE m.campaign.id = :campaignId")
	Boolean existsByCampaignId(int campaignId);

	@Query("SELECT c FROM Campaign c WHERE c.ticker = :ticker")
	Campaign findCampaignByTicker(String ticker);
}
