
package acme.entities.inventions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface InventionRepository extends AbstractRepository {

	@Query("SELECT sum(p.cost.amount) FROM Part p WHERE p.invention.id = :inventionId")
	Double computeCost(int inventionId);

	@Query("SELECT i FROM Invention i WHERE i.ticker = :ticker")
	Invention findInventionByTicker(String ticker);

	@Query("SELECT COUNT(p) > 0 FROM Part p WHERE p.invention.id = :inventionId")
	Boolean existsByInventionId(int inventionId);

}
