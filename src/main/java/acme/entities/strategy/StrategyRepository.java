
package acme.entities.strategy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface StrategyRepository extends AbstractRepository {

	@Query("SELECT SUM(t.expectedPercentage) FROM Tactic t WHERE t.strategy.id = :strategyId")
	Double calculateExpectedPercentage(int strategyId);
}
